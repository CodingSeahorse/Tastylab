package com.codingseahorse.tastylab.controller;

import com.codingseahorse.tastylab.dto.HomeDTO;
import com.codingseahorse.tastylab.dto.MemberDTO;
import com.codingseahorse.tastylab.dto.RecipeDTO;
import com.codingseahorse.tastylab.model.member.Gender;
import com.codingseahorse.tastylab.model.recipe.Food;
import com.codingseahorse.tastylab.model.recipe.FoodTag;
import com.codingseahorse.tastylab.model.recipe.RecipeSkills;
import com.codingseahorse.tastylab.requestsModels.RecipeRequest;
import com.codingseahorse.tastylab.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {
    @Autowired
    RecipeService recipeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createRecipe(
            @RequestBody RecipeRequest recipeRequest
    ) {
        RecipeSkills detectedRecipeSkill = RecipeSkills.valueOf(recipeRequest.getRecipeSkill().toUpperCase());
        Collection<Food> foodCollection = Arrays.stream(recipeRequest.getFoods()).map(Food::valueOf).collect(Collectors.toSet());
        List<FoodTag> foodTagList = Arrays.stream(recipeRequest.getFoodTags()).map(FoodTag::new).collect(Collectors.toList());

        MemberDTO creator = new MemberDTO(
                "scooby",
                "doo",
                8,
                Gender.MALE
        );

        RecipeDTO recipeDTO = new RecipeDTO(
                LocalDateTime.now(),
                recipeRequest.getRecipeName(),
                recipeRequest.getDuration(),
                detectedRecipeSkill,
                foodCollection,
                creator,
                foodTagList
        );

        recipeService.createRecipe(recipeDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/home")
    public HomeDTO getStartingContent(
            @RequestParam Integer[] page,
            @RequestParam Integer[] size
    ) {
        PageRequest pageRequestExplore = PageRequest.of(
                page[0],
                size[0],
                Sort.by("createdAt")
        );
        PageRequest pageRequestHighlight = PageRequest.of(
                page[1],
                size[1],
                Sort.by("createdAt")
        );
        return recipeService.retrieveStartingContent(pageRequestExplore,pageRequestHighlight);
    }

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public MemberDTO getMemberRecipes(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam String username) {
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.by("createdAt").ascending()
        );
        return recipeService.retrieveRecipesFromMember(username,pageRequest);
    }
}



