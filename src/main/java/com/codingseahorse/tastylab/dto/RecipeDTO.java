package com.codingseahorse.tastylab.dto;

import com.codingseahorse.tastylab.model.recipe.Food;
import com.codingseahorse.tastylab.model.recipe.FoodTag;
import com.codingseahorse.tastylab.model.recipe.RecipeSkills;
import com.codingseahorse.tastylab.model.recipe.RecipeStatus;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Data
@RequiredArgsConstructor
public class RecipeDTO {
    private final LocalDateTime createdAt;
    private final String recipeName;
    private final Integer duration;
    private RecipeStatus recipeStatus = RecipeStatus.NORMAL;
    private final RecipeSkills recipeSkills;
    private final Collection<Food> foods;
    private final String creatorEmail;
    private final Set<FoodTag> foodTag;
}
