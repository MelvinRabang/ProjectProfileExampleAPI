package com.doctorcrushaneapps.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
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

	Logger LOGGER = Logger.getLogger(SearchProjectProfileServiceImpl.class);
	
	@Override
	public List<ProjectProfileDto> searchProjectProfile(String searchProfileQuery)
			throws ServiceException {
		LOGGER.info("searchProjectProfile() - START");
		ProjectProfileDto projectProfileDto = 
				this.deriveProjectProfileDtoFromQuery(searchProfileQuery);
		String queryStringForProjectProfileSearch =
				this.deriveQueryStringForProjectProfileSearch(projectProfileDto);
		List<ProjectProfileDto> projectProfileList = null;
		try {
			projectProfileList = projectProfileDao.searchProjectProfile(projectProfileDto, queryStringForProjectProfileSearch);
		} catch (DaoException e) {
			throw new ServiceException("Error in SearchProjectProfileService => searchProjectProfile()",
				e.getErrorCode());
		}
		LOGGER.info("searchProjectProfile() - END");
		return projectProfileList;
	}

	private ProjectProfileDto deriveProjectProfileDtoFromQuery(String searchProfileQuery) {
		ProjectProfileDto profileDto = new ProjectProfileDto();
		Map<String, String> searchQueryMap = createSearchQueryMap(searchProfileQuery);
		setProfileDtoUsingSearchQueryMap(profileDto, searchQueryMap);
		return profileDto;
	}

	private void setProfileDtoUsingSearchQueryMap(ProjectProfileDto profileDto, Map<String, String> searchQueryMap) {
		if (searchQueryMap.get("deliverylead") != null) {
			profileDto.setProjectProfileDeliveryLead(searchQueryMap.get("deliveryLead"));
		}
		if (searchQueryMap.get("firstpointcontact") != null) {
			profileDto.setProjectProfileFirstPointContact(searchQueryMap.get("firstpointcontact"));
		}
		if (searchQueryMap.get("industrygroup") != null) {
			profileDto.setProjectProfileIndustryGroup(searchQueryMap.get("industrygroup"));
		}
		if (searchQueryMap.get("profilename") != null) {
			profileDto.setProjectProfileName(searchQueryMap.get("profilename"));
		}
		if (searchQueryMap.get("projectlocation") != null) {
			profileDto.setProjectProfileProjectLocation(searchQueryMap.get("projectlocation"));
		}
		if (searchQueryMap.get("secondpointcontact") != null) {
			profileDto.setProjectProfileSecondPointContact(searchQueryMap.get("secondpointcontact"));
		}
		if (searchQueryMap.get("seniorexec") != null) {
			profileDto.setProjectProfileSeniorExec(searchQueryMap.get("seniorexec"));
		}
		if (searchQueryMap.get("subteamname") != null) {
			profileDto.setProjectProfileSubTeamName(searchQueryMap.get("subteamname"));
		}
	}

	private Map<String, String> createSearchQueryMap(String searchProfileQuery) {
		Map<String, String> searchQueryMap = new HashMap<String, String>();
		for(String keyValue : searchProfileQuery.split(",")) {
		   String[] pairs = keyValue.split("=");
		   searchQueryMap.put(pairs[0], pairs[1]);
		}
		return searchQueryMap;
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
