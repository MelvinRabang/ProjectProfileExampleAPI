package com.doctorcrushaneapps.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.doctorcrushaneapps.controller.ProjectProfileController;

@SpringBootApplication
@ComponentScan(basePackages = {"com.doctorcrushaneapps.controller",
		"com.doctorcrushaneapps.service", "com.doctorcrushaneapps.dao", 
		"com.doctorcrushaneapps.app"})
public class ProjectProfileApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectProfileApiApplication.class, args);
	}
}
