package com.doctorcrushaneapps.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.doctorcrushaneapps.dao.ProjectProfileDao;
import com.doctorcrushaneapps.dto.ProjectProfileDto;
import com.doctorcrushaneapps.exception.DaoException;

@Repository
public class ProjectProfileDaoImpl implements ProjectProfileDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProjectProfileDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
	
	@Override
	public ProjectProfileDto searchProjectProfile(ProjectProfileDto projectProfileDto,
			String queryStringForProjectProfileSearch)
			throws DaoException {
		ProjectProfileDto searchProjectProfileDto = null;
		try {
			jdbcTemplate.queryForObject(queryStringForProjectProfileSearch, new ProjectProfileDtoSearchMapper());
		} catch (DataAccessException e) {
			throw new DaoException("ProjectProfileDaoImpl => searchProjectProfile()",
					e.getCause().getMessage());
		}
		return searchProjectProfileDto;
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
			return searchProjectProfileDto;
		}
	}
}
