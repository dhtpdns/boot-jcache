package com.codepilot.jcache.bootjcache.util;

import com.codepilot.jcache.bootjcache.entity.User;
import com.codepilot.jcache.bootjcache.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements InitializingBean {

    @Autowired
    UserRepository userRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        User user = new User();
        user.setUserName("test1");
        user.setPassword("password1");
        user.setAge(21);
        user.setProfileName("Test User 1");
        user.setCity("Toronto");
        user.setCountry("Canada");
        user.setPostalCode("M2N 8P3");
        userRepository.save(user);

        User user2 = new User();
        user2.setUserName("test2");
        user2.setPassword("password1");
        user2.setAge(21);
        user2.setProfileName("Test User 2");
        user2.setCity("Winnipeg");
        user2.setCountry("Canada");
        user2.setPostalCode("R2C 0V4");
        userRepository.save(user2);
    }
}
