package com.doctorcrushaneapps.service;

import java.util.List;

import com.doctorcrushaneapps.dto.ProjectProfileDto;
import com.doctorcrushaneapps.exception.ServiceException;

public interface SearchProjectProfileService {

	public List<ProjectProfileDto> searchProjectProfile(String searchProfileQuery) throws ServiceException;
}
