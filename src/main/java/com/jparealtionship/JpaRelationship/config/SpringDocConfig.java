package com.jparealtionship.JpaRelationship.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

	@Bean
	public GroupedOpenApi controllerApi() {
		return GroupedOpenApi.builder().group("jpa-relationship-api").packagesToScan("com.jparealtionship.JpaRelationship")
				.build();
	}

}
