package com.doctorcrushaneapps.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.doctorcrushaneapps.dto.ProjectProfileDto;

@RestController
public class ProjectProfileController {

	@RequestMapping(value="/searchProjectProfiles", method=RequestMethod.GET)
	@ResponseBody
	public ProjectProfileDto searchProjectProfiles(ProjectProfileDto searchProjectProfile) {
		return this.createDummyProjectProfile();
	}
	
	private ProjectProfileDto createDummyProjectProfile() {
		ProjectProfileDto projectProfileDto = new ProjectProfileDto();

		projectProfileDto.setProjectProfileName("Tech sustainment");
		projectProfileDto.setProjectProfileSubTeamName("Assortment");
		projectProfileDto.setProjectProfileDeliveryLead("Renan Caparas");
		projectProfileDto.setProjectProfileProjectLocation("Taguig city");
		projectProfileDto.setProjectProfileIndustryGroup("Retail");
		projectProfileDto.setProjectProfileSeniorExec("Margaret Ang");
		return projectProfileDto;
	}
}