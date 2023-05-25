package com.epam.bohdanstasiv.cacheservice.policy;

import com.epam.bohdanstasiv.cacheservice.storage.CacheStorage;

/**
 * Interface representing an eviction policy for cache storage.
 */
public interface EvictionPolicy {

    /**
     * Evicts an item from the cache storage based on the implemented policy.
     *
     * @param cacheStorage the cache storage to apply the eviction policy on
     * @return true if an item was evicted, false otherwise
     */
    boolean evict(CacheStorage cacheStorage);
}
