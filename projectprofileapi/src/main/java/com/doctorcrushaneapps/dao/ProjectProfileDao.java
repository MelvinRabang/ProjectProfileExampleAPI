package com.doctorcrushaneapps.dao;

import java.util.List;

import com.doctorcrushaneapps.dto.ProjectProfileDto;
import com.doctorcrushaneapps.exception.DaoException;
import com.doctorcrushaneapps.exception.ServiceException;

public interface ProjectProfileDao {

	public List<ProjectProfileDto> searchProjectProfile(ProjectProfileDto projectProfileDto,
			String queryStringForProjectProfileSearch) throws DaoException;
	
	public boolean doesProjectProfileExist(ProjectProfileDto profileProfileDto) throws DaoException;

	public ProjectProfileDto saveProjectProfile(ProjectProfileDto profileProfileDtoToBeSaved) throws DaoException;
}
