package com.tramite.online.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

     @Bean
    public OpenAPI ordersServiceAPI(){
        return new OpenAPI()
        .info(new Info().title("Tramites Controller Online API")
        .description("This is the REST Api for Tramites-Online Service")
        .version("v0.0.1")
        .license(new License().name("Apache 2.0")))
        .externalDocs(new ExternalDocumentation()
        .description("You can refer to the Orders Service Documentation ")
        .url("https://orders-service-dummy-url.com/docs"));
        
    }

}
