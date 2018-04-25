package com.psk.bank.resources;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
public class AccountsResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnAccount() throws Exception {
        mockMvc.perform(get("/accounts/ABC1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.accountNumber", is("ABC1")))
            .andExpect(jsonPath("$.firstName", is("Jan")))
            .andExpect(jsonPath("$.creationTs", is("2017-01-02T21:32:00")));
    }

    @Test
    public void shouldParseLocalDate() throws Exception {
        mockMvc.perform(get("/accounts/added-since/20170321")).andExpect(status().is2xxSuccessful())
                .andExpect(content().string(is("added-since 2017-03-21")));
    }

    @Test
    public void shouldParseLocalDateTime() throws Exception {
        mockMvc.perform(get("/accounts/added-since-ts/20170321T211531")).andExpect(status().is2xxSuccessful())
                .andExpect(content().string(is("added-since-ts 2017-03-21T21:15:31")));
    }
}
