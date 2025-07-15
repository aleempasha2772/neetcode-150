package com.example.neetcode_150.Systems;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;

// Cache Entry class to store value with metadata
class CacheEntry<V> {
    private final V value;
    private final long creationTime;
    private volatile long lastAccessTime;
    private volatile int hitCount;
    private final long ttl; // Time to live in milliseconds
    
    public CacheEntry(V value, long ttl) {
        this.value = value;
        this.creationTime = System.currentTimeMillis();
        this.lastAccessTime = creationTime;
        this.hitCount = 0;
        this.ttl = ttl;
    }
    
    public V getValue() {
        this.lastAccessTime = System.currentTimeMillis();
        this.hitCount++;
        return value;
    }
    
    public boolean isExpired() {
        if (ttl <= 0) return false; // No expiration
        return System.currentTimeMillis() - creationTime > ttl;
    }
    
    public long getLastAccessTime() { return lastAccessTime; }
    public int getHitCount() { return hitCount; }
    public long getCreationTime() { return creationTime; }
    public long getTtl() { return ttl; }
}

// Eviction policies
enum EvictionPolicy {
    LRU,    // Least Recently Used
    LFU,    // Least Frequently Used
    FIFO,   // First In First Out
    RANDOM  // Random eviction
}

// Cache statistics
class CacheStats {
    private volatile long hits = 0;
    private volatile long misses = 0;
    private volatile long evictions = 0;
    private volatile long puts = 0;
    
    public void recordHit() { hits++; }
    public void recordMiss() { misses++; }
    public void recordEviction() { evictions++; }
    public void recordPut() { puts++; }
    
    public long getHits() { return hits; }
    public long getMisses() { return misses; }
    public long getEvictions() { return evictions; }
    public long getPuts() { return puts; }
    public double getHitRate() { 
        long total = hits + misses;
        return total == 0 ? 0.0 : (double) hits / total; 
    }
    
    @Override
    public String toString() {
        return String.format("CacheStats{hits=%d, misses=%d, evictions=%d, puts=%d, hitRate=%.2f%%}", 
                           hits, misses, evictions, puts, getHitRate() * 100);
    }
}

// Main Cache Implementation
public class CustomCache<K, V> {
    private final Map<K, CacheEntry<V>> cache;
    private final int maxSize;
    private final long defaultTtl;
    private final EvictionPolicy evictionPolicy;
    private final CacheStats stats;
    private final ReentrantReadWriteLock lock;
    private final ScheduledExecutorService cleanupExecutor;
    
    // For LRU tracking
    private final LinkedHashMap<K, Long> accessOrder;
    // For LFU tracking
    private final Map<K, Integer> frequencies;
    // For FIFO tracking
    private final Queue<K> insertionOrder;
    
    public CustomCache(int maxSize, long defaultTtl, EvictionPolicy policy) {
        this.maxSize = maxSize;
        this.defaultTtl = defaultTtl;
        this.evictionPolicy = policy;
        this.cache = new ConcurrentHashMap<>();
        this.stats = new CacheStats();
        this.lock = new ReentrantReadWriteLock();
        this.accessOrder = new LinkedHashMap<>();
        this.frequencies = new ConcurrentHashMap<>();
        this.insertionOrder = new ConcurrentLinkedQueue<>();
        
        // Start cleanup thread for expired entries
        this.cleanupExecutor = Executors.newScheduledThreadPool(1);
        this.cleanupExecutor.scheduleWithFixedDelay(this::cleanupExpired, 60, 60, TimeUnit.SECONDS);
    }
    
    // Basic cache operations
    public V get(K key) {
        lock.readLock().lock();
        try {
            CacheEntry<V> entry = cache.get(key);
            if (entry == null) {
                stats.recordMiss();
                return null;
            }
            
            if (entry.isExpired()) {
                stats.recordMiss();
                // Need to upgrade to write lock for removal
                lock.readLock().unlock();
                lock.writeLock().lock();
                try {
                    // Double-check after acquiring write lock
                    entry = cache.get(key);
                    if (entry != null && entry.isExpired()) {
                        cache.remove(key);
                        removeFromTracking(key);
                    }
                    return null;
                } finally {
                    lock.writeLock().unlock();
                }
            }
            
            stats.recordHit();
            V value = entry.getValue();
            
            // Need write lock for tracking updates
            lock.readLock().unlock();
            lock.writeLock().lock();
            try {
                updateAccessTracking(key);
                return value;
            } finally {
                lock.writeLock().unlock();
            }
        } finally {
            // Only unlock if we still hold the read lock
            if (lock.getReadHoldCount() > 0) {
                lock.readLock().unlock();
            }
        }
    }
    
