package com.psk.bank.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psk.bank.model.User;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExercisesRedirectControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	public void shouldRedirectToRegisterUserPage() throws Exception {

		User user = new User(35L, "TU", LocalDateTime.parse("2017-03-03T23:33:00"));

		String content = objectMapper.writeValueAsString(user);
		
		mockMvc.perform(post("/registrationService/register").contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/registrationService/showUserPage/35"));
	}

}
