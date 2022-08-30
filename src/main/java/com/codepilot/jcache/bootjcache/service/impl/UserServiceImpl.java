package com.codepilot.jcache.bootjcache.service.impl;

import com.codepilot.jcache.bootjcache.entity.User;
import com.codepilot.jcache.bootjcache.repository.UserRepository;
import com.codepilot.jcache.bootjcache.service.UserService;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.cache.annotation.CachePut;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "User")
public class UserServiceImpl implements UserService {

	Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserRepository userRepository;

	@Override
	@Cacheable(key = "#userId")
	public User getUser(String userId) throws Exception {
		logger.info("Fetching the user {}", userId);
		Optional<User> user = Optional.ofNullable(userRepository.findById(userId).orElseThrow(NoSuchElementException::new));
		logger.info("Successfully fetched user {}", user.toString());
		return user.get();
	}

	@Override
	// @CachePut(cacheName = "User")
	@Cacheable(key = "#entity.userName")
	public User updUser(User entity) {
		logger.info("Fetching the user {}", entity);
		User user = userRepository.save(entity);
		logger.info("Successfully fetched user {}", user.toString());
		return user;
	}

	@Override
	@CacheEvict(key = "#userId")
	public void delUser(String userId) {
		userRepository.deleteById(userId);
		logger.info("Successfully del user {}", userId);

	}
    
}
