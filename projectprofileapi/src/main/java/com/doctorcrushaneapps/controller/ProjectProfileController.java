package com.doctorcrushaneapps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.doctorcrushaneapps.dto.ProjectProfileDto;
import com.doctorcrushaneapps.exception.ControllerException;
import com.doctorcrushaneapps.exception.ServiceException;
import com.doctorcrushaneapps.service.SearchProjectProfileService;
import com.doctorcrushaneapps.service.UpdateProjectProfileService;

@RestController
public class ProjectProfileController {

	@Autowired
	SearchProjectProfileService searchProjectProfileService;
	
	@Autowired
	UpdateProjectProfileService updateProjectProfileService;

	@RequestMapping(value="/api/searchProjectProfiles", method=RequestMethod.POST)
	public List<ProjectProfileDto> searchProjectProfiles(@RequestBody ProjectProfileDto searchProjectProfile)
			throws ControllerException{
		List<ProjectProfileDto> projectProfileList = null;
		try {
			projectProfileList = searchProjectProfileService.searchProjectProfile(searchProjectProfile);
		} catch (ServiceException e) {
			throw new ControllerException("Controller Exception - searchProjectProfiles() => ", e.getErrorCode());
		}
		return projectProfileList;
	}
	
	@RequestMapping(value="/api/saveProjectProfiles", method=RequestMethod.POST)
	public ProjectProfileDto saveProjectProfiles(@RequestBody ProjectProfileDto projectProfileDtoToBeSaved) 
			throws ControllerException {
		ProjectProfileDto finalProjectProfileDto = null;
		try {
			finalProjectProfileDto = updateProjectProfileService.saveProjectProfile(projectProfileDtoToBeSaved);
		} catch (ServiceException e) {
			throw new ControllerException("Controller Exception - saveProjectProfiles() => ", e.getErrorCode());
		}
		return finalProjectProfileDto;
	}
	
	@RequestMapping(value="/api/isProjectProfileExist", method=RequestMethod.POST)
	public boolean doesProjectProfileExist(@RequestBody ProjectProfileDto projectProfileDto) 
			throws ControllerException {
		boolean isProjectProfileExist = false;
		try {
			isProjectProfileExist = updateProjectProfileService.doesProjectProfileExist(projectProfileDto);
		} catch (ServiceException e) {
			throw new ControllerException("Controller Exception - doesProjectProfileExist() => ", e.getErrorCode());
		}
		return isProjectProfileExist;
	}
}