package com.epam.bohdanstasiv.cacheservice.policy;

import com.epam.bohdanstasiv.cacheservice.listener.RemovalListener;
import com.epam.bohdanstasiv.cacheservice.storage.CacheStorage;

/**
 * Least Frequently Used (LFU) Eviction Policy implementation.
 */
public class LFUEvictionPolicy implements EvictionPolicy {
    private final int maxSize;
    private final RemovalListener removalListener;

    /**
     * Constructs an LFU eviction policy with the specified maximum size.
     *
     * @param maxSize         the maximum size of the cache
     * @param removalListener
     */
    public LFUEvictionPolicy(int maxSize, RemovalListener removalListener) {
        this.maxSize = maxSize;
        this.removalListener = removalListener;
    }

    /**
     * Evicts cache entries based on the LFU policy.
     *
     * @param cacheStorage the cache storage to evict entries from
     * @return true if an entry was evicted, false otherwise
     */
    public boolean evict(CacheStorage cacheStorage) {
        while (cacheStorage.size() >= maxSize) {
            int minFrequency = Integer.MAX_VALUE;
            String evictValue = null;

            for (String value : cacheStorage.getCacheFrequencies().keySet()) {
                final int frequency = cacheStorage.getCacheFrequencies().get(value);
                if (frequency < minFrequency) {
                    minFrequency = frequency;
                    evictValue = value;
                }
            }

            if (evictValue != null) {
                removalListener.onRemoval(cacheStorage.removeEntry(evictValue));
                return true;
            }
        }
        return false;
    }
}
