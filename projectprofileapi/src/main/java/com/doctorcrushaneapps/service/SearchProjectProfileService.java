package com.doctorcrushaneapps.service;

import com.doctorcrushaneapps.dto.ProjectProfileDto;
import com.doctorcrushaneapps.exception.ServiceException;

public interface SearchProjectProfileService {

	public ProjectProfileDto searchProjectProfile(ProjectProfileDto projectProfileDto) throws ServiceException;
}
