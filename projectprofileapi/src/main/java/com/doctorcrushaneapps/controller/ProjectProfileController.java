package com.doctorcrushaneapps.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.doctorcrushaneapps.dto.ProjectProfileDto;

@RestController
public class ProjectProfileController {

	@RequestMapping(value="/api/searchProjectProfiles", method=RequestMethod.GET)
	@ResponseBody
	public List<ProjectProfileDto> searchProjectProfiles(ProjectProfileDto searchProjectProfile) {
		return this.createDummyProjectProfile();
	}
	
	private List<ProjectProfileDto> createDummyProjectProfile() {
		List<ProjectProfileDto> dummyProjectProfileList = new ArrayList<>();
		ProjectProfileDto projectProfileDto = new ProjectProfileDto();

		projectProfileDto.setProjectProfileName("Tech sustainment");
		projectProfileDto.setProjectProfileSubTeamName("Assortment");
		projectProfileDto.setProjectProfileDeliveryLead("Renan Caparas");
		projectProfileDto.setProjectProfileProjectLocation("Taguig city");
		projectProfileDto.setProjectProfileIndustryGroup("Retail");
		projectProfileDto.setProjectProfileSeniorExec("Margaret Ang");
		dummyProjectProfileList.add(projectProfileDto);
		return dummyProjectProfileList;
	}
}