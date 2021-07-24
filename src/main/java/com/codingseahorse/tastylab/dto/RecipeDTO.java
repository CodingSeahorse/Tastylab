package com.codingseahorse.tastylab.dto;

import com.codingseahorse.tastylab.model.recipe.Food;
import com.codingseahorse.tastylab.model.recipe.FoodTag;
import com.codingseahorse.tastylab.model.recipe.RecipeSkills;
import com.codingseahorse.tastylab.model.recipe.RecipeStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class RecipeDTO {
    private @NonNull LocalDateTime createdAt;
    private @NonNull String recipeName;
    private @NonNull Integer duration;
    private RecipeStatus recipeStatus;
    private @NonNull RecipeSkills recipeSkills;
    private @NonNull Collection<Food> foods;
    private @NonNull MemberDTO creator;
    private @NonNull List<FoodTag> foodTag;
}
