package com.example.drones.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("Drone")
                .pathsToMatch("/api/v1/drone")
                .packagesToScan("com.example.drones.controllers")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenApi() {
        Contact contact = new Contact();
        contact.setEmail("mostafaamosad@gmail.com");
        contact.setName("Mostafa Mosad");
        contact.setUrl("https://www.linkedin.com/in/mostafa-mosad-629486170/");
        return new OpenAPI()
                .info(new Info().title("Drones")
                        .description("Musala Soft Drone Task").contact(contact)
                        .license(new License().name("Musala Soft License"))
                );
    }

}
