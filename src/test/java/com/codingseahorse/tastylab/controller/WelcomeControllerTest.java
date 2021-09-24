package com.codingseahorse.tastylab.controller;

import com.codingseahorse.tastylab.requestsModels.RegistrationRequest;
import com.codingseahorse.tastylab.service.WelcomeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WelcomeController.class)
@AutoConfigureMockMvc(addFilters = false)
class WelcomeControllerTest {
    @MockBean
    WelcomeService welcomeService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @Test
    void should_addMember_to_db() throws Exception {
        RegistrationRequest registrationRequest = new RegistrationRequest(
                "Tina",
                "123",
                "Tina",
                "Turner",
                "Tina.Turner@world.com",
                81,
                "Female");

        mockMvc.perform(post("/api/welcome/signup")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(registrationRequest)))
                .andExpect(status().isCreated());
    }
    @Test
    void check_if_the_endpoint_refreshToken_works_correctly() throws Exception {
        mockMvc.perform(get("/api/welcome/token-refresh")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}