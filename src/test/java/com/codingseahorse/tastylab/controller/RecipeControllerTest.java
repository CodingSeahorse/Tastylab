package com.codingseahorse.tastylab.controller;

import com.codingseahorse.tastylab.dto.HomeDTO;
import com.codingseahorse.tastylab.dto.MemberDTO;
import com.codingseahorse.tastylab.dto.RecipeDTO;
import com.codingseahorse.tastylab.model.member.Gender;
import com.codingseahorse.tastylab.model.recipe.Food;
import com.codingseahorse.tastylab.model.recipe.FoodTag;
import com.codingseahorse.tastylab.model.recipe.RecipeSkills;
import com.codingseahorse.tastylab.repository.RecipeRepository;
import com.codingseahorse.tastylab.requestsModels.RecipeRequest;
import com.codingseahorse.tastylab.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//TODO: FIX THE RECIPE-CONTROLLER-TEST
@WebMvcTest
class RecipeControllerTest {
    /*@MockBean
    RecipeService recipeService;
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @Test
    void check_if_endpoint_calls_correctly_retrieveStartingContent() {
        // <editor-fold defaultstate="collapsed" desc="create Lists and Collection">
        List<RecipeDTO> exploreRecipesList = new ArrayList<>();
        List<RecipeDTO> highlightRecipesList = new ArrayList<>();

        List<FoodTag> foodTagsCrepe = new ArrayList<>();
        List<FoodTag> foodTagsFrenshToast = new ArrayList<>();

        Collection<Food> crepeCollection = new ArrayList<>();
        Collection<Food> frenshToastCollection = new ArrayList<>();
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="pageRequestFood for new Page<RecipeDTO>">
        PageRequest pageRequestCrepe = PageRequest.of(
                0,
                3,
                Sort.by("createdAt").ascending()
        );
        PageRequest pageRequestFrenshToast = PageRequest.of(
                0,
                3,
                Sort.by("createdAt").ascending()
        );
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="crepeCollection add">
        crepeCollection.add(Food.APPLE);
        crepeCollection.add(Food.FLOUR);
        crepeCollection.add(Food.MILK);
        crepeCollection.add(Food.EGG);
        crepeCollection.add(Food.SALT);
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="frenshToastCollection add">
        frenshToastCollection.add(Food.SALT);
        frenshToastCollection.add(Food.APPLE);
        frenshToastCollection.add(Food.MILK);
        frenshToastCollection.add(Food.EGG);
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="FoodTags add">
        foodTagsCrepe.add(new FoodTag("crepe"));
        foodTagsCrepe.add(new FoodTag("delicious"));

        foodTagsFrenshToast.add(new FoodTag("frenshToast"));
        foodTagsFrenshToast.add(new FoodTag("tasty"));
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="RecipeDTOs create">
        RecipeDTO firstRecipeDTO = new RecipeDTO(
                LocalDateTime.now(),
                "crepe",
                20,
                RecipeSkills.EASY,
                crepeCollection,
                new MemberDTO(
                        "Shaggy",
                        "Doo",
                        34,
                        Gender.MALE
                ),
                foodTagsCrepe
        );

        RecipeDTO secondRecipeDTO = new RecipeDTO(
                LocalDateTime.now(),
                "frensh toast",
                30,
                RecipeSkills.EASY,
                frenshToastCollection,
                new MemberDTO(
                        "Shaggy",
                        "Doo",
                        34,
                        Gender.MALE
                ),
                foodTagsFrenshToast
        );
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="exploreRecipesList & highlightRecipesList add">
        exploreRecipesList.add(firstRecipeDTO);
        highlightRecipesList.add(secondRecipeDTO);
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="PageRequest variables for new Page<RecipeDTO>">
        final int start1 = (int)pageRequestCrepe.getOffset();
        final int end1 = Math.min((start1 + pageRequestCrepe.getPageSize()), exploreRecipesList.size());
        final int start2 = (int)pageRequestFrenshToast.getOffset();
        final int end2 = Math.min((start2 + pageRequestFrenshToast.getPageSize()), highlightRecipesList.size());
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="create 2 new Pages(Explore- & Highlight-RecipePage) of RecipeDTOs">
        Page<RecipeDTO> exploreRecipesPage = new PageImpl<>(exploreRecipesList.subList(start1,end1),pageRequestCrepe, exploreRecipesList.size());
        Page<RecipeDTO> highlightRecipesPage = new PageImpl<>(highlightRecipesList.subList(start2,end2),pageRequestFrenshToast, highlightRecipesList.size());
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="PageRequest params for retrieveStartingContent(..)">
        PageRequest pageRequestServiceExplore = PageRequest.of(
                0,
                3,
                Sort.by("createdAt").ascending()
        );
        PageRequest pageRequestServiceHighlight = PageRequest.of(
                0,
                3,
                Sort.by("createdAt").ascending()
        );
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="HomeDTO create and set Pages">
        HomeDTO homeDTO = new HomeDTO();
        homeDTO.setHighlightRecipes(highlightRecipesPage);
        homeDTO.setExploreRecipes(exploreRecipesPage);
        // </editor-fold>
        when(recipeService.retrieveStartingContent(pageRequestServiceExplore,pageRequestServiceHighlight))
                .thenReturn(homeDTO);

        verify(recipeRepository,times(2)).getAllByRecipeStatus(any());

    }

    @Test
    void check_if_the_endpoint_createRecipe_works_correctly() throws Exception{
        String[] foods = new String[2];
        foods[0] = "TOMATO";
        foods[1] = "FLOUR";
        String[] foodTags = new String[2];
        foodTags[0] = "tasty";
        foodTags[1] = "pizza";
        RecipeRequest recipeRequest = new RecipeRequest(
                "pizza",
                30,
                "Easy",
                foods,
                foodTags
        );
        mockMvc.perform(post("/api/recipe")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(recipeRequest)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void check_if_the_endpoint_getStartingContent_works_correctly() throws Exception{
        mockMvc.perform(get("/api/recipe/home")
                .queryParam("page","0","0")
                .queryParam("size","3","3")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void should_get_Member_Recipes() {

    }*/
}