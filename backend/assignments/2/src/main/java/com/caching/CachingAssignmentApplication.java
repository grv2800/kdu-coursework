package com.caching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CachingAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(CachingAssignmentApplication.class, args);
	}

}
