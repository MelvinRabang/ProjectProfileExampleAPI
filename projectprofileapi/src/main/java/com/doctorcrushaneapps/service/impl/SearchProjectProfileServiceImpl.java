package com.doctorcrushaneapps.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctorcrushaneapps.dao.ProjectProfileDao;
import com.doctorcrushaneapps.dto.ProjectProfileDto;
import com.doctorcrushaneapps.exception.DaoException;
import com.doctorcrushaneapps.exception.ServiceException;
import com.doctorcrushaneapps.service.SearchProjectProfileService;

@Service
public class SearchProjectProfileServiceImpl implements SearchProjectProfileService {

	@Autowired
	ProjectProfileDao projectProfileDao;

	@Override
	public List<ProjectProfileDto> searchProjectProfile(ProjectProfileDto projectProfileDto)
			throws ServiceException {
		String queryStringForProjectProfileSearch =
				this.deriveQueryStringForProjectProfileSearch(projectProfileDto);
		List<ProjectProfileDto> projectProfileList = null;
		try {
			projectProfileList = projectProfileDao.searchProjectProfile(projectProfileDto, queryStringForProjectProfileSearch);
		} catch (DaoException e) {
			throw new ServiceException("Error in SearchProjectProfileService => searchProjectProfile()",
				e.getErrorCode());
		}
		return projectProfileList;
	}

	private String deriveQueryStringForProjectProfileSearch(ProjectProfileDto projectProfileDto) {
		StringBuilder queryStringForProjProfSearch = new StringBuilder();
		queryStringForProjProfSearch.append("SELECT * FROM PRJCT_PROFL WHERE");
		
		if (projProfileVariableNotNullAndNotEmpty(
				projectProfileDto.getProjectProfileDeliveryLead())) {
			queryStringForProjProfSearch.append(" DLVRY_LD_EID LIKE '%" +
				projectProfileDto.getProjectProfileDeliveryLead() + "%' ");
		}
		if (projProfileVariableNotNullAndNotEmpty(
				projectProfileDto.getProjectProfileIndustryGroup())) {
			queryStringForProjProfSearch.append(" IND_GRP LIKE '%" +
					projectProfileDto.getProjectProfileIndustryGroup() + "%' ");
		}
		if (projProfileVariableNotNullAndNotEmpty(
				projectProfileDto.getProjectProfileName())) {
			queryStringForProjProfSearch.append(" PRJ_NM LIKE '%" +
					projectProfileDto.getProjectProfileName() + "%' ");
		}
		if (projProfileVariableNotNullAndNotEmpty(
				projectProfileDto.getProjectProfileProjectLocation())) {
			queryStringForProjProfSearch.append(" PRJCT_LOC LIKE '%" +
					projectProfileDto.getProjectProfileProjectLocation() + "%' ");
		}
		if (projProfileVariableNotNullAndNotEmpty(
				projectProfileDto.getProjectProfileSeniorExec())) {
			queryStringForProjProfSearch.append(" SNR_EXC_EID LIKE '%" +
					projectProfileDto.getProjectProfileSeniorExec() + "%' ");
		}
		if (projProfileVariableNotNullAndNotEmpty(
				projectProfileDto.getProjectProfileSubTeamName())) {
			queryStringForProjProfSearch.append(" SB_TM_NM LIKE '%" +
					projectProfileDto.getProjectProfileSeniorExec() + "%' ");
		}
		return queryStringForProjProfSearch.toString();
	}
	
	private boolean projProfileVariableNotNullAndNotEmpty(String projProfileVariable) {
		return null != projProfileVariable && !projProfileVariable.isEmpty();
	}
}
