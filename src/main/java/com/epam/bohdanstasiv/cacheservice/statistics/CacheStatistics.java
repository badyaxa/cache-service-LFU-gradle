package com.epam.bohdanstasiv.cacheservice.statistics;

/**
 * Represents statistics for a cache.
 */
public class CacheStatistics {
    private int hitCount;
    private int missCount;
    private int evictionCount;

    /**
     * Constructs a new CacheStatistics object with initial counts set to zero.
     */
    public CacheStatistics() {
        this.hitCount = 0;
        this.missCount = 0;
        this.evictionCount = 0;
    }

    /**
     * Increments the hit count by 1.
     */
    public void incrementHitCount() {
        hitCount++;
    }

    /**
     * Increments the miss count by 1.
     */
    public void incrementMissCount() {
        missCount++;
    }

    /**
     * Increments the eviction count by 1.
     */
    public void incrementEvictionCount() {
        evictionCount++;
    }
}
