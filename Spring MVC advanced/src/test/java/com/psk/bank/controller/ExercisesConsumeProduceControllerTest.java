package com.psk.bank.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExercisesConsumeProduceControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	

	@Test
	public void shouldConsumeJson() throws Exception {

		mockMvc.perform(post("/consumeProduceApi").contentType(MediaType.APPLICATION_JSON).content("json"))
				.andExpect(content().string(containsString("Consume json")));
	}
	

	@Test
	public void shouldConsumeXml() throws Exception {

		mockMvc.perform(post("/consumeProduceApi").contentType(MediaType.APPLICATION_XML).content("xml"))
				.andExpect(content().string(containsString("Consume xml")));
	}
	
	@Test
	public void shouldConsumeText() throws Exception {

		mockMvc.perform(post("/consumeProduceApi").contentType(MediaType.TEXT_PLAIN).content("text"))
				.andExpect(content().string(containsString("Consume text")));
	}
	
}
