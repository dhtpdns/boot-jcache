package com.codepilot.jcache.bootjcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories
@SpringBootApplication
public class BootJcacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootJcacheApplication.class, args);
	}

}