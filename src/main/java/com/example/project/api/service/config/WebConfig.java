package com.example.project.api.service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Value("${cors.allowed-origins}")
    private String allowedOrigins;
    @Value("${cors.allowed-methods}")
    private String allowedMethods;
    @Value("${cors.max-age}")
    private long maxAge;
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
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public String string() {
        return "";
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(allowedOrigins)
                        .allowedMethods(allowedMethods.split(","))
                        .maxAge(maxAge);
            }
        };
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(List.of(new Server().url(allowedOrigins)))
                .info(new Info()
                        .title(apiTitle)
                        .description(apiDescription)
                        .contact(new Contact().name(contactName).email(contactEmail).url(contactGithub))
                        .version("1.0.0"));
    }
}