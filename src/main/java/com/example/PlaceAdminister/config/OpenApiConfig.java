package com.example.PlaceAdminister.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info =@Info(
                contact = @Contact(
                        name = "Wassem Tenbakji" ,
                        email = "wasee.tenbakji@gmail.com"
                ),
                description = "open api documentation for Place Administer System",
                title = " OpenApi Swagger Demo",
                version = "1.0",
                license = @License(
                        name = "This Docs is a stage Docs which is Copyrighted for Wassem Tenbakji"
//                        url = "https://some-url.com"
                ),
                termsOfService = "Term of my Service"
        ),
        servers =
                {
                        @Server(
                                description = "Local ENV",
                                url = "http://localhost:8080"
                        ),
                        @Server(
                                description = "Prod ENV",
                                url = "https://some-url"
                        )
                },
        security = @SecurityRequirement(name = "BearerAuth")
)
@SecurityScheme(
        name = "BearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
