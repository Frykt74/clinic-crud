package com.clinik.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ClinicCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicCrudApplication.class, args);
	}

}
