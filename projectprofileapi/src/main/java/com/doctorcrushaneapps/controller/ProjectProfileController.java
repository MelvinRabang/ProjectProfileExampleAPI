package com.doctorcrushaneapps.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doctorcrushaneapps.dao.ProjectProfileDao;
import com.doctorcrushaneapps.dto.ProjectProfileDto;
import com.doctorcrushaneapps.exception.ControllerException;
import com.doctorcrushaneapps.exception.ServiceException;
import com.doctorcrushaneapps.service.SearchProjectProfileService;
import com.doctorcrushaneapps.service.UpdateProjectProfileService;

@CrossOrigin
@RestController
@RequestMapping(value="/api/projectprofiles")
public class ProjectProfileController {

	@Autowired
	ProjectProfileDao projectProfileDao;

	@Autowired
	SearchProjectProfileService searchProjectProfileService;

	@Autowired
	UpdateProjectProfileService updateProjectProfileService;

	Logger LOGGER = Logger.getLogger(ProjectProfileController.class);

	@GetMapping
	public List<ProjectProfileDto> searchProjectProfiles(@RequestParam(name="query") String searchProjectQuery)
			throws ControllerException{
		LOGGER.info("searchProjectProfiles() - START");
		List<ProjectProfileDto> projectProfileList = null;
		try {
			projectProfileList = searchProjectProfileService.searchProjectProfile(searchProjectQuery);
		} catch (ServiceException e) {
			throw new ControllerException("Controller Exception - searchProjectProfiles() => ", e.getErrorCode());
		}
		LOGGER.info("searchProjectProfiles() - END");
		return projectProfileList;
	}

	@RequestMapping(method=RequestMethod.POST)
	public ProjectProfileDto saveProjectProfiles(@RequestBody ProjectProfileDto projectProfileDtoToBeSaved)
			throws ControllerException {
		LOGGER.info("saveProjectProfiles() - START");
		ProjectProfileDto finalProjectProfileDto = null;
		try {
			finalProjectProfileDto = updateProjectProfileService.saveProjectProfile(projectProfileDtoToBeSaved);
		} catch (ServiceException e) {
			throw new ControllerException("Controller Exception - saveProjectProfiles() => ", e.getErrorCode());
		}
		LOGGER.info("saveProjectProfiles() - END");
		return finalProjectProfileDto;
	}

	@RequestMapping(method=RequestMethod.DELETE)
	public int deleteProjectProfile(@RequestBody ProjectProfileDto projectProfileDto)
			throws ControllerException {
		LOGGER.info("deleteProjectProfile() - START");
		int rowsUpdated = 0;
		try {
			rowsUpdated = updateProjectProfileService.deleteProjectProfile(projectProfileDto);
		} catch (ServiceException e) {
			throw new ControllerException("Controller Exception - deleteProjectProfile() => ", e.getErrorCode());
		}
		LOGGER.info("deleteProjectProfile() - END");
		return rowsUpdated;
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public int updateProjectProfile(@RequestBody ProjectProfileDto projectProfileDto)
			throws ControllerException {
		LOGGER.info("updateProjectProfile() - START");
		int rowsUpdated = 0;
		try {
			rowsUpdated = updateProjectProfileService.updateProjectProfile(projectProfileDto);
		} catch (ServiceException e) {
			throw new ControllerException("Controller Exception - updateProjectProfile() => ", e.getErrorCode());
		}
		LOGGER.info("updateProjectProfile() - END");
		return rowsUpdated;
	}
}