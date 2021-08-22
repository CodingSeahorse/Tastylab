package com.codingseahorse.tastylab.requestsModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@AllArgsConstructor
public class RecipeRequest {
    String recipeName;
    Integer duration;
    String recipeSkill;
    String[] foods;
    String[] foodTags;
}
