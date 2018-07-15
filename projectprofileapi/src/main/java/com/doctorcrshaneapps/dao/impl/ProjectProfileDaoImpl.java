package com.doctorcrshaneapps.dao.impl;
import java.sql.ResultSet;	
import java.sql.SQLException;	
import java.util.HashMap;	
import java.util.List;	
import java.util.Map;	
	
import org.apache.log4j.Logger;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.dao.DataAccessException;	
import org.springframework.jdbc.core.JdbcTemplate;	
import org.springframework.jdbc.core.RowMapper;	
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;	
import org.springframework.stereotype.Repository;	
	
import com.doctorcrushaneapps.app.ProjectProfileSqlConfigureProperties;	
import com.doctorcrushaneapps.dao.ProjectProfileDao;	
import com.doctorcrushaneapps.dto.ProjectProfileDto;	
import com.doctorcrushaneapps.exception.DaoException;	
	
@Repository	
public class ProjectProfileDaoImpl implements ProjectProfileDao {	
	
    private final JdbcTemplate jdbcTemplate;	
    	
    @Autowired	
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;	
	
    @Autowired	
    public ProjectProfileDaoImpl(JdbcTemplate jdbcTemplate) {	
        this.jdbcTemplate = jdbcTemplate;	
    }	
	
    @Autowired	
    private ProjectProfileSqlConfigureProperties sqlProperties;	
	
    Logger LOGGER = Logger.getLogger(ProjectProfileDaoImpl.class);	
    	
	@Override	
	public List<ProjectProfileDto> searchProjectProfile(ProjectProfileDto projectProfileDto,	
			String queryStringForProjectProfileSearch)	
			throws DaoException {	
		LOGGER.info("searchProjectProfile() - START");	
		List<ProjectProfileDto> searchProjectProfileList = null;	
		try {	
			searchProjectProfileList = 	
					jdbcTemplate.query(queryStringForProjectProfileSearch, new ProjectProfileDtoSearchMapper());	
		} catch (DataAccessException e) {	
			throw new DaoException("ProjectProfileDaoImpl => searchProjectProfile()",	
					e.getCause().getMessage());	
		}	
		LOGGER.info("searchProjectProfile() - END");	
		return searchProjectProfileList;	
	}	
		
	@Override	
	public boolean doesProjectProfileExist(ProjectProfileDto profileProfileDto) throws DaoException {	
		LOGGER.info("doesProjectProfileExist() - START");	
		boolean doesProjectProfileExist = false;	
		ProjectProfileDto projectProfileRetrievedFromDB = null;	
		Map<String, String> projectProfileNamedParameters = putProjectProfileQueryParameters(profileProfileDto);	
		try {	
			projectProfileRetrievedFromDB =	
					(ProjectProfileDto) namedParameterJdbcTemplate.queryForObject(sqlProperties.getDoesProjectProfileExistQuery(),	
							projectProfileNamedParameters, new ProjectProfileDtoSearchMapper());	
		} catch (DataAccessException e) {	
			return doesProjectProfileExist;	
		}	
		doesProjectProfileExist = isProjectProfileExistInDB(projectProfileRetrievedFromDB);	
		LOGGER.info("doesProjectProfileExist() - END");	
		return doesProjectProfileExist;	
	}	
	
	@Override	
	public ProjectProfileDto saveProjectProfile(ProjectProfileDto profileProfileDtoToBeSaved) throws DaoException {	
		LOGGER.info("saveProjectProfile() - START");	
		Map<String, String> projectProfileNamedParameters = putProjectProfileQueryParameters(profileProfileDtoToBeSaved);	
		try {	
			namedParameterJdbcTemplate.update(sqlProperties.getSaveProjectProfileQuery(),	
							projectProfileNamedParameters);	
		} catch (DataAccessException e) {	
			throw new DaoException("ProjectProfileDaoImpl => saveProjectProfile()",	
					e.getCause().getMessage());	
		}	
		LOGGER.info("saveProjectProfile() - END");	
		return profileProfileDtoToBeSaved;	
	}	
		
	private class ProjectProfileDtoSearchMapper implements RowMapper<ProjectProfileDto> {	
	
