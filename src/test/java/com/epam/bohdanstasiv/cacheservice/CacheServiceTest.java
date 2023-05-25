package com.epam.bohdanstasiv.cacheservice;

import com.epam.bohdanstasiv.cacheservice.listener.LoggingRemovalListener;
import com.epam.bohdanstasiv.cacheservice.listener.RemovalListener;
import com.epam.bohdanstasiv.cacheservice.policy.EvictionPolicy;
import com.epam.bohdanstasiv.cacheservice.policy.LFUEvictionPolicy;
import com.epam.bohdanstasiv.cacheservice.service.CacheService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CacheServiceTest {
    private CacheService cacheService;

    @BeforeEach
    public void setUp() {
        RemovalListener removalListener = new LoggingRemovalListener();
        EvictionPolicy evictionPolicy = new LFUEvictionPolicy(3, removalListener);
        cacheService = new CacheService(evictionPolicy);
    }

    @Test
    public void testPutAndGet() {
        cacheService.put("value1");
        cacheService.put("value2");
        cacheService.put("value3");

        String value11 = cacheService.get("value1");
        assertEquals("value1", value11);

        String value2 = cacheService.get("value2");
        assertEquals("value2", value2);

        String value3 = cacheService.get("value3");
        assertEquals("value3", value3);

        String value4 = cacheService.get("value4");
        assertNull(value4);
    }

    @Test
    public void testEviction() {
        cacheService.put("value1");
        cacheService.put("value2");
        cacheService.put("value3");

        String value11 = cacheService.get("value1");
        assertEquals("value1", value11);

        String value21 = cacheService.get("value2");
        assertEquals("value2", value21);
        String value22 = cacheService.get("value2");
        assertEquals("value2", value22);
        String value23 = cacheService.get("value2");
        assertEquals("value2", value23);

        String value31 = cacheService.get("value3");
        assertEquals("value3", value31);
        String value32 = cacheService.get("value3");
        assertEquals("value3", value32);

        String value41 = cacheService.get("value4");
        assertNull(value41);

        cacheService.put("value4");
        value11 = cacheService.get("value1");
        assertNull(value11);
    }
}
