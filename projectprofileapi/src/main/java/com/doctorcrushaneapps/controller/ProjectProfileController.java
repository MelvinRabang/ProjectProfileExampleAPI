package com.doctorcrushaneapps.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.doctorcrushaneapps.dao.ProjectProfileDao;
import com.doctorcrushaneapps.dto.ProjectProfileDto;
import com.doctorcrushaneapps.exception.ControllerException;
import com.doctorcrushaneapps.exception.ServiceException;
import com.doctorcrushaneapps.service.SearchProjectProfileService;
import com.doctorcrushaneapps.service.UpdateProjectProfileService;

@RestController
@RequestMapping(value="/projectProfiles")
public class ProjectProfileController {

	@Autowired
	ProjectProfileDao projectProfileDao;

	@Autowired
	SearchProjectProfileService searchProjectProfileService;

	@Autowired
	UpdateProjectProfileService updateProjectProfileService;

	Logger LOGGER = Logger.getLogger(ProjectProfileController.class);

	@RequestMapping(value="/searchProjectProfiles", method=RequestMethod.POST)
	public List<ProjectProfileDto> searchProjectProfiles(@RequestBody ProjectProfileDto searchProjectProfile)
			throws ControllerException{
		LOGGER.info("searchProjectProfiles() - START");
		List<ProjectProfileDto> projectProfileList = null;
		try {
			projectProfileList = searchProjectProfileService.searchProjectProfile(searchProjectProfile);
		} catch (ServiceException e) {
			throw new ControllerException("Controller Exception - searchProjectProfiles() => ", e.getErrorCode());
		}
		LOGGER.info("searchProjectProfiles() - END");
		return projectProfileList;
	}

	@RequestMapping(value="/saveProjectProfiles", method=RequestMethod.POST)
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

	@RequestMapping(value="/isProjectProfileExist", method=RequestMethod.POST)
	public boolean doesProjectProfileExist(@RequestBody ProjectProfileDto projectProfileDto)
			throws ControllerException {
		LOGGER.info("isProjectProfileExist() - START");
		boolean isProjectProfileExist = false;
		try {
			isProjectProfileExist = updateProjectProfileService.doesProjectProfileExist(projectProfileDto);
		} catch (ServiceException e) {
			throw new ControllerException("Controller Exception - doesProjectProfileExist() => ", e.getErrorCode());
		}
		LOGGER.info("isProjectProfileExist() - END");
		return isProjectProfileExist;
	}

	@RequestMapping(value="/projectProfile", method=RequestMethod.DELETE)
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

	@RequestMapping(value="/updateProjectProfile", method=RequestMethod.PUT)
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