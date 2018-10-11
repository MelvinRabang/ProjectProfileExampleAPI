package com.doctorcrushaneapps.dao;

import java.util.List;

import com.doctorcrushaneapps.dto.ProjectProfileDto;
import com.doctorcrushaneapps.exception.DaoException;

public interface ProjectProfileDao {

	public List<ProjectProfileDto> searchProjectProfile(ProjectProfileDto projectProfileDto,
			String queryStringForProjectProfileSearch) throws DaoException;
	
	public ProjectProfileDto saveProjectProfile(ProjectProfileDto profileProfileDtoToBeSaved) throws DaoException;
	
	public int deleteProjectProfile(ProjectProfileDto projectProfileToBeDeleted) throws DaoException;
	
	public int updateProjectProfile(ProjectProfileDto projectProfileToBeUpdated) throws DaoException;
}
