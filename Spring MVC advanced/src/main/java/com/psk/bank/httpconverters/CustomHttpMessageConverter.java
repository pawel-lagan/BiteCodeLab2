package com.psk.bank.httpconverters;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psk.bank.model.User;

//@Component
public class CustomHttpMessageConverter extends AbstractHttpMessageConverter<User> {

	
	ObjectMapper objectMapper;

	
	@Autowired
	public CustomHttpMessageConverter(ObjectMapper objectMapper){
		this.objectMapper=objectMapper;
		
		this.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
		
	}
	
	
	@Override
	protected boolean supports(Class<?> clazz) {
		
		return clazz.equals(com.psk.bank.model.User.class);
	}

	@Override
	protected User readInternal(Class<? extends User> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		
		User user = objectMapper.readValue(inputMessage.getBody(), User.class);
		
		return user;
	}
	
	

	@Override
	protected void writeInternal(User t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		
        String body = t.getId()+"|"+ t.getName() + "|" +
                t.getDate();
        
        
        outputMessage.getHeaders().setContentType(MediaType.TEXT_PLAIN);
        outputMessage.getBody().write(body.getBytes());
		
	}
	
}
