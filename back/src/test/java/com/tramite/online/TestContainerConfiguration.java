package com.tramite.online;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestContainerConfiguration {

	/**Singleto Container 
	 */
	static  PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));
		
	@Bean
	@ServiceConnection
	PostgreSQLContainer<?> postgresContainer() {
	    return postgreSQLContainer;
	}

	

}
