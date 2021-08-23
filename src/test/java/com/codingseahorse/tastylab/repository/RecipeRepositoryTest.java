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

    // <editor-fold defaultstate="collapsed" desc="FoodCollection & FoodTags">
    Collection<Food> foodCollection = new ArrayList<>();
    List<FoodTag> foodTags = new ArrayList<>();
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="MemberCard, Member & Recipe">
    MemberCard memberCard = new MemberCard(
            LocalDateTime.now(),
            "shaggy",
            "123"
    );
    Member shaggy = new Member(
            "shaggy",
            "Doo",
            "shaggy.doo@gmail.com",
            34,
            Gender.MALE,
            memberCard
    );
    Recipe shaggysCrepe = new Recipe(
            LocalDateTime.now(),
            "shaggys-awesome-doo-crepe",
            20,
            RecipeSkills.EASY,
            foodCollection,
            shaggy,
            foodTags
    );
    // </editor-fold>

    @BeforeEach
    void setup(){
        // <editor-fold defaultstate="collapsed" desc="added data to FoodCollection & FoodTags">
        foodCollection.add(Food.FLOUR);
        foodCollection.add(Food.MILK);
        foodCollection.add(Food.EGG);
        foodCollection.add(Food.SALT);

        foodTags.add(new FoodTag("crepe"));
        foodTags.add(new FoodTag("tasty"));
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="save MemberCard,Member,MembersRecipe">
        memberCardRepository.save(memberCard);
        memberRepository.save(shaggy);
        recipeRepository.save(shaggysCrepe);
        // </editor-fold>
    }

    @Test
    void should_getAllByRecipeStatus(){

        List<Recipe> recipeList = recipeRepository.getAllByRecipeStatus(RecipeStatus.NORMAL); //Default

        assertThat(recipeList)
                .isNotNull()
                .isNotEmpty()
                .contains(shaggysCrepe);
    }

    @Test
    void should_getAllByCreatorEmail(){

        List<Recipe> getRecipeByCreatorMemberIdList = recipeRepository.getAllByCreatorEmail(shaggy.getEmail());

        assertThat(getRecipeByCreatorMemberIdList)
                .isNotNull()
                .isNotEmpty()
                .contains(shaggysCrepe);
    }


    @Test
    void should_getRecipeByCreator_FirstName(){

        List<Recipe> getShaggysRecipes = recipeRepository.getRecipeByCreator_FirstName("shaggy");

        assertThat(getShaggysRecipes)
                .isNotNull()
                .isNotEmpty()
                .contains(shaggysCrepe);
    }

    @Test
    void should_getRecipeByRecipeNameAndCreatorEmail_returns_true_or_false(){
        boolean existedRecipe =
                recipeRepository
                        .existsByRecipeNameAndCreatorEmail(
                                "shaggys-awesome-doo-crepe",
                                "shaggy.doo@gmail.com"
                        );

        assertThat(existedRecipe)
                .isTrue()
                .isNotNull();
    }
}
