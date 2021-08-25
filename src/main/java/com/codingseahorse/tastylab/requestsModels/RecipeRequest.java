package com.codingseahorse.tastylab.requestsModels;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecipeRequest {
    String recipeName;
    Integer duration;
    String recipeSkill;
    String[] foods;
    String[] foodTags;
}
