package com.doctorcrushaneapps.app;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:sql.properties")
@ConfigurationProperties(prefix = "sql")
public class ProjectProfileSqlConfigureProperties {

	private String saveProjectProfileQuery;
	private String deleteProjectProfileQuery;
	private String updateProjectProfileQuery;
	
	public String getSaveProjectProfileQuery() {
		return saveProjectProfileQuery;
	}
	
	public void setSaveProjectProfileQuery(String saveProjectProfileQuery) {
		this.saveProjectProfileQuery = saveProjectProfileQuery;
	}
	
	public String getDeleteProjectProfileQuery() {
		return deleteProjectProfileQuery;
	}
	
	public void setDeleteProjectProfileQuery(String deleteProjectProfileQuery) {
		this.deleteProjectProfileQuery = deleteProjectProfileQuery;
	}
	
	public String getUpdateProjectProfileQuery() {
		return updateProjectProfileQuery;
	}
	
	public void setUpdateProjectProfileQuery(String updateProjectProfileQuery) {
		this.updateProjectProfileQuery = updateProjectProfileQuery;
	}
}
