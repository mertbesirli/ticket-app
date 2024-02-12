package com.ticket.app.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomBeanFactory {

    // Create this configuration for model mapper
    // Create instance for use model-mapper with other classes
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
