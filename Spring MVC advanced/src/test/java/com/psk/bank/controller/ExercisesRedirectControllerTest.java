package com.psk.bank.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
public class ExercisesRedirectControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldRedirectToRegisterUserPage() throws Exception {

		mockMvc.perform(post("/redirect/register").param("id", "35").param("name", "Jan"))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/redirect/showProfile/35"));
	}

}
