package com.flight.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

	@Value("${bookMyFlight.openapi.dev-url}")
	private String devUrl;

	@Value("${bookMyFlight.openapi.prod-url}")
	private String prodUrl;

	@Bean
	public OpenAPI myOpenAPI() {
		Server devServer = new Server();
		devServer.setUrl(devUrl);
		devServer.setDescription("Server URL in Development environment");

		Server prodServer = new Server();
		prodServer.setUrl(prodUrl);
		prodServer.setDescription("Server URL in Production environment");

		Contact contact = new Contact();
		contact.setEmail("bookMyFlight@gmail.com");
		contact.setName("bookMyFlight");
		contact.setUrl("https://www.bookMyFlight.com");

		License mitLicense = new License()
				.name("MIT License")
				.url("https://choosealicense.com/licenses/mit/");

		Info info = new Info()
				.title("bookMyFlight APIs")
				.version("1.0")
				.contact(contact)
				.description("This service exposes REST API endpoints to manage BookMyFlight application.")
				.license(mitLicense);

		return new OpenAPI()
				.info(info)
				.servers(List.of(devServer, prodServer));
	}
}