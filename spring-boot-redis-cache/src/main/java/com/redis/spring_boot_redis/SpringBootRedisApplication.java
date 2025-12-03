package com.redis.spring_boot_redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootRedisApplication {
	public static void main(String[] args) {
		//System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication.run(SpringBootRedisApplication.class, args);
	}

}
