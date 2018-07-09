package com.doctorcrushaneapps.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ProjectProfiles")
public class ProjectProfileDto {

	@Id
	private String id;
	private String projectProfileName;
	private String projectProfileSubTeamName;
	private String projectProfileDeliveryLead;
	private String projectProfileProjectLocation;
	private String projectProfileIndustryGroup;
	private String projectProfileSeniorExec;
	private String projectProfileFirstPointContact;
	private String projectProfileSecondPointContact;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectProfileName() {
		return projectProfileName;
	}
	
	public void setProjectProfileName(String projectProfileName) {
		this.projectProfileName = projectProfileName;
	}
	
	public String getProjectProfileSubTeamName() {
		return projectProfileSubTeamName;
	}
	
	public void setProjectProfileSubTeamName(String projectProfileSubTeamName) {
		this.projectProfileSubTeamName = projectProfileSubTeamName;
	}
	
	public String getProjectProfileDeliveryLead() {
		return projectProfileDeliveryLead;
	}
	
	public void setProjectProfileDeliveryLead(String projectProfileDeliveryLead) {
		this.projectProfileDeliveryLead = projectProfileDeliveryLead;
	}
	
	public String getProjectProfileProjectLocation() {
		return projectProfileProjectLocation;
	}
	
	public void setProjectProfileProjectLocation(String projectProfileProjectLocation) {
		this.projectProfileProjectLocation = projectProfileProjectLocation;
	}
	
	public String getProjectProfileIndustryGroup() {
		return projectProfileIndustryGroup;
	}
	
	public void setProjectProfileIndustryGroup(String projectProfileIndustryGroup) {
		this.projectProfileIndustryGroup = projectProfileIndustryGroup;
	}
	
	public String getProjectProfileSeniorExec() {
		return projectProfileSeniorExec;
	}
	
	public void setProjectProfileSeniorExec(String projectProfileSeniorExec) {
		this.projectProfileSeniorExec = projectProfileSeniorExec;
	}

	public String getProjectProfileFirstPointContact() {
		return projectProfileFirstPointContact;
	}

	public void setProjectProfileFirstPointContact(String projectProfileFirstPointContact) {
		this.projectProfileFirstPointContact = projectProfileFirstPointContact;
	}

	public String getProjectProfileSecondPointContact() {
		return projectProfileSecondPointContact;
	}

	public void setProjectProfileSecondPointContact(String projectProfileSecondPointContact) {
		this.projectProfileSecondPointContact = projectProfileSecondPointContact;
	}	
}