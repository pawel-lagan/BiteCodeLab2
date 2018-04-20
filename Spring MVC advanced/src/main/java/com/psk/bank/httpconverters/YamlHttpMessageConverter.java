package com.psk.bank.httpconverters;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.psk.bank.model.User;
import com.psk.bank.serializers.LocalDateDeserializer;
import com.psk.bank.serializers.LocalDateSerializer;

public class YamlHttpMessageConverter extends AbstractHttpMessageConverter<User>{

	
	ObjectMapper objectMapper;
	
	public YamlHttpMessageConverter() {
		MediaType mediaType = new MediaType("application","yaml");
		setSupportedMediaTypes(Arrays.asList(mediaType));
		
		SimpleModule sm = new SimpleModule();
    	sm.addSerializer(LocalDateTime.class,new LocalDateSerializer());
    	sm.addDeserializer(LocalDateTime.class,new LocalDateDeserializer());
    	
    	objectMapper = new ObjectMapper(new YAMLFactory()).registerModule(sm);
    	
	}
	
	
	@Override
	protected boolean supports(Class<?> clazz) {
		return clazz.equals(com.psk.bank.model.User.class);
	}

	@Override
	protected User readInternal(Class<? extends User> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
	    
		return objectMapper.readValue(inputMessage.getBody(),User.class);
	}

	@Override
	protected void writeInternal(User t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		
		outputMessage.getBody().write(objectMapper.writeValueAsBytes(t));
		
	}

}
	




