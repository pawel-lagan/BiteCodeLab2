package com.psk.bank.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ConverterClientTest {

	private static String url;

	@BeforeClass
	public static void onceExecutedBeforeAll() {
		url = "http://localhost:8080/";
	}
	
    ///Tests Converters
    
    
	@Test
	public void shouldReturnUserXml() {
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<String> response = restTemplate.exchange(url + "getUserWithGivenId/{id}", HttpMethod.GET, entity,
				String.class, "1");

		assertTrue(response.getHeaders().getContentType().includes(MediaType.APPLICATION_XML));
		assertThat(response.getBody()).isNotNull();
	}
    
    
	@Test
	public void shouldReturnUserJson() {
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<String> response = restTemplate.exchange(url + "getUserWithGivenId/{id}", HttpMethod.GET, entity,
				String.class, "1");

		assertTrue(response.getHeaders().getContentType().includes(MediaType.APPLICATION_JSON));
		assertThat(response.getBody()).isNotNull();
	}
	
}
