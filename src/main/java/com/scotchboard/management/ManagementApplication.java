package com.scotchboard.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.scotchboard.management.util.Generator;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ManagementApplication implements CommandLineRunner {
	
	@Autowired
	private Generator generator;
	
	public static void main(String[] args) {
		SpringApplication.run(ManagementApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		generator.generate();
	}
}
