package com.epam.bohdanstasiv.cacheservice;

import com.epam.bohdanstasiv.cacheservice.listener.LoggingRemovalListener;
import com.epam.bohdanstasiv.cacheservice.listener.RemovalListener;
import com.epam.bohdanstasiv.cacheservice.policy.EvictionPolicy;
import com.epam.bohdanstasiv.cacheservice.policy.LFUEvictionPolicy;
import com.epam.bohdanstasiv.cacheservice.service.CacheService;

/**
 * The main class to demonstrate the usage of the CacheService.
 */
public class Main {

    /**
     * The main method to run the cache service demo.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        RemovalListener removalListener = new LoggingRemovalListener();
        EvictionPolicy evictionPolicy = new LFUEvictionPolicy(100_000, removalListener);
        CacheService cacheCacheServiceLFU = new CacheService(evictionPolicy);

        cacheCacheServiceLFU.put("value1");
        cacheCacheServiceLFU.put("value2");

        String value1 = cacheCacheServiceLFU.get("value1");
        String value2 = cacheCacheServiceLFU.get("value2");

        System.out.println("Value 1: " + value1);
        System.out.println("Value 2: " + value2);
    }
}
