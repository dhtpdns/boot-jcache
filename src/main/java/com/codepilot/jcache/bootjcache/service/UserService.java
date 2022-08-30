package com.codepilot.jcache.bootjcache.service;

import java.util.List;

import com.codepilot.jcache.bootjcache.entity.User;

public interface UserService {
    User getUser(String userId) throws Exception;
    
    User updUser(User user);
    
    void delUser(String userId);
    
    void putUserCache(User user);
    
    boolean evicUserCache(String userId);
    
    User getUserByCache(String userId);
   
}
