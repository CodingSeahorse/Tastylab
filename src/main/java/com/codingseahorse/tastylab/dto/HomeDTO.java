package com.codingseahorse.tastylab.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class HomeDTO {
    private Page<RecipeDTO> exploreRecipes;
    private Page<RecipeDTO> highlightRecipes;
}
