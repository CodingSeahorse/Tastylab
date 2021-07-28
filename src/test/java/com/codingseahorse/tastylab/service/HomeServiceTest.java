package com.codingseahorse.tastylab.service;

import com.codingseahorse.tastylab.dto.HomeDTO;
import com.codingseahorse.tastylab.dto.RecipeDTO;
import com.codingseahorse.tastylab.dto.converter.Converter;
import com.codingseahorse.tastylab.model.member.Gender;
import com.codingseahorse.tastylab.model.member.Member;
import com.codingseahorse.tastylab.model.member.MemberCard;
import com.codingseahorse.tastylab.model.recipe.Food;
import com.codingseahorse.tastylab.model.recipe.FoodTag;
import com.codingseahorse.tastylab.model.recipe.Recipe;
import com.codingseahorse.tastylab.model.recipe.RecipeSkills;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class HomeServiceTest {

    @Autowired
    private Converter converter;

    @Test
    public void check_if_starting_Content_loads_in_two_pages(){
        // <editor-fold desc="PageRequest Explore & Highlight">
        PageRequest pageRequestExplore = PageRequest.of(
                0,
                3,
                Sort.by("createdAt")
        );
        PageRequest pageRequestHighlight = PageRequest.of(
                0,
                3,
                Sort.by("createdAt")
        );
        // </editor-fold>
        // <editor-fold desc="create List and Collection">
        List<Recipe> exploreRecipeList = new ArrayList<>();
        List<Recipe> highlightRecipeList = new ArrayList<>();

        Collection<Food> foodCollectionCrepe = new ArrayList<>();
        Collection<Food> foodCollectionFrenshToast = new ArrayList<>();

        List<FoodTag> foodTagsCrepe = new ArrayList<>();
        List<FoodTag> foodTagsFrenshToast = new ArrayList<>();
        // </editor-fold>
        // <editor-fold desc="List & Collection add">
        foodTagsCrepe.add(new FoodTag("crepe"));
        foodTagsFrenshToast.add(new FoodTag("frensh toast"));

        foodCollectionCrepe.add(Food.BUTTER);
        foodCollectionCrepe.add(Food.OIL);
        foodCollectionCrepe.add(Food.FLOUR);
        foodCollectionCrepe.add(Food.EGG);
        foodCollectionCrepe.add(Food.MILK);
        foodCollectionCrepe.add(Food.SALT);

        foodCollectionFrenshToast.add(Food.BUTTER);
        foodCollectionFrenshToast.add(Food.OIL);
        foodCollectionFrenshToast.add(Food.APPLE);
        foodCollectionFrenshToast.add(Food.FLOUR);
        foodCollectionFrenshToast.add(Food.EGG);
        foodCollectionFrenshToast.add(Food.MILK);
        // </editor-fold>
        // <editor-fold desc="create Member(creator) and two Recipes">
        Member member = new Member(
                "Shaggy",
                "Doo",
                "shaggy.doo@gmail.com",
                24,
                Gender.MALE,
                new MemberCard(
                        LocalDateTime.now(),
                        "shaggy",
                        "123Doo"
                )
        );

        Recipe crepe = new Recipe(
                LocalDateTime.now(),
                "crepe with banana",
                40,
                RecipeSkills.EASY,
                foodCollectionCrepe,
                member,
                foodTagsCrepe
        );

        Recipe frenshToast = new Recipe(
                LocalDateTime.now(),
                "frensh toast",
                30,
                RecipeSkills.MIDDLE,
                foodCollectionFrenshToast,
                member,
                foodTagsFrenshToast
        );
        // </editor-fold>
        // <editor-fold desc="add explore- and highlightRecipeList">
        exploreRecipeList.add(crepe);
        highlightRecipeList.add(frenshToast);
        // </editor-fold>
        // <editor-fold desc="create List<RecipeDTO>'s and Page<RecipeDTO>'s ">
        List<RecipeDTO> exploreRecipeDTOList = converter.convertRecipeListToRecipeDTOList(exploreRecipeList);
        List<RecipeDTO> highlightRecipeDTOList = converter.convertRecipeListToRecipeDTOList(highlightRecipeList);

        Page<RecipeDTO> exploreRecipePage = converter.convertRecipeDTOListToPageOfRecipeDTO(exploreRecipeDTOList,pageRequestExplore);
        Page<RecipeDTO> highlightRecipePage = converter.convertRecipeDTOListToPageOfRecipeDTO(highlightRecipeDTOList,pageRequestHighlight);
        // </editor-fold>
        // <editor-fold desc="create homeDTO">
        HomeDTO homeDTO = new HomeDTO();
        homeDTO.setExploreRecipes(exploreRecipePage);
        homeDTO.setHighlightRecipes(highlightRecipePage);
        // </editor-fold>
        assertThat(homeDTO)
                .satisfies(explore -> {
                    assertThat(explore.getExploreRecipes().getTotalElements()).isGreaterThanOrEqualTo(1).isNotNull();
                    assertThat(explore.getExploreRecipes().getTotalPages()).isEqualTo(1).isNotNull();
                    assertThat(explore.getHighlightRecipes().getTotalElements()).isGreaterThanOrEqualTo(1).isNotNull();
                    assertThat(explore.getHighlightRecipes().getTotalPages()).isEqualTo(1).isNotNull();
                })
                .isInstanceOf(HomeDTO.class)
                .isNotNull();
    }
}