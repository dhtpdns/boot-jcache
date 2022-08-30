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
        user.setUserName("hwang");
        user.setPassword("password1");
        user.setAge(31);
        user.setProfileName("황상필바보");
        user.setCity("서울");
        user.setCountry("잠실");
        user.setPostalCode("234234");
        userRepository.save(user);

        User user2 = new User();
        user2.setUserName("ose");
        user2.setPassword("password1");
        user2.setAge(21);
        user2.setProfileName("오세운 천재");
        user2.setCity("서울");
        user2.setCountry("당산");
        user2.setPostalCode("39482");
        userRepository.save(user2);
    }
}
