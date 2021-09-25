package com.codingseahorse.tastylab.controller;

import com.codingseahorse.tastylab.dto.MemberCardDTO;
import com.codingseahorse.tastylab.dto.MemberDTO;
import com.codingseahorse.tastylab.dto.RecipeDTO;
import com.codingseahorse.tastylab.dto.converter.Converter;
import com.codingseahorse.tastylab.model.member.Gender;
import com.codingseahorse.tastylab.model.recipe.Food;
import com.codingseahorse.tastylab.model.recipe.FoodTag;
import com.codingseahorse.tastylab.model.recipe.RecipeSkills;
import com.codingseahorse.tastylab.requestsModels.RecipeRequest;
import com.codingseahorse.tastylab.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(RecipeController.class)
class RecipeControllerTest {
    @MockBean
    RecipeService recipeService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @Spy
    Converter converter;

    // <editor-fold defaultstate="collapsed" desc="FoodCollection,FoodTags,List<RecipeDTO>,Page<RecipeDTO>">
    Collection<Food> foodCollection = new ArrayList<>();
    Set<FoodTag> foodTags = new HashSet<>();
    List<RecipeDTO> recipeDTOList = new ArrayList<>();
    Page<RecipeDTO> recipeDTOPage;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="MemberCardDTO,MemberDTO,RecipeDTO,PageRequest">
    MemberCardDTO aliciaCard = new MemberCardDTO(
            "secure",
            "123"
    );

    MemberDTO alicia = new MemberDTO(
            "Alicia",
            "Sierra",
            42,
            Gender.FEMALE
    );

    RecipeDTO salmon = new RecipeDTO(
            LocalDateTime.now(),
            "salmon",
            45,
            RecipeSkills.PROFESSIONAL,
            foodCollection,
            "Alicia.Sierra@world.com",
            foodTags
    );

    PageRequest anyPageRequest = PageRequest.of(
            0,
            3,
            Sort.by("createdAt").ascending()
    );
    // </editor-fold>

    @BeforeEach
    void setup(){
        // <editor-fold defaultstate="collapsed" desc="setMemberCardDTO&Email of alicia">
        alicia.setMemberCardDTO(aliciaCard);
        alicia.setEmail("Alicia.Sierra@world.com");
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="add data to foodCollection and foodTags">
        foodCollection.add(Food.LEMON);
        foodTags.add(new FoodTag("salmon"));
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="add Recipe to List|setRecipes to alicia">
        recipeDTOList.add(salmon);

        recipeDTOPage = converter.convertRecipeDTOListToPageOfRecipeDTO(recipeDTOList,anyPageRequest);

        alicia.setRecipes(recipeDTOPage);
        // </editor-fold>
    }

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
        // <editor-fold defaultstate="collapsed" desc="RecipeRequest Model">
        RecipeRequest recipeRequest = new RecipeRequest(
                "pizza",
                30,
                "Easy",
                foods,
                foodTags,
                "Alicia.Sierra@world.com");
        // </editor-fold>
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

    @Test
    void check_if_calculateRecipe_works_correctly() throws Exception {
        mockMvc.perform(post("/api/recipe/lizzy")
                        .queryParam("foods", "Egg", "Flour","Milk")
                        .queryParam("page","0")
                        .queryParam("size","3"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void check_if_the_endpoint_getMember_recipe_works_correctly() throws Exception {
        mockMvc.perform(get("/api/recipe/shaggyDoo")
                .queryParam("page","0")
                .queryParam("size","3"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}