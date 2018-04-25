package com.psk.bank.controller;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
// @ActiveProfiles("WithoutBoot")
public class ExampleControllerTest {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	
	
	
	@Test
	public void shouldGetViewWithName() throws Exception {

		mockMvc.perform(get("/modelAndView")).andExpect(status().isOk()).andExpect(view().name("infoPage"));
	}
	
	
	@Test
	public void shouldShowUserWithGivenId() throws Exception {

		mockMvc.perform(get("/getUserWithId/2")).andExpect(status().isOk()).andExpect(view().name("infoPage"))
				.andExpect(model().attributeExists("message")).andExpect(
						model().attribute("message", containsString("User [id=2, name=User3, date=2017-03-02T21:32]")));
	}
	
	
	
	@Test
	public void shouldProcessRegistration() throws Exception {

		mockMvc.perform(
				post("/registerUser").param("id", "10").param("name", "NewUser").param("date", "2017-02-02T21:32:00"))
				.andExpect(status().isOk()).andExpect(view().name("infoPage"))
				.andExpect(model().attributeExists("message")).andExpect(
						model().attribute("message", containsString("User with id 10 added successfully")));
	}
	
	
	
	
	

	@Test
	public void getDetailsTest() throws Exception {
		mockMvc.perform(get("/pathVariableExample/1234/Adrian"))
				.andExpect(content().string(containsString("1234 Adrian")));
	}

	@Test
	public void getDetailsResponseTest() throws Exception {
		this.mockMvc
				.perform(get("/pathVariableExample/1234/Adrian")
						.accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
				.andExpect(status().is(200)).andExpect(content().contentType("text/html;charset=UTF-8"));

	}

	@Test
	public void deleteWithPathVariableExampleShouldReturnDeletedUser() throws Exception {
		mockMvc.perform(delete("/deleteUserWithGivenId/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1))).andExpect(jsonPath("$.name", is("User2")))
				.andExpect(jsonPath("$.date", is("2017-02-02T21:32:00")));
	}

	@Test
	public void postMethodExampleShouldReturnString() throws Exception {

		mockMvc.perform(post("/addUser").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("id", "1")
				.param("name", "NewUser").param("date", "2017-01-02T21:32:00"))
				.andExpect(content().string(containsString("User added successfully")));
	}

	@Test
	public void postMethodExampleShouldReturnStringUsingRealObject() throws Exception {

		User user = new User(10L, "TU", LocalDateTime.parse("2017-03-03T23:33:00"));

		String content = objectMapper.writeValueAsString(user);

		mockMvc.perform(post("/addUserWithRequestBody").contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(content().string(containsString("User added successfully")));
	}
	
	

	
}
