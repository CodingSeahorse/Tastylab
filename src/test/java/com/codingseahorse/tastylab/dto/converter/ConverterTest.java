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

    // <editor-fold defaultstate="collapsed" desc="created Lists and Collection">
    Collection<Food> foods = new ArrayList<>();
    List<FoodTag> foodTags = new ArrayList<>();
    List<Recipe> recipeList = new ArrayList<>();
    List<RecipeDTO> recipeDTOList = new ArrayList<>();
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="created MemberCard,Member,Recipe,MemberCardDTO,MemberDTO,RecipeDTO + PageRequest">
    MemberCard memberCard = new MemberCard(
            LocalDateTime.now(),
            "britneyS",
            "12345");

    Member member = new Member(
            "Britney",
            "Spears",
            "oops@gmail.com",
            39,
            FEMALE,
            memberCard);

    Recipe recipe = new Recipe(
            LocalDateTime.now(),
            "Crepe",
            25,
            EASY,
            foods,
            member,
            foodTags);

    MemberCardDTO memberCardDTO = new MemberCardDTO(
            "shaggy",
            "scooby");

    MemberDTO memberDTO = new MemberDTO(
            "Peter",
            "Parker",
            26,
            MALE);

    RecipeDTO recipeDTO = new RecipeDTO(
            LocalDateTime.now(),
            "frensh toast",
            40,
            EASY,
            foods,
            memberDTO,
            foodTags);

    PageRequest pageRequest = PageRequest.of(
            0,
            3,
            Sort.by("createdAt"));
    // </editor-fold>

    @Test
    void convertToEntityRecipe_callsModelMapper() {
        //arrange
        foodTags.add(new FoodTag("Frensh Toast"));
        foods.add(Food.TOMATO);

        memberDTO.setEmail("Peter.Parker@gmail.com");
        //act
        converter.convertToEntityRecipe(recipeDTO);
        //assert
        verify(mockModelMapper).map(recipeDTO, Recipe.class);
    }

    @Test
    void convertToRecipeDTO_callsModelMapper() {
        //arrange
        foods.add(Food.TOMATO);
        foodTags.add(new FoodTag("crepe"));
        // act
        converter.convertToRecipeDTO(recipe);
        // assert
        verify(mockModelMapper).map(recipe,RecipeDTO.class);
    }

    @Test
    void convertToEntityMember_callsModelMapper() {
        // arrange
        memberDTO.setEmail("Peter.Parker@gmail.com");
        // act
        converter.convertToEntityMember(memberDTO);
        // assert
        verify(mockModelMapper).map(memberDTO,Member.class);
    }

    @Test
    void convertToMemberDTO_callsModelMapper() {
        // act
        converter.convertToMemberDTO(member);
        // assert
        verify(mockModelMapper).map(member,MemberDTO.class);
    }

    @Test
    void convertToEntityMemberCard_callsModelMapper() {
        // act
        converter.convertToEntityMemberCard(memberCardDTO);
        // assert
        verify(mockModelMapper).map(memberCardDTO,MemberCard.class);
    }

    @Test
    void convertToMemberCardDTO_callsModelMapper() {
        // act
        converter.convertToMemberCardDTO(memberCard);
        // assert
        verify(mockModelMapper).map(memberCard,MemberCardDTO.class);
    }

    @Test
    void convertRecipeDTOListToPageOfRecipeDTO_callsModelMapper() {
        // arrange
        foods.add(Food.BUTTER);
        foods.add(Food.LEMON);
        foodTags.add(new FoodTag("Steak"));
        recipeDTOList.add(new RecipeDTO(
                LocalDateTime.now(),
                "Steak",
                30,
                MIDDLE,
                foods,
                memberDTO,
                foodTags));
        // act
        Page<RecipeDTO> convertedRecipeDTOPage =
                converter.convertRecipeDTOListToPageOfRecipeDTO(
                        recipeDTOList,
                        pageRequest);
        // assert
        assertThat(convertedRecipeDTOPage)
                .isNotNull()
                .isNotEmpty()
                .isInstanceOf(Page.class);
    }

    @Test
    void check_if_Recipe_Entity_List_converts_into_List_of_RecipeDTO() {
        // arrange
        foodTags.add(new FoodTag("Fish"));
        foods.add(Food.BUTTER);
        recipeList.add(
                new Recipe(
                        LocalDateTime.now(),
                        "Fish",
                        20,
                        EASY,
                        foods,
                        member,
                        foodTags));
        // act
        List<RecipeDTO> convertedListRecipeDTO =
                converter.convertRecipeListToRecipeDTOList(recipeList);
        // assert
        assertThat(convertedListRecipeDTO)
                .isNotNull()
                .isNotEmpty()
                .isInstanceOf(List.class);
    }

    @Test
    void check_if_converter_is_not_null() {
        Converter converter = new Converter();

        assertThat(converter)
                .isNotNull();
    }
}