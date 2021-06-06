package com.jyellow.tp2api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class PropertyServiceForJasyptStarter {
	
	@Value("${spring.datasource.url}")
	private String springDataSourceUrl;
	
	@Value("${spring.datasource.username}")
	private String springDatasourceUsername;
	
	@Value("${spring.datasource.password}")
	private String springDatasourcePassword;
	
	@Value("${spring.mail.username}")
	private String springMailUsername;
	
	@Value("${spring.mail.password}")
	private String springMailPassword;
	
	@Value("${admin.password}")
	private String adminPassword;

	public String getSpringDataSourceUrl() {
		return springDataSourceUrl;
	}

	public String getSpringDatasourceUsername() {
		return springDatasourceUsername;
	}
	
	public String getSpringDatasourcePassword() {
		return springDatasourcePassword;
	}
	
	public String getSpringMailUsername() {
		return springMailUsername;
	}
	
	public String getSpringMailPassword() {
		return springMailPassword;
	}
	
	public String getAdminPassword() {
		return adminPassword;
	}

	public String getSpringDataSourceUrlUsingEnvironment(Environment environment) {
		return environment.getProperty("spring.datasource.url");
	}
	
	public String getSpringDatasourceUsernameUsingEnvironment(Environment environment) {
		return environment.getProperty("spring.datasource.username");
	}
	
	public String getSpringDatasourcePasswordUsingEnvironment(Environment environment) {
		return environment.getProperty("spring.datasource.password");
	}
	
	public String getSpringMailUsernameUsingEnvironment(Environment environment) {
		return environment.getProperty("spring.mail.username");
	}
	
	public String getSpringMailPasswordUsingEnvironment(Environment environment) {
		return environment.getProperty("spring.mail.password");
	}
	
	public String getAdminPasswordUsingEnvironment(Environment environment) {
		return environment.getProperty("admin.password");
	}
}
