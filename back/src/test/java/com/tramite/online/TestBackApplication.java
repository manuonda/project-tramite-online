package com.tramite.online;

import org.springframework.boot.SpringApplication;

public class TestBackApplication {

	public static void main(String[] args) {
		SpringApplication.from(BackApplication::main).with(ContainerConfiguration.class).run(args);
	}

}
