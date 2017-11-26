package com.doctorcrushaneapps.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.doctorcrushaneapps.dao.ProjectProfileDao;
import com.doctorcrushaneapps.dto.ProjectProfileDto;
import com.doctorcrushaneapps.exception.ServiceException;
import com.doctorcrushaneapps.service.UpdateProjectProfileService;

public class UpdateProjectProfileServiceImpl implements UpdateProjectProfileService {

	@Autowired
	ProjectProfileDao projectProfileDao;
	
	@Override
	public boolean doesProjectProfileExist(ProjectProfileDto profileProfileDto) throws ServiceException {
		return false;
	}

	@Override
	public ProjectProfileDto saveProjectProfile(ProjectProfileDto profileProfileDtoToBeSaved) throws ServiceException {
		return null;
	}

}
