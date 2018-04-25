package com.psk.bank.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExampleRedirectsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldRedirectToView() throws Exception {
		mockMvc.perform(get("/do-and-redirect-1")).andExpect(status().is3xxRedirection())
				.andExpect(header().string("Location", containsString("/destination")));
	}

	@Test
	public void shouldForwardView() throws Exception {
		mockMvc.perform(get("/do-and-forward")).andExpect(status().is2xxSuccessful());
	}

	////////// Consume Produce

	@Test
	public void shouldRespondWithErrorWheNotPlainText() throws Exception {
		mockMvc.perform(post("/consume-produce-example").contentType(MediaType.APPLICATION_OCTET_STREAM)
				.accept(MediaType.TEXT_PLAIN).content("input")).andExpect(status().is4xxClientError());
	}

	@Test
	public void shouldAcceptPlainTextEntityVersion() throws Exception {
		mockMvc.perform(post("/consume-produce-entity-example").contentType(MediaType.APPLICATION_JSON).content("input")
				.accept(MediaType.APPLICATION_XML)).andExpect(status().is2xxSuccessful())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML));
	}

	@Test
	public void shouldRespondWithErrorWheNotPlainTextEntityVersion() throws Exception {
		mockMvc.perform(post("/consume-produce-entity-example").contentType(MediaType.APPLICATION_OCTET_STREAM)
				.accept(MediaType.TEXT_PLAIN).content("input")).andExpect(status().is4xxClientError());
	}

	////////////// Response Status

	@Test
	public void shouldRespondWithTeapotStatus() throws Exception {
		mockMvc.perform(post("/response-status-example").contentType(MediaType.TEXT_PLAIN).content("input"))
				.andExpect(status().isIAmATeapot())
				.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN));
	}

	@Test
	public void shouldRespondWithTeapotStatusEntityVersion() throws Exception {
		mockMvc.perform(post("/response-status-entity-example").contentType(MediaType.TEXT_PLAIN).content("input"))
				.andExpect(status().isIAmATeapot())
				.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN));
	}

	@Test
	public void shouldRespondWithTeapotStatusWhenExceptionOccures() throws Exception {
		mockMvc.perform(post("/response-status-exception-example").contentType(MediaType.APPLICATION_JSON).content("input"))
				.andExpect(status().isIAmATeapot());
	}
}
