package com.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.lms.config.RsaKeyProperties;
// import com.lms.config.RsaKeyStringProperties;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

//import io.swagger.v3.oas.models.security.SecurityScheme.In;
//import io.swagger.v3.oas.annotations.security.SecurityScheme.In;
@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
@OpenAPIDefinition(info = @Info(title = "Library Management System", version = "v1", description = "System for tracking books"), servers = {
		@Server(url = "http://localhost:8080", description = "development server"),
		@Server(url = "http://localhost:8081", description = "testing server") }, security = @SecurityRequirement(name = "bearerAuth"))
@SecurityScheme(name = "bearerAuth", // Name of the security scheme
		type = SecuritySchemeType.HTTP, // HTTP authentication type
		scheme = "bearer", // Bearer token scheme
		bearerFormat = "JWT", // Optional: specify JWT format (can be omitted)
		in = SecuritySchemeIn.HEADER, // Define token in header
		description = "Authorization Bearer Token" // Description of the token
)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
