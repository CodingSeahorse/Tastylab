package com.codingseahorse.tastylab.repository;

import com.codingseahorse.tastylab.model.member.Gender;
import com.codingseahorse.tastylab.model.member.Member;
import com.codingseahorse.tastylab.model.member.MemberCard;
import com.codingseahorse.tastylab.model.recipe.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class RecipeRepositoryTest {

    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    MemberCardRepository memberCardRepository;
    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    public void create_recipe_with_member_and_memberCard(){
        // <editor-fold desc="created empty FoodCollection & FoodTags">
        Collection<Food> foodCollection = new ArrayList<>();
        List<FoodTag> foodTags = new ArrayList<>();
        // </editor-fold>
        // <editor-fold desc="added data to FoodCollection & FoodTags">
        foodCollection.add(Food.FLOUR);
        foodCollection.add(Food.MILK);
        foodCollection.add(Food.EGG);
        foodCollection.add(Food.SALT);

        foodTags.add(new FoodTag("crepe"));
        foodTags.add(new FoodTag("tasty"));
        // </editor-fold>
        // <editor-fold desc="created MemberCard">
        MemberCard memberCard = new MemberCard(
                LocalDateTime.now(),
                "shaggy",
                "123"
        );

        memberCardRepository.save(memberCard);
        // </editor-fold>
        // <editor-fold desc="created Member">
        Member shaggy = new Member(
                "shaggy",
                "Doo",
                "shaggy.doo@gmail.com",
                34,
                Gender.MALE,
                memberCard
        );
        memberRepository.save(shaggy);
        // </editor-fold>
        // <editor-fold desc="created new Recipe">
        Recipe shaggysCrepe = new Recipe(
                LocalDateTime.now(),
                "shaggys-awesomes-doo-crepe",
                20,
                RecipeSkills.EASY,
                foodCollection,
                shaggy,
                foodTags
        );

        recipeRepository.save(shaggysCrepe);
        // </editor-fold>
    }

    @Test
    void check_if_getRecipeByCreator_FirstName_methode_returns_the_right_recipes(){

        List<Recipe> getShaggysRecipes = recipeRepository.getRecipeByCreator_FirstName("shaggy");

        Recipe shaggysCrepeResipeResult =
                getShaggysRecipes
                        .stream()
                        .filter(recipe -> recipe.getCreator().getFirstName().equals("shaggy"))
                        .findFirst()
                        .orElse(null);

        assertThat(getShaggysRecipes)
                .satisfies(recipes -> {
                    assertThat(recipes.size()).isGreaterThanOrEqualTo(1);
                    assertThat(shaggysCrepeResipeResult).isNotNull();
                })
                .isInstanceOf(List.class)
                .isNotEmpty()
                .isNotNull();
    }

    @Test
    public void check_if_getRecipeByCreator_MemberId_returns_the_right_recipes(){

        List<Recipe> getRecipeByCreatorMemberIdList = recipeRepository.getRecipeByCreator_MemberId(1);

        Recipe shaggysCrepeRecipe =
                getRecipeByCreatorMemberIdList
                        .stream()
                        .filter(recipe -> recipe.getRecipeName().equals("shaggys-awesomes-doo-crepe"))
                        .findFirst()
                        .orElse(null);

        assertThat(getRecipeByCreatorMemberIdList)
                .satisfies(recipes -> {
                    assertThat(recipes.size()).isGreaterThanOrEqualTo(1);
                    assertThat(shaggysCrepeRecipe).isNotNull();
                })
                .isInstanceOf(List.class)
                .isNotEmpty()
                .isNotNull();
    }

    @Test
    public void check_if_getAllByRecipeStatus_returns_the_right_recipes(){

        List<Recipe> getAllByRecipeStatusList = recipeRepository.getAllByRecipeStatus(RecipeStatus.NORMAL); //Default Value

        Recipe shaggysCrepeRecipe =
                getAllByRecipeStatusList
                        .stream()
                        .filter(recipe -> recipe.getRecipeSkills().equals(RecipeSkills.EASY))
                        .findFirst()
                        .orElse(null);

        assertThat(getAllByRecipeStatusList)
                .satisfies(recipe -> {
                    assertThat(recipe.size()).isGreaterThanOrEqualTo(1);
                    assertThat(shaggysCrepeRecipe).isNotNull();
                })
                .isInstanceOf(List.class)
                .isNotEmpty()
                .isNotNull();
    }

    @Test
    public void check_if_getRecipeByRecipeNameAndCreatorEmail_returns_the_right_recipe(){
        Recipe getRecipe =
                recipeRepository
                        .getRecipeByRecipeNameAndCreatorEmail(
                                "shaggys-awesomes-doo-crepe",
                                "shaggy.doo@gmail.com"
                        );

        assertThat(getRecipe)
                .satisfies(recipe -> {
                    assertThat(recipe.getRecipeName()).isEqualTo("shaggys-awesomes-doo-crepe");
                    assertThat(recipe.getCreator().getEmail()).isEqualTo("shaggy.doo@gmail.com");
                })
                .isInstanceOf(Recipe.class)
                .isNotNull();
    }

}