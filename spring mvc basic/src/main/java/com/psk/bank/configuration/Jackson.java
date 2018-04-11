package com.psk.bank.configuration;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.module.SimpleModule;


@Configuration
public class Jackson {

	
    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
    	
    	SimpleModule sm = new SimpleModule();
    	sm.addSerializer(LocalDateTime.class,new LocalDateSerializer());
    	sm.addDeserializer(LocalDateTime.class,new LocalDateDeserializer());
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder().modules(sm);
        
        return builder;
    }
    
    
}






