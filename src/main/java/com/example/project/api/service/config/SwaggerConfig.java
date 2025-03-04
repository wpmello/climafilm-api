package com.example.project.api.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.HashSet;
import java.util.Set;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

@Configuration
public class SwaggerConfig {

    @Value("${documentation.api.base-package}")
    private String basePackage;
    @Value("${documentation.api.title}")
    private String apiTitle;
    @Value("${documentation.api.description}")
    private String apiDescription;
    @Value("${documentation.api.contact-name}")
    private String contactName;
    @Value("${documentation.api.contact-email}")
    private String contactEmail;
    @Value("${documentation.api.contact-github}")
    private String contactGithub;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .protocols(getProtocols())
                .select()
                .apis(basePackage(basePackage))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(buildApiInfo());
    }

    private Set<String> getProtocols() {
        Set<String> protocols = new HashSet<>();
        protocols.add("https");
        protocols.add("http");
        return protocols;
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title(apiTitle)
                .description(apiDescription)
                .version("1.0.0")
                .contact(new Contact(contactName, contactGithub, contactEmail))
                .build();
    }
}
