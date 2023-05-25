package com.epam.bohdanstasiv.cacheservice.model;

/**
 * Represents an entry in a cache.
 */
public class CacheEntry {
    private final String value;

    /**
     * Constructs a new CacheEntry with the specified value.
     *
     * @param value the value to be stored in the cache entry
     */
    public CacheEntry(String value) {
        this.value = value;
    }

    /**
     * Retrieves the value stored in this cache entry.
     *
     * @return the value stored in the cache entry
     */
    public String getValue() {
        return value;
    }
}
