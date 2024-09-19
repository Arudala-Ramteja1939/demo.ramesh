package com.example.ramesh.demo.ramesh;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "spring boot blog app Rest APIs",
                                description = "Spring boot Blog App Rest APIs Documentation",
                                version = "v1.0",
                                contact = @Contact(
                                		name = "Ramteja",
                                		email = "ramteja1939@gmail.com",
                                		url="hhtps://www.javaguides.net/license"),
                                license = @License(
                                		name = "Apache 2.0",
                                		url = "https://www.javaguides.net/license")),
							externalDocs = @ExternalDocumentation(description = "Spring boot Blog App Rest APIs Documentation",
							url = "http://github.com/RameshMF/springbooot-blog-rest-api"))
public class Application {


	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

	@Bean 
	public static  PasswordEncoder encry()
	{
		return new BCryptPasswordEncoder();

	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		//		System.out.println(encry().encode("ramteja"));
		//		System.out.println(encry().encode("karthik"));
	}

}
