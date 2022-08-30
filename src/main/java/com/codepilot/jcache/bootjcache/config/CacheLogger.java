package com.codepilot.jcache.bootjcache.config;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheLogger implements CacheEventListener<Object, Object> {
	Logger logger = LoggerFactory.getLogger(CacheLogger.class);

	@Override
	public void onEvent(CacheEvent<?, ?> cacheEvent) {	
		logger.info("Key: {} | EventType: {} | Old value: {} | New value: {}", cacheEvent.getKey(), cacheEvent.getType(),
				cacheEvent.getOldValue(), cacheEvent.getNewValue());
	}

//	@Override
//	public void onEvent(CacheEvent<? extends Object, ? extends Object> event) {
//		// TODO Auto-generated method stub
//		
//	}

}