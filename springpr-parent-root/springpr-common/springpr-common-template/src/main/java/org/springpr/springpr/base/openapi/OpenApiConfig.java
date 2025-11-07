/* (C)2023 */
package org.springpr.springpr.base.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
    @Bean
    OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(
                        new Info()
                                .title("SpringPr Base Application API")
                                .description(
                                        "This is a sample Spring Boot RESTful service using"
                                                + " springdoc-openapi and OpenAPI 3.")
                                .termsOfService("terms")
                                .contact(new Contact().email("yangli136@gmail.com"))
                                .license(new License().name("GNU"))
                                .version("0.0.1"));
    }
}
