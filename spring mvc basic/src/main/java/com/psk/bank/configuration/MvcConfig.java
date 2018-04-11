package com.psk.bank.configuration;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
@EnableWebMvc
@Profile("WithoutBoot")
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        
    	SimpleModule sm = new SimpleModule();
    	sm.addSerializer(LocalDateTime.class,new LocalDateSerializer());
    	sm.addDeserializer(LocalDateTime.class,new LocalDateDeserializer());
        Jackson2ObjectMapperBuilder b = new Jackson2ObjectMapperBuilder().modules(sm);
        
        converters.add(new MappingJackson2HttpMessageConverter(b.build()));
    }
    
    
}
