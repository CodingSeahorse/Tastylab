package com.codingseahorse.tastylab.controller;

import com.codingseahorse.tastylab.dto.HomeDTO;
import com.codingseahorse.tastylab.dto.LizzyDTO;
import com.codingseahorse.tastylab.dto.MemberDTO;
import com.codingseahorse.tastylab.dto.RecipeDTO;
import com.codingseahorse.tastylab.model.recipe.Food;
import com.codingseahorse.tastylab.model.recipe.FoodTag;
import com.codingseahorse.tastylab.model.recipe.RecipeSkills;
import com.codingseahorse.tastylab.requestsModels.RecipeRequest;
import com.codingseahorse.tastylab.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {
    @Autowired
    RecipeService recipeService;

    // ===== CREATE =====
    @Operation(summary = "create recipe")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "successfully created",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid payload. Please correct your RecipeRequest or url",
                            content =  @Content),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Member not found",
                            content = @Content)})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createRecipe(
            @Parameter(description = "RequestBody(RecipeRequest) to pass")
            @RequestBody RecipeRequest recipeRequest) {

        RecipeSkills detectedRecipeSkill =
                RecipeSkills.valueOf(recipeRequest.getRecipeSkill().toUpperCase());

        Set<FoodTag> foodTagSet =
                Arrays.stream(recipeRequest.getFoodTags())
                        .map(FoodTag::new)
                        .collect(Collectors.toSet());

        Collection<Food> foodCollection =
                Arrays.stream(recipeRequest.getFoods())
                        .map(String::toUpperCase)
                        .map(Food::valueOf)
                        .collect(Collectors.toSet());

        RecipeDTO recipeDTO = new RecipeDTO(
                LocalDateTime.now(),
                recipeRequest.getRecipeName(),
                recipeRequest.getDuration(),
                detectedRecipeSkill,
                foodCollection,
                recipeRequest.getCreatorEmail(),
                foodTagSet);

        recipeService.createRecipe(recipeDTO);
    }

    // ===== READ =====
    @Operation(summary = "get the starting content for the Home*page")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "found the startingContent",
                            content = { @Content(
                                    mediaType = "application/json",
                                    schema = @Schema (implementation = HomeDTO.class))}),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid variables. Please correct your parameters or url",
                            content =  @Content),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No pages found",
                            content = @Content)})
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/home")
    public HomeDTO getStartingContent(
            @RequestParam Integer[] page,
            @RequestParam Integer[] size) {

        PageRequest pageRequestExplore = PageRequest.of(
                page[0],
                size[0],
                Sort.by("createdAt"));

        PageRequest pageRequestHighlight = PageRequest.of(
                page[1],
                size[1],
                Sort.by("createdAt"));

        return recipeService.retrieveStartingContent(
                pageRequestExplore,
                pageRequestHighlight);
    }

    @Operation(summary = "get member recipes")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "found the member recipes",
                            content = { @Content(
                                    mediaType = "application/json",
                                    schema = @Schema (implementation = MemberDTO.class))}),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid variables. Please correct your parameters or url",
                            content =  @Content),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No recipes found",
                            content = @Content)})
    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public MemberDTO getMemberRecipes(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @PathVariable String username) {

        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.by("createdAt").ascending());

        return recipeService.retrieveRecipesFromMember(
                username,
                pageRequest);
    }

    @Operation(summary = "find recipes with lizzy")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successfully loaded",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid payload. Please correct your RecipeRequest or url",
                            content =  @Content),
                    @ApiResponse(
                            responseCode = "404",
                            description = "no recipes found",
                            content = @Content)})
    @PostMapping("/lizzy")
    @ResponseStatus(HttpStatus.OK)
    public LizzyDTO calculateRecipe(@RequestParam String[] foods,
                              @RequestParam Integer page,
                              @RequestParam Integer size){
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.by("createdAt").ascending()
        );
        return recipeService.findRecipeByElements(foods,pageRequest);
    }
}



