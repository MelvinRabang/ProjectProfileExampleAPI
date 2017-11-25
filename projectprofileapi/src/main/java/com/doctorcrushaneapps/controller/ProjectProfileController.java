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

@RestController
public class ProjectProfileController {

	@Autowired
	SearchProjectProfileService searchProjectProfileService;
	
	@RequestMapping(value="/api/searchProjectProfiles", method=RequestMethod.POST)
	public List<ProjectProfileDto> searchProjectProfiles(@RequestBody ProjectProfileDto searchProjectProfile)
			throws ControllerException{
		List<ProjectProfileDto> projectProfileList = null;
		try {
			projectProfileList = searchProjectProfileService.searchProjectProfile(searchProjectProfile);
		} catch (ServiceException e) {
			throw new ControllerException("Controller Exception() => ", e.getErrorCode());
		}
		return projectProfileList;
	}
}