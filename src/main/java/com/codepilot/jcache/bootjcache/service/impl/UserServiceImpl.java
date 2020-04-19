package com.codepilot.jcache.bootjcache.service.impl;

import com.codepilot.jcache.bootjcache.entity.User;
import com.codepilot.jcache.bootjcache.repository.UserRepository;
import com.codepilot.jcache.bootjcache.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
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
    public User getUser(String userId) {
        logger.info("Fetching the user {}", userId);
        User user = userRepository.getOne(userId);
        logger.info("Successfully fetched user {}", user.toString());
        return user;
    }
}
