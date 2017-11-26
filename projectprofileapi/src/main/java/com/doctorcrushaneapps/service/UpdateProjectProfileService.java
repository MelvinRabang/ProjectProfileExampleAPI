package com.doctorcrushaneapps.service;

import com.doctorcrushaneapps.dto.ProjectProfileDto;
import com.doctorcrushaneapps.exception.ServiceException;

public interface UpdateProjectProfileService {

	public boolean doesProjectProfileExist(ProjectProfileDto profileProfileDto) throws ServiceException;
	
	public ProjectProfileDto saveProjectProfile(ProjectProfileDto profileProfileDtoToBeSaved) throws ServiceException;
}
