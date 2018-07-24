package com.psk.bank.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StringToUserConverterTest {


	@Autowired
	MockMvc mockMvc; 
	
	@Test
	public void convertStringToUserRequestParamTest() throws Exception {
	    mockMvc.perform(get("/stringToUserUsingRequestParam?user=2"))
	      .andExpect(jsonPath("$.id", is(2)))
	      .andExpect(jsonPath("$.name", is("User3")))
	      .andExpect(jsonPath("$.date", is("2017-03-02T21:32:00")));
	}
	
	
	@Test
	public void convertStringToUserPathVariableTest() throws Exception {
	    mockMvc.perform(get("/stringToUserUsingPathVariable/2"))
	      .andExpect(jsonPath("$.id", is(2)))
	      .andExpect(jsonPath("$.name", is("User3")))
	      .andExpect(jsonPath("$.date", is("2017-03-02T21:32:00")));
	}
	
	
}