		public ProjectProfileDto mapRow(ResultSet resultSet, int i) throws SQLException {	
	
			ProjectProfileDto searchProjectProfileDto = new ProjectProfileDto();	
			searchProjectProfileDto.setProjectProfileDeliveryLead(	
					resultSet.getString("DLVRY_LD_EID"));	
			searchProjectProfileDto.setProjectProfileIndustryGroup(	
					resultSet.getString("IND_GRP"));	
			searchProjectProfileDto.setProjectProfileName(	
					resultSet.getString("PRJ_NM"));	
			searchProjectProfileDto.setProjectProfileProjectLocation(	
					resultSet.getString("PRJCT_LOC"));	
			searchProjectProfileDto.setProjectProfileSeniorExec(	
					resultSet.getString("SNR_EXC_EID"));	
			searchProjectProfileDto.setProjectProfileSubTeamName(	
					resultSet.getString("SB_TM_NM"));	
			searchProjectProfileDto.setProjectProfileFirstPointContact(	
					resultSet.getString("FRST_CNTCT_EID"));	
			searchProjectProfileDto.setProjectProfileSecondPointContact(	
					resultSet.getString("SCND_CNTCT_EID"));	
			return searchProjectProfileDto;	
		}	
	}	
		
	private static Map<String, String> putProjectProfileQueryParameters(ProjectProfileDto profileProfileDto) {	
		Map<String, String> projectProfileNamedParameters = new HashMap<>();	
		projectProfileNamedParameters.put("projectname", profileProfileDto.getProjectProfileName());	
		projectProfileNamedParameters.put("subteamname", profileProfileDto.getProjectProfileSubTeamName());	
		projectProfileNamedParameters.put("industrygroup", profileProfileDto.getProjectProfileIndustryGroup());	
		projectProfileNamedParameters.put("seniorexecutive", profileProfileDto.getProjectProfileSeniorExec());	
		projectProfileNamedParameters.put("deliverylead", profileProfileDto.getProjectProfileDeliveryLead());	
		projectProfileNamedParameters.put("firstcontact", profileProfileDto.getProjectProfileFirstPointContact());	
		projectProfileNamedParameters.put("secondcontact", profileProfileDto.getProjectProfileSecondPointContact());	
		projectProfileNamedParameters.put("projectlocation", profileProfileDto.getProjectProfileProjectLocation());	
			
		return projectProfileNamedParameters;	
	}	
	
	private static boolean isProjectProfileExistInDB(ProjectProfileDto projectProfileRetrievedFromDB) {	
		return projectProfileRetrievedFromDB != null;	
	}	
	
	@Override	
	public int deleteProjectProfile(ProjectProfileDto projectProfileToBeDeleted) throws DaoException {	
		LOGGER.info("deleteProjectProfile() - START");	
		int rowsUpdated = 0;	
		Map<String, String> projectProfileNamedParameters = putProjectProfileQueryParameters(projectProfileToBeDeleted);	
		try {	
			rowsUpdated = namedParameterJdbcTemplate.update(sqlProperties.getDeleteProjectProfileQuery(), projectProfileNamedParameters);	
		} catch (DataAccessException e) {	
			throw new DaoException("ProjectProfileDaoImpl => deleteProjectProfile()",	
					e.getCause().getMessage());	
		}	
		LOGGER.info("deleteProjectProfile() - END");	
		return rowsUpdated;	
	}	
	
	@Override	
	public int updateProjectProfile(ProjectProfileDto projectProfileToBeUpdated) throws DaoException {	
		LOGGER.info("updateProjectProfile() - START");	
		int rowsUpdated = 0;	
		Map<String, String> projectProfileNamedParameters = putProjectProfileQueryParameters(projectProfileToBeUpdated);	
		try {	
			rowsUpdated = namedParameterJdbcTemplate.update(sqlProperties.getUpdateProjectProfileQuery(), projectProfileNamedParameters);	
		} catch (DataAccessException e) {	
			throw new DaoException("ProjectProfileDaoImpl => deleteProjectProfile()",	
					e.getCause().getMessage());	
		}	
		LOGGER.info("updateProjectProfile() - END");	
		return rowsUpdated;	
	}	
}
