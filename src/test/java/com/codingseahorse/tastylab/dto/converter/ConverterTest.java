package com.codingseahorse.tastylab.dto.converter;

import com.codingseahorse.tastylab.dto.MemberCardDTO;
import com.codingseahorse.tastylab.dto.MemberDTO;
import com.codingseahorse.tastylab.dto.RecipeDTO;
import com.codingseahorse.tastylab.model.member.Member;
import com.codingseahorse.tastylab.model.member.MemberCard;
import com.codingseahorse.tastylab.model.recipe.Food;
import com.codingseahorse.tastylab.model.recipe.FoodTag;
import com.codingseahorse.tastylab.model.recipe.Recipe;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.codingseahorse.tastylab.model.member.Gender.FEMALE;
import static com.codingseahorse.tastylab.model.member.Gender.MALE;
import static com.codingseahorse.tastylab.model.recipe.RecipeSkills.EASY;
import static com.codingseahorse.tastylab.model.recipe.RecipeSkills.MIDDLE;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConverterTest {
    @Mock
    private ModelMapper mockModelMapper;
    @InjectMocks
    private Converter converter;

    @Test
    public void convertToEntityRecipe_callsModelMapper(){
        //arrange
        Collection<Food> foods = new ArrayList<>();
        List<FoodTag> foodTags = new ArrayList<>();

        foodTags.add(new FoodTag("Frensh Toast"));
        foods.add(Food.TOMATO);

        MemberDTO memberDTO = new MemberDTO(
                "Peter",
                "Parker",
                26,
                MALE
        );
        memberDTO.setEmail("Peter.Parker@gmail.com");

        RecipeDTO recipeDTO = new RecipeDTO(
                LocalDateTime.now(),
                "frensh toast",
                40,
                EASY,
                foods,
                memberDTO,
                foodTags
        );
        //act
        converter.convertToEntityRecipe(recipeDTO);
        //assert
        verify(mockModelMapper).map(recipeDTO, Recipe.class);
    }

    @Test
    public void convertToRecipeDTO_callsModelMapper(){
        //arrange
        Collection<Food> foods = new ArrayList<>();
        List<FoodTag> foodTags = new ArrayList<>();

        foods.add(Food.TOMATO);
        foodTags.add(new FoodTag("crepe"));

        MemberCard memberCard = new MemberCard(
                LocalDateTime.now(),
                "britneyS",
                "12345"
        );

        Member member = new Member(
                "Britney",
                "Spears",
                "oops@gmail.com",
                37,
                FEMALE,
                memberCard
        );

        Recipe recipe = new Recipe(
                LocalDateTime.now(),
                "Crepe",
                25,
                EASY,
                foods,
                member,
                foodTags
        );
        // act
        converter.convertToRecipeDTO(recipe);
        // assert
        verify(mockModelMapper).map(recipe,RecipeDTO.class);

    }

    @Test
    public void convertToEntityMember_callsModelMapper(){
        // arrange
        MemberDTO memberDTO = new MemberDTO(
                "Peter",
                "Parker",
                25,
                MALE
        );
        memberDTO.setEmail("Peter.Parker@gmail.com");
        // act
        converter.convertToEntityMember(memberDTO);
        // assert
        verify(mockModelMapper).map(memberDTO,Member.class);
    }

    @Test
    public void convertToMemberDTO_callsModelMapper(){
        // arrange
        MemberCard memberCard = new MemberCard(
                LocalDateTime.now(),
                "scooby",
                "shaggy"
        );

        Member member = new Member(
                "Scooby",
                "Doo",
                "ScoobyDoobyDoo@gmail.com",
                8,
                MALE,
                memberCard
        );
        // act
        converter.convertToMemberDTO(member);
        // assert
        verify(mockModelMapper).map(member,MemberDTO.class);
    }

    @Test
    public void convertToEntityMemberCard_callsModelMapper(){
        // arrange
        MemberCardDTO memberCardDTO = new MemberCardDTO(
                "shaggy",
                "scooby"
        );
        // act
        converter.convertToEntityMemberCard(memberCardDTO);
        // assert
        verify(mockModelMapper).map(memberCardDTO,MemberCard.class);
    }

    @Test
    public void convertToMemberCardDTO_callsModelMapper(){
        // arrange
        MemberCard memberCard = new MemberCard(
                LocalDateTime.now(),
                "velma",
                "daphne"
        );
        // act
        converter.convertToMemberCardDTO(memberCard);
        // assert
        verify(mockModelMapper).map(memberCard,MemberCardDTO.class);
    }

    @Test
    public void convertRecipeDTOListToPageOfRecipeDTO_callsModelMapper(){
        // arrange
        List<RecipeDTO> recipeDTOList = new ArrayList<>();
        Collection<Food> foodCollection = new ArrayList<>();
        List<FoodTag> foodTags = new ArrayList<>();

        MemberDTO memberDTO = new MemberDTO(
                "Daphne",
                "Doo",
                24,
                FEMALE
        );

        PageRequest pageRequest = PageRequest.of(
                0,
                3,
                Sort.by("createdAt")
        );

        foodCollection.add(Food.BUTTER);
        foodCollection.add(Food.LEMON);

        foodTags.add(new FoodTag("Steak"));

        recipeDTOList.add(new RecipeDTO(
                LocalDateTime.now(),
                "Steak",
                30,
                MIDDLE,
                foodCollection,
                memberDTO,
                foodTags
        ));
        // act
        Page<RecipeDTO> convertedRecipeDTOPage = converter.convertRecipeDTOListToPageOfRecipeDTO(recipeDTOList, pageRequest);
        // assert
        assertThat(convertedRecipeDTOPage)
                .isNotNull()
                .isNotEmpty()
                .isInstanceOf(Page.class);
    }

    @Test
    public void check_if_Recipe_Entity_List_converts_into_List_of_RecipeDTO(){
        // arrange
        List<Recipe> recipeList = new ArrayList<>();
        List<FoodTag> foodTags = new ArrayList<>();
        Collection<Food> foodCollection = new ArrayList<>();

        Member member = new Member(
                "Fred",
                "Jones",
                "Fred.Jons@gmail.com",
                29,
                MALE,
                new MemberCard(
                        LocalDateTime.now(),
                        "Freddy",
                        "Daphne"
                )
        );

        foodTags.add(new FoodTag("Fish"));
        foodCollection.add(Food.BUTTER);

        recipeList.add(
                new Recipe(
                        LocalDateTime.now(),
                        "Fish",
                        20,
                        EASY,
                        foodCollection,
                        member,
                        foodTags
                )
        );
        // act
        List<RecipeDTO> convertedListRecipeDTO = converter.convertRecipeListToRecipeDTOList(recipeList);
        // assert
        assertThat(convertedListRecipeDTO)
                .isNotNull()
                .isNotEmpty()
                .isInstanceOf(List.class);
    }
}