    public void put(K key, V value) {
        put(key, value, defaultTtl);
    }
    
    public void put(K key, V value, long ttl) {
        lock.writeLock().lock();
        try {
            // Check if we need to evict
            if (!cache.containsKey(key) && cache.size() >= maxSize) {
                evict();
            }
            
            CacheEntry<V> entry = new CacheEntry<>(value, ttl);
            cache.put(key, entry);
            
            // Update tracking structures
            updatePutTracking(key);
            stats.recordPut();
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    public V remove(K key) {
        lock.writeLock().lock();
        try {
            CacheEntry<V> entry = cache.remove(key);
            if (entry != null) {
                removeFromTracking(key);
                return entry.getValue();
            }
            return null;
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    public boolean containsKey(K key) {
        lock.readLock().lock();
        try {
            CacheEntry<V> entry = cache.get(key);
            if (entry == null) {
                return false;
            }
            
            if (entry.isExpired()) {
                // Need to upgrade to write lock for removal
                lock.readLock().unlock();
                lock.writeLock().lock();
                try {
                    // Double-check after acquiring write lock
                    entry = cache.get(key);
                    if (entry != null && entry.isExpired()) {
                        cache.remove(key);
                        removeFromTracking(key);
                    }
                    return false;
                } finally {
                    lock.writeLock().unlock();
                }
            }
            
            return true;
        } finally {
            // Only unlock if we still hold the read lock
            if (lock.getReadHoldCount() > 0) {
                lock.readLock().unlock();
            }
        }
    }
    
    public void clear() {
        lock.writeLock().lock();
        try {
            cache.clear();
            accessOrder.clear();
            frequencies.clear();
            insertionOrder.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    public int size() {
        return cache.size();
    }
    
    public boolean isEmpty() {
        return cache.isEmpty();
    }
    
    // Advanced operations
    public V getOrCompute(K key, Function<K, V> computeFunction) {
        V value = get(key);
        if (value != null) {
            return value;
        }
        
        lock.writeLock().lock();
        try {
            // Double-check after acquiring write lock
            value = get(key);
            if (value != null) {
                return value;
            }
            
            // Compute and cache the value
            value = computeFunction.apply(key);
            if (value != null) {
                put(key, value);
            }
            return value;
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    public void putIfAbsent(K key, V value) {
        lock.writeLock().lock();
        try {
            CacheEntry<V> entry = cache.get(key);
            if (entry == null || entry.isExpired()) {
                put(key, value);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    public V replace(K key, V newValue) {
        lock.writeLock().lock();
        try {
            CacheEntry<V> entry = cache.get(key);
            if (entry != null && !entry.isExpired()) {
                V oldValue = entry.getValue();
                put(key, newValue);
                return oldValue;
            }
            return null;
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    public boolean replace(K key, V oldValue, V newValue) {
        lock.writeLock().lock();
        try {
            CacheEntry<V> entry = cache.get(key);
            if (entry != null && !entry.isExpired()) {
                V currentValue = entry.getValue();
                if (currentValue != null && currentValue.equals(oldValue)) {
                    put(key, newValue);
                    return true;
                }
            }
            return false;
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    public Set<K> keySet() {
        lock.readLock().lock();
        try {
            Set<K> keys = new HashSet<>();
            for (Map.Entry<K, CacheEntry<V>> entry : cache.entrySet()) {
                if (!entry.getValue().isExpired()) {
                    keys.add(entry.getKey());
                }
            }
            return keys;
        } finally {
            lock.readLock().unlock();
        }
    }
    
    public Collection<V> values() {
        lock.readLock().lock();
        try {
            List<V> values = new ArrayList<>();
            for (CacheEntry<V> entry : cache.values()) {
                if (!entry.isExpired()) {
                    values.add(entry.getValue());
                }
            }
            return values;
        } finally {
            lock.readLock().unlock();
        }
    }
    
    // Eviction logic
    private void evict() {
        if (cache.isEmpty()) return;
        
        K keyToEvict = null;
        
        switch (evictionPolicy) {
            case LRU:
                keyToEvict = findLRUKey();
                break;
            case LFU:
                keyToEvict = findLFUKey();
                break;
            case FIFO:
                keyToEvict = insertionOrder.poll();
                break;
            case RANDOM:
                keyToEvict = findRandomKey();
                break;
        }
        
        if (keyToEvict != null) {
            cache.remove(keyToEvict);
            removeFromTracking(keyToEvict);
            stats.recordEviction();
        }
    }
    
    private K findLRUKey() {
        return accessOrder.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
    
    private K findLFUKey() {
        return frequencies.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
    
    private K findRandomKey() {
        List<K> keys = new ArrayList<>(cache.keySet());
        return keys.get(new Random().nextInt(keys.size()));
    }
    
    // Tracking updates
    private void updateAccessTracking(K key) {
        long currentTime = System.currentTimeMillis();
        accessOrder.put(key, currentTime);
        frequencies.merge(key, 1, Integer::sum);
    }
    
    private void updatePutTracking(K key) {
        long currentTime = System.currentTimeMillis();
        accessOrder.put(key, currentTime);
        frequencies.put(key, 1);
        
        if (evictionPolicy == EvictionPolicy.FIFO) {
            insertionOrder.offer(key);
        }
    }
    
    private void removeFromTracking(K key) {
        accessOrder.remove(key);
        frequencies.remove(key);
        insertionOrder.remove(key);
    }
    
    // Cleanup expired entries
    private void cleanupExpired() {
        lock.writeLock().lock();
        try {
            Iterator<Map.Entry<K, CacheEntry<V>>> iterator = cache.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<K, CacheEntry<V>> entry = iterator.next();
                if (entry.getValue().isExpired()) {
                    iterator.remove();
                    removeFromTracking(entry.getKey());
                }
            }
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    // Statistics and monitoring
    public CacheStats getStats() {
        return stats;
    }
    
    public void printStats() {
        System.out.println(stats.toString());
    }
    
    public Map<K, String> getEntryInfo() {
        lock.readLock().lock();
        try {
            Map<K, String> info = new HashMap<>();
            for (Map.Entry<K, CacheEntry<V>> entry : cache.entrySet()) {
                CacheEntry<V> cacheEntry = entry.getValue();
                String entryInfo = String.format(
                    "Value: %s, Created: %d, LastAccess: %d, Hits: %d, TTL: %d, Expired: %s",
                    cacheEntry.getValue(),
                    cacheEntry.getCreationTime(),
                    cacheEntry.getLastAccessTime(),
                    cacheEntry.getHitCount(),
                    cacheEntry.getTtl(),
                    cacheEntry.isExpired()
                );
                info.put(entry.getKey(), entryInfo);
            }
            return info;
        } finally {
            lock.readLock().unlock();
        }
    }
    
    // Shutdown
    public void shutdown() {
        cleanupExecutor.shutdown();
        try {
            if (!cleanupExecutor.awaitTermination(5, TimeUnit.SECONDS)) {
                cleanupExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            cleanupExecutor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
    
    // Demo usage
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Custom Cache System Demo ===\n");
        
        // Create cache with max size 3, default TTL 5000ms, LRU eviction
        CustomCache<String, String> cache = new CustomCache<>(3, 5000, EvictionPolicy.LRU);
        
        // Basic operations
        System.out.println("1. Basic Operations:");
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        System.out.println("Added 3 items");
        System.out.println("Get key1: " + cache.get("key1"));
        System.out.println("Get key2: " + cache.get("key2"));
        System.out.println("Cache size: " + cache.size());
        
        // Eviction test
        System.out.println("\n2. Eviction Test:");
        cache.put("key4", "value4"); // Should evict key3 (LRU)
        System.out.println("Added key4, should evict key3");
        System.out.println("Contains key3: " + cache.containsKey("key3"));
        System.out.println("Contains key4: " + cache.containsKey("key4"));
        
        // TTL test
        System.out.println("\n3. TTL Test:");
        cache.put("shortLived", "expires soon", 1000); // 1 second TTL
        System.out.println("Added shortLived with 1s TTL");
        System.out.println("Get shortLived: " + cache.get("shortLived"));
        Thread.sleep(1100);
        System.out.println("After 1.1s, Get shortLived: " + cache.get("shortLived"));
        
        // Compute function test
        System.out.println("\n4. Compute Function Test:");
        String computed = cache.getOrCompute("computed", key -> "computed_" + key);
        System.out.println("Computed value: " + computed);
        String cached = cache.getOrCompute("computed", key -> "should_not_compute");
        System.out.println("Cached value: " + cached);
        
        // Statistics
        System.out.println("\n5. Statistics:");
        cache.printStats();
        
        // Entry information
        System.out.println("\n6. Entry Information:");
        cache.getEntryInfo().forEach((key, info) -> 
            System.out.println(key + ": " + info));
        
        // Cleanup
        cache.shutdown();
        System.out.println("\nCache system shutdown complete.");
    }
}