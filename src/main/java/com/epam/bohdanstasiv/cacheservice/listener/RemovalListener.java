package com.epam.bohdanstasiv.cacheservice.listener;

import com.epam.bohdanstasiv.cacheservice.model.CacheEntry;

/**
 * Interface for a removal listener that is notified when a cache entry is removed.
 */
public interface RemovalListener {

    /**
     * This method is called when a cache entry is removed.
     *
     * @param cacheEntry The cache entry that was removed.
     */
    void onRemoval(CacheEntry cacheEntry);
}
