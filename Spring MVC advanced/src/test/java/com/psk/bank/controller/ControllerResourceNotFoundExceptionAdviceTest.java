package com.psk.bank.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerResourceNotFoundExceptionAdviceTest {

	@Autowired
	MockMvc mockMvc; 
	
	@Test
	public void shouldThrowException() throws Exception {
		mockMvc.perform(get("/getUserWithGivenId/5")).andExpect(status().is5xxServerError())
				.andExpect(content().string(containsString("Resource with id 5 is not exist")));
	}

}
