package com.example.walkthroughjava.config;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class Config implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "DELETE", "PUT")
                .allowedOrigins("*")
                .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept",
                        "Access-Control-Allow-Headers", "access-control-allow-origin");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Override
    public void addViewControllers(ViewControllerRegistry viewControllerRegistry) {
//        viewControllerRegistry.addViewController("/").setViewName("home");
    }
    @Bean
    public ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.addDialect(new Java8TimeDialect());
        engine.setTemplateResolver(templateResolver);
        return engine;
    }

    @Bean
    public JavaTimeModule dateTimeModule() {
        return new JavaTimeModule();
    }
}
