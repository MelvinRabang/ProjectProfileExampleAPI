package com.doctorcrushaneapps.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.doctorcrushaneapps.dao.ProjectProfileDao;
import com.doctorcrushaneapps.dto.ProjectProfileDto;
import com.doctorcrushaneapps.exception.DaoException;
import com.doctorcrushaneapps.exception.ServiceException;

@Repository
public class ProjectProfileDaoImpl implements ProjectProfileDao {

    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ProjectProfileDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String DOES_PROJECT_PROFILE_EXIST_QUERY = 
    		"SELECT * FROM PRJCT_PROFL " +
    	    "WHERE PRJ_NM = :projectname " +
    		"AND SB_TM_NM = :subteamname " +
    		"AND IND_GRP = :industrygroup " +
    		"AND SNR_EXC_EID = :seniorexecutive " +
    		"AND DLVRY_LD_EID = :deliverylead " +
    		"AND FRST_CNTCT_EID = :firstcontact " +
    		"AND SCND_CNTCT_EID = :secondcontact " +
    		"AND PRJCT_LOC = :projectlocation";
    
    private static final String SAVE_PROJECT_PROFILE_QUERY = 
    		"INSERT INTO PRJCT_PROFL " +
    		"(PRJ_NM, SB_TM_NM, IND_GRP, SNR_EXC_EID, DLVRY_LD_EID, " +
    		"FRST_CNTCT_EID, SCND_CNTCT_EID, PRJCT_LOC) VALUES " +
    	    "(:projectname, :subteamname, :industrygroup, :seniorexecutive, " +
    	    ":deliverylead, :firstcontact, :secondcontact, :projectlocation)";
    
    private static final String DELETE_PROJECT_PROFILE_QUERY = 
    		"DELETE FROM PRJCT_PROFL " +
    	    "WHERE PRJ_NM = :projectname " +
    		"AND SB_TM_NM = :subteamname " +
    		"AND IND_GRP = :industrygroup " +
    		"AND SNR_EXC_EID = :seniorexecutive " +
    		"AND DLVRY_LD_EID = :deliverylead " +
    		"AND FRST_CNTCT_EID = :firstcontact " +
    		"AND SCND_CNTCT_EID = :secondcontact " +
    		"AND PRJCT_LOC = :projectlocation";
    
    private static final String UPDATE_PROJECT_PROFILE_QUERY = 
    		"UPDATE PRJCT_PROFL " +
    		"SET SB_TM_NM = :subteamname ," +
    		"IND_GRP = :industrygroup ," +
    		"SNR_EXC_EID = :seniorexecutive ," +
    		"DLVRY_LD_EID = :deliverylead ," +
    		"FRST_CNTCT_EID = :firstcontact ," +
    		"SCND_CNTCT_EID = :secondcontact ," +
    		"PRJCT_LOC = :projectlocation " +
    		"WHERE PRJ_NM = :projectname ";

	@Override
	public List<ProjectProfileDto> searchProjectProfile(ProjectProfileDto projectProfileDto,
			String queryStringForProjectProfileSearch)
			throws DaoException {
		List<ProjectProfileDto> searchProjectProfileList = null;
		try {
			searchProjectProfileList = 
					jdbcTemplate.query(queryStringForProjectProfileSearch, new ProjectProfileDtoSearchMapper());
		} catch (DataAccessException e) {
			throw new DaoException("ProjectProfileDaoImpl => searchProjectProfile()",
					e.getCause().getMessage());
		}
		return searchProjectProfileList;
	}
	
	@Override
	public boolean doesProjectProfileExist(ProjectProfileDto profileProfileDto) throws DaoException {
		boolean doesProjectProfileExist = false;
		ProjectProfileDto projectProfileRetrievedFromDB = null;
		Map<String, String> projectProfileNamedParameters = putProjectProfileQueryParameters(profileProfileDto);
		try {
			projectProfileRetrievedFromDB =
					(ProjectProfileDto) namedParameterJdbcTemplate.queryForObject(DOES_PROJECT_PROFILE_EXIST_QUERY,
							projectProfileNamedParameters, new ProjectProfileDtoSearchMapper());
		} catch (DataAccessException e) {
			return doesProjectProfileExist;
		}
		doesProjectProfileExist = isProjectProfileExistInDB(projectProfileRetrievedFromDB);
		return doesProjectProfileExist;
	}

	@Override
	public ProjectProfileDto saveProjectProfile(ProjectProfileDto profileProfileDtoToBeSaved) throws DaoException {
		Map<String, String> projectProfileNamedParameters = putProjectProfileQueryParameters(profileProfileDtoToBeSaved);
		try {
			namedParameterJdbcTemplate.update(SAVE_PROJECT_PROFILE_QUERY,
							projectProfileNamedParameters);
		} catch (DataAccessException e) {
			throw new DaoException("ProjectProfileDaoImpl => saveProjectProfile()",
					e.getCause().getMessage());
		}
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
		int rowsUpdated = 0;
		Map<String, String> projectProfileNamedParameters = putProjectProfileQueryParameters(projectProfileToBeDeleted);
		try {
			rowsUpdated = namedParameterJdbcTemplate.update(DELETE_PROJECT_PROFILE_QUERY, projectProfileNamedParameters);
		} catch (DataAccessException e) {
			throw new DaoException("ProjectProfileDaoImpl => deleteProjectProfile()",
					e.getCause().getMessage());
		}
		return rowsUpdated;
	}

	@Override
	public int updateProjectProfile(ProjectProfileDto projectProfileToBeUpdated) throws DaoException {
		int rowsUpdated = 0;
		Map<String, String> projectProfileNamedParameters = putProjectProfileQueryParameters(projectProfileToBeUpdated);
		try {
			rowsUpdated = namedParameterJdbcTemplate.update(UPDATE_PROJECT_PROFILE_QUERY, projectProfileNamedParameters);
		} catch (DataAccessException e) {
			throw new DaoException("ProjectProfileDaoImpl => deleteProjectProfile()",
					e.getCause().getMessage());
		}
		return rowsUpdated;
	}
}
