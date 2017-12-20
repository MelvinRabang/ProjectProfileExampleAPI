package com.doctorcrushaneapps.controller.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.doctorcrushaneapps.dto.ProjectProfileDto;
import com.doctorcrushaneapps.exception.ServiceException;
import com.doctorcrushaneapps.service.SearchProjectProfileService;;

@RunWith(SpringRunner.class)
public class ProjectProfileControllerTest {

	SearchProjectProfileService searchProjectProfileService = mock(SearchProjectProfileService.class);
	ProjectProfileDto searchProjectProfile;
	List<ProjectProfileDto> projectProfileList;
	
	@Before
	public void setupService() {
		searchProjectProfile = this.createProjectProfile();
		projectProfileList = this.createProjectProfileList();
	}
	
	@Test
	public void testSearchProjectProfiles() throws ServiceException {
		 when(searchProjectProfileService.searchProjectProfile(searchProjectProfile)).thenReturn(projectProfileList);
		 assertEquals(projectProfileList, searchProjectProfileService.searchProjectProfile(searchProjectProfile));
	}
	
	@Test
	public void testSaveProjectProfiles() {

	}
	
	@Test
	public void doesProjectProfileExist() {

	}
	
	@Test
	public void deleteProjectProfile() {

	}
	
	@Test
	public void updateProjectProfile() {

	}
	
	private ProjectProfileDto createProjectProfile() {
		ProjectProfileDto projectProfileDto = new ProjectProfileDto();
		projectProfileDto.setProjectProfileDeliveryLead("Renan Caparas");
		projectProfileDto.setProjectProfileFirstPointContact("Mardolfh DelRosario");
		projectProfileDto.setProjectProfileIndustryGroup("Retail");
		projectProfileDto.setProjectProfileName("Tech Sustainment");
		projectProfileDto.setProjectProfileProjectLocation("Taguig City");
		projectProfileDto.setProjectProfileSecondPointContact("Elmer Astronomo");
		projectProfileDto.setProjectProfileSeniorExec("Margaret Ang");
		projectProfileDto.setProjectProfileSubTeamName("Team one");
		return projectProfileDto;
	}
	
	private List<ProjectProfileDto> createProjectProfileList() {
		List<ProjectProfileDto> projectProfileList = new ArrayList<>();
		ProjectProfileDto projectProfileDto = this.createProjectProfile();
		projectProfileList.add(projectProfileDto);
		return projectProfileList;
	}
}
