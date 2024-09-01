package com.cortestudios.pricingmanager.pricing.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.api-docs.version}") String appVersion) {
        return new OpenAPI()
                .info(apiInfo(appVersion));
    }
    private Info apiInfo(String appVersion) {

        Contact contact = new Contact();
        contact.setName("Pricing Manager");
        contact.setUrl("https://www.cortestudios.com");
        contact.setEmail("jecortes2304@gmail.com");
        return new Info()
                .description("This is a Rest API to manage pricing")
                .summary("Example of a Rest API to manage pricing")
                .contact(contact)
                .title("Pricing Manager")
                .version(appVersion)
                .license(new License().name("MIT").url("https://opensource.org/licenses/MIT"));
    }
}
