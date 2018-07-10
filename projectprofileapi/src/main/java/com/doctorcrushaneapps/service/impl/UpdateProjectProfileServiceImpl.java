package com.doctorcrushaneapps.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctorcrushaneapps.controller.ProjectProfileController;
import com.doctorcrushaneapps.dao.ProjectProfileDao;
import com.doctorcrushaneapps.dto.ProjectProfileDto;
import com.doctorcrushaneapps.exception.DaoException;
import com.doctorcrushaneapps.exception.ServiceException;
import com.doctorcrushaneapps.service.UpdateProjectProfileService;

@Service
public class UpdateProjectProfileServiceImpl implements UpdateProjectProfileService {

	@Autowired
	ProjectProfileDao projectProfileDao;
	
	Logger LOGGER = Logger.getLogger(UpdateProjectProfileServiceImpl.class);
	
	@Override
	public boolean doesProjectProfileExist(ProjectProfileDto profileProfileDto) throws ServiceException {
		LOGGER.info("doesProjectProfileExist() - START");
		boolean doesProjectProfileExist = false;
		try {
			doesProjectProfileExist = this.projectProfileDao.doesProjectProfileExist(profileProfileDto);
		} catch (DaoException e) {
			throw new ServiceException("Service Exception - doesProjectProfileExist() =>", e.getErrorCode());
		}
		LOGGER.info("doesProjectProfileExist() - END");
		return doesProjectProfileExist;
	}

	@Override
	public ProjectProfileDto saveProjectProfile(ProjectProfileDto profileProfileDtoToBeSaved) throws ServiceException {
		LOGGER.info("saveProjectProfile() - START");
		ProjectProfileDto savedProjectProfileDto = null;
		try {
			savedProjectProfileDto = this.projectProfileDao.saveProjectProfile(profileProfileDtoToBeSaved);
		} catch (DaoException e) {
			throw new ServiceException("Service Exception - updateProjectProfile() =>", e.getErrorCode());
		}
		LOGGER.info("saveProjectProfile() - END");
		return savedProjectProfileDto;
	}

//	@Override
//	public int deleteProjectProfile(ProjectProfileDto projectProfileToBeDeleted) throws ServiceException {
//		LOGGER.info("deleteProjectProfile() - START");
//		int rowsUpdated = 0;
//		try {
//			rowsUpdated = this.projectProfileDao.deleteProjectProfile(projectProfileToBeDeleted);
//		} catch (DaoException e) {
//			throw new ServiceException("Service Exception - deleteProjectProfile() =>", e.getErrorCode());
//		}
//		LOGGER.info("deleteProjectProfile() - END");
//		return rowsUpdated;
//	}

	@Override
	public int updateProjectProfile(ProjectProfileDto projectProfileToBeUpdated) throws ServiceException {
		LOGGER.info("updateProjectProfile() - START");
		int rowsUpdated = 0;
		try {
			rowsUpdated = this.projectProfileDao.updateProjectProfile(projectProfileToBeUpdated);
		} catch (DaoException e) {
			throw new ServiceException("Service Exception - updateProjectProfile() =>", e.getErrorCode());
		}
		LOGGER.info("updateProjectProfile() - END");
		return rowsUpdated;
	}

	@Override
	public int deleteProjectProfile(ProjectProfileDto projectProfileToBeDeleted) throws ServiceException {
		// TODO Auto-generated method stub
		return 0;
	}

}
