package com.doctorcrushaneapps.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctorcrushaneapps.dao.ProjectProfileDao;
import com.doctorcrushaneapps.dto.ProjectProfileDto;
import com.doctorcrushaneapps.exception.DaoException;
import com.doctorcrushaneapps.exception.ServiceException;
import com.doctorcrushaneapps.service.UpdateProjectProfileService;

@Service
public class UpdateProjectProfileServiceImpl implements UpdateProjectProfileService {

	@Autowired
	ProjectProfileDao projectProfileDao;
	
	@Override
	public boolean doesProjectProfileExist(ProjectProfileDto profileProfileDto) throws ServiceException {
		boolean doesProjectProfileExist = false;
		try {
			doesProjectProfileExist = this.projectProfileDao.doesProjectProfileExist(profileProfileDto);
		} catch (DaoException e) {
			throw new ServiceException("Service Exception - doesProjectProfileExist() =>", e.getErrorCode());
		}
		return doesProjectProfileExist;
	}

	@Override
	public ProjectProfileDto saveProjectProfile(ProjectProfileDto profileProfileDtoToBeSaved) throws ServiceException {
		ProjectProfileDto savedProjectProfileDto = null;
		int rowsUpdated = 0;
		try {
			savedProjectProfileDto = this.projectProfileDao.saveProjectProfile(profileProfileDtoToBeSaved);
		} catch (DaoException e) {
			throw new ServiceException("Service Exception - updateProjectProfile() =>", e.getErrorCode());
		}
		return savedProjectProfileDto;
	}

	@Override
	public int deleteProjectProfile(ProjectProfileDto projectProfileToBeDeleted) throws ServiceException {
		int rowsUpdated = 0;
		try {
			rowsUpdated = this.projectProfileDao.deleteProjectProfile(projectProfileToBeDeleted);
		} catch (DaoException e) {
			throw new ServiceException("Service Exception - deleteProjectProfile() =>", e.getErrorCode());
		}
		return rowsUpdated;
	}

	@Override
	public int updateProjectProfile(ProjectProfileDto projectProfileToBeUpdated) throws ServiceException {
		int rowsUpdated = 0;
		try {
			rowsUpdated = this.projectProfileDao.updateProjectProfile(projectProfileToBeUpdated);
		} catch (DaoException e) {
			throw new ServiceException("Service Exception - updateProjectProfile() =>", e.getErrorCode());
		}
		return rowsUpdated;
	}

}
