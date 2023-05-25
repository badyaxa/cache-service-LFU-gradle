package com.epam.bohdanstasiv.cacheservice.storage;

import com.epam.bohdanstasiv.cacheservice.model.CacheEntry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheStorage {
    private final Map<String, CacheEntry> entries;
    private final Map<String, Integer> frequencies;

    /**
     * Constructs a new CacheStorage object.
     */
    public CacheStorage() {
        this.entries = new ConcurrentHashMap<String, CacheEntry>();
        this.frequencies = new ConcurrentHashMap<String, Integer>();
    }

    /**
     * Returns the map of cache entries.
     *
     * @return the map of cache entries
     */
    public Map<String, CacheEntry> getEntries() {
        return entries;
    }

    /**
     * Returns the map of cache frequencies.
     *
     * @return the map of cache frequencies
     */
    public Map<String, Integer> getCacheFrequencies() {
        return frequencies;
    }

    /**
     * Checks if the cache storage contains the specified value.
     *
     * @param value the value to check for existence in the cache storage
     * @return true if the value exists in the cache storage, false otherwise
     */
    public boolean contains(String value) {
        return entries.containsKey(value);
    }

    /**
     * Returns the size of the cache storage.
     *
     * @return the size of the cache storage
     */
    public int size() {
        return entries.size();
    }

    /**
     * Retrieves the cache entry for the specified value.
     *
     * @param value the value for which to retrieve the cache entry
     * @return the cache entry associated with the value, or null if not found
     */
    public CacheEntry getCacheEntries(String value) {
        return entries.get(value);
    }

    /**
     * Puts a new cache entry with the specified value into the cache storage.
     *
     * @param value the value of the cache entry
     */
    public void putEntry(String value) {
        entries.put(value, new CacheEntry(value));
        frequencies.put(value, 0);
    }

    /**
     * Removes the cache entry with the specified value from the cache storage.
     *
     * @param value the value of the cache entry to remove
     * @return
     */
    public CacheEntry removeEntry(String value) {
        frequencies.remove(value);
        return entries.remove(value);
    }
}
