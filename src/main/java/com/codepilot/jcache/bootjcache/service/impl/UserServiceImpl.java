package com.codepilot.jcache.bootjcache.service.impl;

import com.codepilot.jcache.bootjcache.cache.CacheAgent;
import com.codepilot.jcache.bootjcache.cache.enums.CacheConst;
import com.codepilot.jcache.bootjcache.cache.enums.CacheConst.CacheNames;
import com.codepilot.jcache.bootjcache.entity.User;
import com.codepilot.jcache.bootjcache.repository.UserRepository;
import com.codepilot.jcache.bootjcache.service.UserService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	//Logger logger = LoggerFactory.getLogger(UserService.class);

	//@Autowired
	final UserRepository userRepository;
	
	final CacheAgent cacheAgent;
	
	@PostConstruct
    public void init() {
		  cacheAgent.getCacheNames();
    }
		
	
	
	@Override
	@Cacheable(key = "#userId")
	public User getUser(String userId) throws Exception {
		log.info("Fetching the user {}", userId);
		Optional<User> user = Optional.ofNullable(userRepository.findById(userId).orElseThrow(NoSuchElementException::new));
		log.info("Successfully fetched user {}", user.toString());
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
    

	@Override
	public User getUserByCache(String userId) {
		User user = (User) cacheAgent.getCacheValue(User.class,CacheNames.USER.name(), userId);
		return user;
	}
	
	@Override
	public void putUserCache(User user) {
		cacheAgent.putCacheValue(CacheNames.USER.name(),user.getUserName(),user);
		//acheAgent.getCacheValue(User.class,CacheNames.USER.name(), user.getUserName()); TEST
	}
	
	@Override
	public boolean evicUserCache(String userId) {
		return cacheAgent.cacheEvic(CacheNames.USER.name(),userId);
	}
	
}
