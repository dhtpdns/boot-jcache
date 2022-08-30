package com.codepilot.jcache.bootjcache.service.impl;

import com.codepilot.jcache.bootjcache.cache.CacheAgent;
import com.codepilot.jcache.bootjcache.cache.enums.CacheConst;
import com.codepilot.jcache.bootjcache.cache.enums.CacheConst.CacheNames;
import com.codepilot.jcache.bootjcache.entity.User;
import com.codepilot.jcache.bootjcache.repository.UserRepository;
import com.codepilot.jcache.bootjcache.service.UserService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "User")
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

	//Logger logger = LoggerFactory.getLogger(UserService.class);

	//@Autowired
	final UserRepository userRepository;
	
	final CacheAgent cacheAgent;
	
	//@Value("${project.name}")
	//private String aaa;
	

	@Override
	@Cacheable(key = "#userId")
	public User getUser(String userId) throws Exception {
		log.info("Fetching the user {}", userId);
		Optional<User> user = Optional.ofNullable(userRepository.findById(userId).orElseThrow(NoSuchElementException::new));
		log.info("Successfully fetched user {}", user.toString());
		
		//log.info("PROJECT_NAME {}", aaa);
		return user.get();
	}

	@Override
	// @CachePut(cacheName = "User")
	@Cacheable(key = "#entity.userName")
	public User updUser(User entity) {
		log.info("Fetching the user {}", entity);
		User user = userRepository.save(entity);
		log.info("Successfully fetched user {}", user.toString());
		return user;
	}

	@Override
	@CacheEvict(key = "#userId")
	public void delUser(String userId) {
		userRepository.deleteById(userId);
		log.info("Successfully del user {}", userId);

	}
    
	
//	@Override
//	public User getCacheValue(String cacheName,String userId) {
//		Cache cache =  cacheManager.getCache(com.codepilot.jcache.bootjcache.config.CacheConfig.USER_CACHE);
//		//Cache<String, User> userCache = cacheManager.getCache(com.codepilot.jcache.bootjcache.config.CacheConfig.USER_CACHE, String.class, User.class);
//		
//		Cache.ValueWrapper valueWrapper = cache.get(userId);
//		User user = (User) valueWrapper.get();
//		 
//		logger.info("Successfully cache get user {}", user);
//		return user;
//	}
	
	
	// @Override
	@Override
	public User getUserByCache(String userId) {
		User user = (User) cacheAgent.getCacheValue(User.class,CacheNames.USER.name(), userId);
		return user;
	}
	
	@Override
	public void putUserCache(User user) {
		cacheAgent.putCacheValue(CacheNames.USER.name(),user.getUserName(),user);
		
		cacheAgent.getCacheValue(User.class,CacheNames.USER.name(), user.getUserName());
	}
	
	@Override
	public boolean evicUserCache(String userId) {
		return cacheAgent.cacheEvic(CacheNames.USER.name(),userId);
	}
	

	@PostConstruct
    public void init() {
		  cacheAgent.getCacheNames();
    }
	
//	//@Override
//	@SuppressWarnings("unchecked")
//	public <T>  Object getCacheValue(T calzz, String cacheName,String userId) {
//		Cache cache =  cacheManager.getCache(cacheName);
//		Cache.ValueWrapper valueWrapper = cache.get(userId);
//		T obj =  (T) valueWrapper.get();
//		logger.info("Successfully cache get user {}", obj);
//		return obj;
//	}
	
}
