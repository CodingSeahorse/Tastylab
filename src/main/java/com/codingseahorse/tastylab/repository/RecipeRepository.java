package com.codingseahorse.tastylab.repository;

import com.codingseahorse.tastylab.model.recipe.Recipe;
import com.codingseahorse.tastylab.model.recipe.RecipeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe,Integer>{
    List<Recipe> getAllByRecipeStatus(RecipeStatus type);

    List<Recipe> getAllByCreatorEmail(String creatorEmail);

    List<Recipe> getRecipeByCreator_FirstName(String firstName);

    boolean existsByRecipeNameAndCreatorEmail(String recipeName,String creatorEmail);
}
