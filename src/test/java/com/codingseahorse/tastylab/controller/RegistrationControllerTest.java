package com.codingseahorse.tastylab.controller;

import com.codingseahorse.tastylab.dto.RegistrationDTO;
import com.codingseahorse.tastylab.model.member.Gender;
import com.codingseahorse.tastylab.requestsModels.RegistrationRequest;
import com.codingseahorse.tastylab.service.WelcomeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//TODO: FIX THE REGISTRATION-CONTROLLER-TEST
@WebMvcTest
class RegistrationControllerTest {
    /*@Autowired
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
                "Female"
        );

        RegistrationDTO registrationDTO = new RegistrationDTO(
                "Tina",
                "123",
                "Tina",
                "Turner",
                "Tina.Turner@world.com",
                81,
                Gender.FEMALE
        );

        welcomeService.registerMember(registrationDTO);

        mockMvc.perform(post("/api/welcome/signup")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(registrationRequest)))
                .andExpect(status().isCreated());
    }*/
}