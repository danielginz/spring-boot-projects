package com.cache.cache_spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableCaching
@EnableScheduling
@SpringBootApplication
public class CacheSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheSpringBootApplication.class, args);
	}

}

//create DB in MySql