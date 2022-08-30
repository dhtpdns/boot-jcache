package com.codepilot.jcache.bootjcache.cache;

import java.util.Collection;

import javax.annotation.Resource;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CacheAgent {
	Logger logger = LoggerFactory.getLogger(CacheAgent.class);
	
	@Resource
	private CacheManager cacheManager;
	
	public Collection<String> getCacheNames(){
		logger.info("cacheNames({})",cacheManager.getCacheNames());	
		return cacheManager.getCacheNames();
	}
	
	public boolean cacheEvic(String cacheName,String key) {
		return cacheManager.getCache(cacheName).evictIfPresent(key);
	}
	
	public <T>  Object putCacheValue(String cacheName,String key,Object obj) {
		Cache cache =  cacheManager.getCache(cacheName);
		cache.put(key, obj);
		logger.info("Successfully cache put key({}) Obj({})",key, obj);
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	public <T>  Object getCacheValue(T calzz, String cacheName,String userId) {
		Cache cache =  cacheManager.getCache(cacheName);
		Cache.ValueWrapper valueWrapper = cache.get(userId);
		T obj =  (T) valueWrapper.get();
		logger.info("Successfully cache get user {}", obj);
		return obj;
	}
}