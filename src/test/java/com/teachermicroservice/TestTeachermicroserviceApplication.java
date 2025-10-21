package com.teachermicroservice;

import org.springframework.boot.SpringApplication;

public class TestTeachermicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.from(TeachermicroserviceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
