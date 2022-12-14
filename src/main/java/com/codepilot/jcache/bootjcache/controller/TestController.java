package com.codepilot.jcache.bootjcache.controller;

import com.codepilot.jcache.bootjcache.entity.User;
import com.codepilot.jcache.bootjcache.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@AllArgsConstructor
public class TestController {
    
    private final UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity getUser(@PathVariable("userId") String userId) throws Exception {
        User user = userService.getUser(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    @GetMapping(value = "/usercache/{userId}")
    public ResponseEntity getUserCache(@PathVariable("userId") String userId) throws Exception {
        return new ResponseEntity<>(userService.getUserByCache(userId), HttpStatus.OK);
    }
    
    @PostMapping(value = "/usercache")
    public ResponseEntity putUserCache(@RequestBody User user) throws Exception {
    	userService.putUserCache(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/usercache/evic/{userId}")
    public ResponseEntity evicUserCache(@PathVariable("userId") String userId) throws Exception {
        return new ResponseEntity<>(userService.evicUserCache(userId),HttpStatus.OK);
    }
    
    @PostMapping(value = "/user")
    public ResponseEntity updUser(@RequestBody User user){
        user = userService.updUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/user/{userId}")
    public ResponseEntity delUser(@PathVariable("userId") String userId){
         userService.delUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
