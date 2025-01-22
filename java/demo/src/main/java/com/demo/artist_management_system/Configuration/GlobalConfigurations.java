package com.example.demo.Configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalConfigurations {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
