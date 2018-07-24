package com.psk.bank.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.psk.bank.model.User;
import com.psk.bank.serializers.LocalDateDeserializer;
import com.psk.bank.serializers.LocalDateSerializer;

//@Ignore // uncoment to show how restTemplate works with localhost server
public class ExampleClientTest {

    private static String url;
    private static RestTemplate restTemplate;

    @BeforeClass
    public static void onceExecutedBeforeAll() {
        restTemplate = new RestTemplate();
        url = "http://localhost:8080/";
        
    	SimpleModule sm = new SimpleModule();
		sm.addSerializer(LocalDateTime.class, new LocalDateSerializer());
		sm.addDeserializer(LocalDateTime.class,new LocalDateDeserializer());
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder().modules(sm);
		
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();

		converters.add(new MappingJackson2HttpMessageConverter(builder.build()));	
		converters.add(new StringHttpMessageConverter());
		converters.add(new AllEncompassingFormHttpMessageConverter());
		
		restTemplate.setMessageConverters(converters);
        
    }

    @Test
    public void getWithPathVariableExampleShouldReturnUserWithId() {

        ResponseEntity<User> response = restTemplate.exchange(url + "getUserWithGivenId/{id}", HttpMethod.GET, null,
                User.class, 2);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getId()).isEqualTo(2);
    }
    
  

    @Test
    public void getWithPathVariableExampleShouldReturnUserWithDate() {

        ResponseEntity<User> response = restTemplate.getForEntity(url + "getUserWithGivenId/{id}", User.class, "2");

        response.getStatusCodeValue();
        response.getBody();

        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getDate()).isEqualTo(LocalDateTime.parse("2017-03-02T21:32:00"));
    }

    @Test
    public void postMethodExampleShouldReturnString() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Arrays.asList(MediaType.TEXT_PLAIN));

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("id", "1");
        map.add("name", "NewUser");
        map.add("date", "2017-01-02T21:32:00");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url + "addUser", request, String.class);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo("User added successfully");
    }

    
    ////////////
    
    
    @Test
    public void deleteWithPathVariableExampleShouldReturnDeletedUser() {
        ResponseEntity<User> response = restTemplate.exchange(url + "deleteUserWithGivenId/{id}", HttpMethod.DELETE,
                null, User.class, 1);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getId()).isEqualTo(1);
        assertThat(response.getBody().getDate()).isEqualTo(LocalDateTime.parse("2017-02-02T21:32:00"));
    }
    
    
    @Test
    public void getWithPathVariableExampleShouldReturnUser() {
        ResponseEntity<User> response = restTemplate.exchange(url + "getUserWithGivenId/{id}", HttpMethod.GET,
                null, User.class, "2");

        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getId()).isEqualTo(2);
        assertThat(response.getBody().getDate()).isEqualTo(LocalDateTime.parse("2017-03-02T21:32:00"));
    }
    
    
    

    @Test
    public void postMethodExampleShouldReturnStringUsingRealObject() {
  
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_PLAIN));
        
        User newUser = new User(4L, "newUser4", LocalDateTime.parse("2017-02-02T21:32:00"));
        HttpEntity<User> request = new HttpEntity<User>(newUser, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url + "addUserWithRequestBody", request,
                String.class);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo("User added successfully");
    }
    
    
}
