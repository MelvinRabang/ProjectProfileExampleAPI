package com.doctorcrushaneapps.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.doctorcrushaneapps.dto.ProjectProfileDto;
import com.doctorcrushaneapps.entity.ProjectProfileEntity;
import com.doctorcrushaneapps.exception.DaoException;
import com.doctorcrushaneapps.exception.ServiceException;

@Repository
public interface ProjectProfileDao extends MongoRepository<ProjectProfileEntity, String>{

	public List<ProjectProfileDto> searchProjectProfile(ProjectProfileDto projectProfileDto,
			String queryStringForProjectProfileSearch) throws DaoException;
	
	public boolean doesProjectProfileExist(ProjectProfileDto profileProfileDto) throws DaoException;

	public ProjectProfileDto saveProjectProfile(ProjectProfileDto profileProfileDtoToBeSaved) throws DaoException;
	
//	public int deleteProjectProfile(ProjectProfileDto projectProfileToBeDeleted) throws DaoException;
	
	public int updateProjectProfile(ProjectProfileDto projectProfileToBeUpdated) throws DaoException;
}
