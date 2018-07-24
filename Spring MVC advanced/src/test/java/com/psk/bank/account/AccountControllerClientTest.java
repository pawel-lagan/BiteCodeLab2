package com.psk.bank.account;

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
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.psk.bank.model.Account;
import com.psk.bank.model.AccountEntity;
import com.psk.bank.serializers.LocalDateDeserializer;
import com.psk.bank.serializers.LocalDateSerializer;

//@Ignore // uncoment to show how restTemplate works with localhost server
public class AccountControllerClientTest {

    private static String url;
    private static RestTemplate restTemplate = new RestTemplate();

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
		
		restTemplate.setMessageConverters(converters);
    }

    
    
    @Test
    public void shouldReturnDeletedUser() {
        ResponseEntity<AccountEntity> response = restTemplate.exchange(url + "deleteAccountWithGivenId/{id}", HttpMethod.DELETE,
                null, AccountEntity.class, "ABC1");

        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getAccountNumber()).isEqualTo("ABC1");
        assertThat(response.getBody().getCreationTs()).isEqualTo(LocalDateTime.parse("2017-01-02T21:32:00"));
    }
    
    
    @Test
    public void ShouldReturnUserWithGivenId() {
        ResponseEntity<AccountEntity> response = restTemplate.exchange(url + "getAccountWithGivenId/{id}", HttpMethod.GET,
                null, AccountEntity.class, "ABC2");

        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getAccountNumber()).isEqualTo("ABC2");
        assertThat(response.getBody().getCreationTs()).isEqualTo(LocalDateTime.parse("2017-01-02T21:32:00"));
    }
    
    

    @Test
    public void shouldAddAccount() {
    	HttpHeaders header = new HttpHeaders();
    	header.setAccept(Arrays.asList(MediaType.TEXT_PLAIN));

    	Account account = AccountEntity.newInstance("asd", "csac", "adw", true, LocalDateTime.parse("2017-01-02T21:32:00"));
    	
    	HttpEntity<Account> request = new HttpEntity<Account>(account,header);
    	
    	ResponseEntity<String> response = restTemplate.postForEntity(url+"addAccount", request, String.class);
    	
    	assertThat(response.getStatusCode().is2xxSuccessful());
    	assertThat(response.getBody().equals("Account added"));
    }
    
    
    
}

