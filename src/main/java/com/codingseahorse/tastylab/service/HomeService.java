package com.codingseahorse.tastylab.service;

import com.codingseahorse.tastylab.dto.HomeDTO;
import com.codingseahorse.tastylab.dto.RecipeDTO;
import com.codingseahorse.tastylab.dto.converter.Converter;
import com.codingseahorse.tastylab.exception.BadRequestException;
import com.codingseahorse.tastylab.model.recipe.Recipe;
import com.codingseahorse.tastylab.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.codingseahorse.tastylab.model.recipe.RecipeStatus.EXPLORE;
import static com.codingseahorse.tastylab.model.recipe.RecipeStatus.HIGHLIGHT;

@Service
public class HomeService {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private Converter converter;

    public HomeDTO retrieveStartingContent(
            PageRequest pageRequestExploreRecipe,
            PageRequest pageRequestHighlightRecipe
    ){
        List<Recipe> exploreRecipeList = recipeRepository.getAllByRecipeStatus(EXPLORE);
        List<Recipe> highlightRecipeList = recipeRepository.getAllByRecipeStatus(HIGHLIGHT);

        if (pageRequestExploreRecipe.getOffset() > 0 && pageRequestExploreRecipe.getPageSize() > exploreRecipeList.size()){
            throw new BadRequestException(String.format("This page doesn't exists. All elements are already displayed in %s page or pages. Please enter a valid page",pageRequestExploreRecipe.getOffset()));
        }
        if (pageRequestHighlightRecipe.getOffset() > 0 && pageRequestHighlightRecipe.getPageSize() > highlightRecipeList.size()){
            throw new BadRequestException(String.format("This page doesn't exists. All elements are already displayed in %s page or pages. Please enter a valid page",pageRequestExploreRecipe.getOffset()));
        }

        List<RecipeDTO> exploreRecipeDTOList = converter.convertRecipeListToRecipeDTOList(exploreRecipeList);
        List<RecipeDTO> highlightRecipeDTOList = converter.convertRecipeListToRecipeDTOList(highlightRecipeList);

        final Page<RecipeDTO> exploreRecipes = converter.convertRecipeDTOListToPageOfRecipeDTO(exploreRecipeDTOList,pageRequestExploreRecipe);
        final Page<RecipeDTO> highlightRecipes = converter.convertRecipeDTOListToPageOfRecipeDTO(highlightRecipeDTOList,pageRequestHighlightRecipe);

        HomeDTO homeDTO = new HomeDTO();

        homeDTO.setExploreRecipes(exploreRecipes);
        homeDTO.setHighlightRecipes(highlightRecipes);

        return homeDTO;
    }
}
