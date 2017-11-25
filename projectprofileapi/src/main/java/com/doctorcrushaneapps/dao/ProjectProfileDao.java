package com.doctorcrushaneapps.dao;

import java.util.List;

import com.doctorcrushaneapps.dto.ProjectProfileDto;
import com.doctorcrushaneapps.exception.DaoException;

public interface ProjectProfileDao {

	public List<ProjectProfileDto> searchProjectProfile(ProjectProfileDto projectProfileDto,
			String queryStringForProjectProfileSearch) throws DaoException;
}
