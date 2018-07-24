package com.psk.bank.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.psk.bank.model.User;
import com.psk.bank.repository.UserRepository;


@Component
public class StringToUserConverter implements Converter<String, User>{

	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public User convert(String id) {
		
		return userRepository.findOne(Long.parseLong(id));
	}

	

}
