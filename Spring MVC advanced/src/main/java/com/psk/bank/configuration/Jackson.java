package com.psk.bank.configuration;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.psk.bank.serializers.LocalDateDeserializer;
import com.psk.bank.serializers.LocalDateSerializer;

@Configuration
public class Jackson extends WebMvcConfigurerAdapter {


	
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
 
        
    	//converters.add(new CustomHttpMessageConverter());
        // converters.add(createXmlHttpMessageConverter());
    	
        super.configureMessageConverters(converters);
    
    }
    

    
    
    public HttpMessageConverter<Object> createXmlHttpMessageConverter() {
        
    	MarshallingHttpMessageConverter xmlConverter = 
          new MarshallingHttpMessageConverter();
        
        XStreamMarshaller xstreamMarshaller = new XStreamMarshaller();
       
        xmlConverter.setMarshaller(xstreamMarshaller);
        xmlConverter.setUnmarshaller(xstreamMarshaller);
        
        
        return xmlConverter;
    }
    
    
    
    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
    	
    	SimpleModule sm = new SimpleModule();
    	sm.addSerializer(LocalDateTime.class,new LocalDateSerializer());
    	sm.addDeserializer(LocalDateTime.class,new LocalDateDeserializer());
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder().modules(sm);
        
        
        return builder;
    }
    
    
    
}
