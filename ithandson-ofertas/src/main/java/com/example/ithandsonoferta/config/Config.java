package com.example.ithandsonoferta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config  implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "DELETE")
                .allowedOrigins("http://localhost:4200","http://localhost:4300", "http://localhost:8888")
                .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept",
                        "Access-Control-Allow-Headers", "access-control-allow-origin");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
