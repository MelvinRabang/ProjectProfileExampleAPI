package com.doctorcrushaneapps.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.doctorcrushaneapps.controller.ProjectProfileController;

@SpringBootApplication
@ComponentScan(basePackages = {"com.doctorcrushaneapps.controller"})
public class ProjectProfileApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectProfileApiApplication.class, args);
	}
}
