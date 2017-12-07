package com.doctorcrushaneapps.service;

import com.doctorcrushaneapps.dto.ProjectProfileDto;
import com.doctorcrushaneapps.exception.ServiceException;

public interface UpdateProjectProfileService {

	public boolean doesProjectProfileExist(ProjectProfileDto profileProfileDto) throws ServiceException;
	
	public ProjectProfileDto saveProjectProfile(ProjectProfileDto projectProfileDtoToBeSaved) throws ServiceException;
	
	public int deleteProjectProfile(ProjectProfileDto projectProfileToBeDeleted) throws ServiceException;
	
	public int updateProjectProfile(ProjectProfileDto projectProfileToBeUpdated) throws ServiceException;
}
