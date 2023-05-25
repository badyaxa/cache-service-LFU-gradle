package com.epam.bohdanstasiv.cacheservice.service;

import com.epam.bohdanstasiv.cacheservice.model.CacheEntry;
import com.epam.bohdanstasiv.cacheservice.policy.EvictionPolicy;
import com.epam.bohdanstasiv.cacheservice.statistics.CacheStatistics;
import com.epam.bohdanstasiv.cacheservice.storage.CacheStorage;

public class CacheService {
    private final CacheStorage storage;
    private final EvictionPolicy evictionPolicy;
    private final CacheStatistics cacheStatistics;

    /**
     * Constructs a CacheService object with the specified eviction policy and removal listener.
     *
     * @param evictionPolicy the eviction policy to be used by the cache service
     */
    public CacheService(EvictionPolicy evictionPolicy) {
        this.storage = new CacheStorage();
        this.evictionPolicy = evictionPolicy;
        this.cacheStatistics = new CacheStatistics();
    }

    /**
     * Retrieves the value associated with the specified key from the cache.
     *
     * @param value the key used to retrieve the value from the cache
     * @return the value associated with the key, or null if the key is not present in the cache
     */
    public String get(String value) {
        CacheEntry entry = storage.getCacheEntries(value);
        if (entry != null) {
            cacheStatistics.incrementHitCount();
            final Integer frequency = storage.getCacheFrequencies().get(value);
            storage.getCacheFrequencies().put(value, frequency + 1);
            return entry.getValue();
        }
        cacheStatistics.incrementMissCount();
        return null;
    }

    /**
     * Puts the specified value into the cache.
     * If the cache is full and eviction is necessary, the eviction policy will be applied.
     *
     * @param value the value to be put into the cache
     */
    public void put(String value) {
        String existInStorage = get(value);
        if (existInStorage == null) {
            if (evictionPolicy.evict(storage)) {
                cacheStatistics.incrementEvictionCount();
            }
            storage.putEntry(value);
        }
    }
}
