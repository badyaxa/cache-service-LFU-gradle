package com.epam.bohdanstasiv.cacheservice.listener;

import com.epam.bohdanstasiv.cacheservice.model.CacheEntry;

import java.util.logging.Logger;

public class LoggingRemovalListener implements RemovalListener {
    private static final Logger logger = Logger.getLogger(LoggingRemovalListener.class.getName());

    @Override
    public void onRemoval(CacheEntry cacheEntry) {
        logger.info("Cache entry removed: Value = " + cacheEntry.getValue());
    }
}
