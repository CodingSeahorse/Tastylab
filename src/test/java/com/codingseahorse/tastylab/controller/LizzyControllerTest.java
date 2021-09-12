package com.codingseahorse.tastylab.controller;

import com.codingseahorse.tastylab.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LizzyController.class)
class LizzyControllerTest {
    @MockBean
    RecipeService recipeService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void check_if_post_food_works_correctly() throws Exception {
        mockMvc.perform(post("/api/lizzy")
                .queryParam("foods", "Egg", "Flour","Milk")
                .queryParam("page","0")
                .queryParam("size","3"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}