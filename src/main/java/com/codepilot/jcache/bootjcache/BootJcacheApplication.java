package com.codepilot.jcache.bootjcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;



@EnableJpaRepositories
@SpringBootApplication
//@EnableSwagger2
public class BootJcacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootJcacheApplication.class, args);
	}

}