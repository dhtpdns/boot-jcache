package com.codepilot.jcache.bootjcache.repository;

import com.codepilot.jcache.bootjcache.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
