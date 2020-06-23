package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	//TODO remove nocache in application.properties

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
