package com.od.eisgroup.service.impl.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class provides main configuration of the Servise Layer of a EIS HR Project.
 *
 * @author Alice Klochkova
 * @since 1.1
 */
@Configuration
public class ServiceConfig {

    /**
     * {@link org.modelmapper.ModelMapper} helps to convert Entity to DTO and vice versa.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
