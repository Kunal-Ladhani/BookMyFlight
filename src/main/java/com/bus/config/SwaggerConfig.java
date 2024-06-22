package com.bus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getInfo())
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();

	}

	private ApiInfo getInfo() {
		return new ApiInfo("BookMyBus REST APIs",
				"REST API for BookMyBus - a Trip Management System created using Java, Spring Boot, Hibernate, Maven for my capstone project.",
				"1.0",
				"",
				new Contact("Kunal Ladhani", "https://kunal-ladhani.github.io/", "k.ladhani1@gmail.com"),
				"",
				"",
				Collections.EMPTY_LIST);
	}
}
