package com.psk.bank.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.psk.bank.model.User;
import com.psk.bank.repository.UserRepository;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class WithoutSpringBootAnnotationTest {

	
	MockMvc mockMvc;
	
	@Test
	public void showAccountPageTest() throws Exception {
		
		UserRepository mockRepo =  mock(UserRepository.class);
		
		User user = new User(1L, "User1", LocalDateTime.parse("2017-01-02T21:32:00"));
		
		when(mockRepo.findOne(1L)).thenReturn(user);
		
		ExampleController exampleController = new ExampleController(mockRepo);
		
		mockMvc = MockMvcBuilders
                .standaloneSetup(exampleController)
                .build();
		
		
		mockMvc.perform(get("/getUserWithId/1")).andExpect(status().isOk()).andExpect(view().name("infoPage"))
				.andExpect(model().attributeExists("message")).andExpect(model().attribute("message", containsString(
						"User [id=1, name=User1, date=2017-01-02T21:32]"))); 
		
	}
	
	
	
}
