package com.codingseahorse.tastylab.controller;

import com.codingseahorse.tastylab.requestsModels.RecipeRequest;
import com.codingseahorse.tastylab.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RecipeController.class)
class RecipeControllerTest {
    @MockBean
    RecipeService recipeService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @Test
    void check_if_the_endpoint_createRecipe_works_correctly() throws Exception {
        // <editor-fold defaultstate="collapsed" desc="String[] for RecipeRequest">
        String[] foods = new String[2];
        foods[0] = "TOMATO";
        foods[1] = "FLOUR";

        String[] foodTags = new String[2];
        foodTags[0] = "tasty";
        foodTags[1] = "pizza";
        // </editor-fold>
        RecipeRequest recipeRequest = new RecipeRequest(
                "pizza",
                30,
                "Easy",
                foods,
                foodTags);

        mockMvc.perform(post("/api/recipe")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(recipeRequest)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void check_if_the_endpoint_getStartingContent_works_correctly() throws Exception {
        mockMvc.perform(get("/api/recipe/home")
                    .queryParam("page","0","0")
                    .queryParam("size","3","3")
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk());
    }

    //TODO: WRITE getMemberRecipes() TEST
}