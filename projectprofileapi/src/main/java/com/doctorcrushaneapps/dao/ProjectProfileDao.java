package com.doctorcrushaneapps.dao;

import com.doctorcrushaneapps.dto.ProjectProfileDto;
import com.doctorcrushaneapps.exception.DaoException;

public interface ProjectProfileDao {

	public ProjectProfileDto searchProjectProfile(ProjectProfileDto projectProfileDto,
			String queryStringForProjectProfileSearch) throws DaoException;
}
