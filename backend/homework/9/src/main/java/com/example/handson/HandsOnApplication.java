package com.example.handson;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class HandsOnApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(HandsOnApplication.class).profiles("dev").run(args);
	}

}
