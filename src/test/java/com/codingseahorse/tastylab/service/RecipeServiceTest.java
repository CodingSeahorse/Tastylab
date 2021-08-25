package com.codingseahorse.tastylab.service;

import com.codingseahorse.tastylab.dto.HomeDTO;
import com.codingseahorse.tastylab.dto.MemberCardDTO;
import com.codingseahorse.tastylab.dto.MemberDTO;
import com.codingseahorse.tastylab.dto.RecipeDTO;
import com.codingseahorse.tastylab.dto.converter.Converter;
import com.codingseahorse.tastylab.exception.BadRequestException;
import com.codingseahorse.tastylab.model.member.Gender;
import com.codingseahorse.tastylab.model.member.Member;
import com.codingseahorse.tastylab.model.member.MemberCard;
import com.codingseahorse.tastylab.model.recipe.*;
import com.codingseahorse.tastylab.repository.MemberRepository;
import com.codingseahorse.tastylab.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {
    @Mock
    RecipeRepository recipeRepository;
    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    RecipeService recipeService;
    @Spy
    Converter converter;

    // <editor-fold desc="created Collection & Lists">
    Collection<Food> foodCollection = new ArrayList<>();
    List<FoodTag> foodTagList = new ArrayList<>();
    List<Recipe> stromaeRecipeList = new ArrayList<>();
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="created MemberCardDTO,MemberDTO & RecipeDTO">
    MemberCardDTO memberCardDTO = new MemberCardDTO(
            "scooby",
            "doo");

    MemberDTO memberDTO = new MemberDTO(
            "sara",
            "lance",
            34,
            Gender.FEMALE);

    RecipeDTO recipeDTO = new RecipeDTO(
            LocalDateTime.now(),
            "Muffin",
            40,
            RecipeSkills.EASY,
            foodCollection,
            memberDTO,
            foodTagList);
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="created MemberCard & Member">
    MemberCard memberCardReturn = new MemberCard(
            LocalDateTime.now(),
            "StromaeFreshy",
            "123");

    Member memberReturn = new Member(
            "Stromae",
            "fresh",
            "Stromae.fresh@beat.com",
            34,
            Gender.QUEERGENDER,
            memberCardReturn);
    // </editor-fold>

    @BeforeEach
    void setup() {
        // <editor-fold defaultstate="collapsed" desc="added data to FoodCollection & FoodTags">
        foodCollection.add(Food.FLOUR);
        foodCollection.add(Food.EGG);
        foodCollection.add(Food.MILK);

        foodTagList.add(new FoodTag("tasty"));
        foodTagList.add(new FoodTag("muffin"));
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="setEmail & MemberCardDTO memberDTO">
        memberDTO.setEmail("sara.lance@gmail.com");
        memberDTO.setMemberCardDTO(memberCardDTO);
        // </editor-fold>
    }

    @Test
    void should_createRecipe() {
        given(memberRepository
                .getMemberByEmailAndFirstName(
                        anyString(),
                        anyString()))
                    .willReturn(memberReturn);

        recipeService.createRecipe(recipeDTO);

        verify(recipeRepository,times(1))
                .save(any(Recipe.class));
    }

    @Test
    void will_throw_when_member_already_created_a_food_with_this_name() {
        given(recipeRepository
                .existsByRecipeNameAndCreatorEmail(
                        "Muffin",
                        "sara.lance@gmail.com"))
                    .willReturn(true);

        assertThatThrownBy(
                () -> recipeService.createRecipe(recipeDTO))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining(
                        "You already created the Food with the name:%s",
                        recipeDTO.getRecipeName());
    }

    @Test
    void should_retrieveRecipesFromMember() {
        // <editor-fold defaultstate="collapsed" desc="created MemberCard,Member,Recipe + PageRequest">
        PageRequest pageRequestMemberRecipes = PageRequest.of(
         0,
         3,
         Sort.by("createdAt"));

        MemberCard stromaeMemberCard = new MemberCard(
                LocalDateTime.now(),
                "stromae",
                "123");

        Member stromaeMember = new Member(
                "Stromae",
                "Freshy",
                "Stromae.Freshy@world.org",
                34,
                Gender.MALE,
                stromaeMemberCard);

        Recipe stromaeRecipe = new Recipe(
                LocalDateTime.now(),
                "crepe",
                20,
                RecipeSkills.EASY,
                foodCollection,
                stromaeMember,
                foodTagList);
        // </editor-fold>

        stromaeRecipeList.add(stromaeRecipe);

        given(memberRepository
                .getMemberByMembercardUsername(anyString()))
                    .willReturn(memberReturn);
        given(recipeRepository
                .getAllByCreatorEmail(anyString()))
                    .willReturn(stromaeRecipeList);

        MemberDTO result =
                recipeService
                        .retrieveRecipesFromMember(
                                "stromae",
                                pageRequestMemberRecipes);

        assertThat(result)
                .isNotNull();
    }

    @Test
    void should_retrieve_StartingContent_of_Home() {
        // <editor-fold defaultstate="collapsed" desc="created PageRequest Explore & Highlight">
        PageRequest pageRequestExplore = PageRequest.of(
                0,
                3,
                Sort.by("createdAt"));

        PageRequest pageRequestHighlight = PageRequest.of(
                0,
                3,
                Sort.by("createdAt"));
        // </editor-fold>

        recipeService
                .retrieveStartingContent(
                        pageRequestExplore,
                        pageRequestHighlight);

        verify(recipeRepository,times(2))
                .getAllByRecipeStatus(any());
    }

    @Test
    void retrieveStartingContent_should_throw_Exception_if_pageRequestExploreRecipe_page_count_is_bigger_then_page_size() {
        // <editor-fold defaultstate="collapsed" desc="created PageRequest(normal_data)">
        PageRequest pageRequestExplore = PageRequest.of(
                0,
                3,
                Sort.by("createdAt"));

        PageRequest pageRequestHighlight = PageRequest.of(
                0,
                3,
                Sort.by("createdAt"));
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="created PageRequest(exception)">
        PageRequest pageRequestExploreException = PageRequest.of(
                6,
                3,
                Sort.by("createdAt"));
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="created two RecipeDTO's">
        RecipeDTO recipeDTOforExplore = new RecipeDTO(
                LocalDateTime.now(),
                "Grandmother's Cookies",
                40,
                RecipeSkills.MIDDLE,
                foodCollection,
                memberDTO,
                foodTagList);

        RecipeDTO recipeDTOforHighlight = new RecipeDTO(
                LocalDateTime.now(),
                "Grandfather's Cookies",
                40,
                RecipeSkills.PROFESSIONAL,
                foodCollection,
                memberDTO,
                foodTagList);
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="created two empty List<RecipeDTO>">
        List<RecipeDTO> recipeDTOListExplore = new ArrayList<>();
        List<RecipeDTO> recipeDTOListHighlight = new ArrayList<>();
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="added data to two List<RecipeDTO>">
        recipeDTOListExplore.add(recipeDTOforExplore);
        recipeDTOListHighlight.add(recipeDTOforHighlight);
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="created two Page<RecipeDTO> (explore&highlight)">
        Page<RecipeDTO> recipeDTOPageExplore =
                converter.convertRecipeDTOListToPageOfRecipeDTO(
                                recipeDTOListExplore,
                                pageRequestExplore);

        Page<RecipeDTO> recipeDTOPageHighlight =
                converter.convertRecipeDTOListToPageOfRecipeDTO(
                                recipeDTOListHighlight,
                                pageRequestHighlight);
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="created HomeDTO & added the two Page<RecipeDTO>">
        HomeDTO homeDTO = new HomeDTO();
        homeDTO.setExploreRecipes(recipeDTOPageExplore);
        homeDTO.setHighlightRecipes(recipeDTOPageHighlight);
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="created two List<Recipe>">
        List<Recipe> returnListExplore = new ArrayList<>();
        List<Recipe> returnListHighlight = new ArrayList<>();
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="created MemberCard,Member & Recipe">
        MemberCard memberCardX = new MemberCard(
                LocalDateTime.now(),
                memberCardDTO.getUsername(),
                memberCardDTO.getPassword());

        Member memberX = new Member(
                memberDTO.getFirstName(),
                memberDTO.getLastName(),
                memberDTO.getEmail(),
                memberDTO.getAge(),
                memberDTO.getGender(),
                memberCardX);

        Recipe recipeX = new Recipe(
                LocalDateTime.now(),
                recipeDTO.getRecipeName(),
                recipeDTO.getDuration(),
                recipeDTO.getRecipeSkills(),
                recipeDTOforExplore.getFoods(),
                memberX,
                foodTagList);
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="added data to the two returnList">
        returnListExplore.add(recipeX);
        returnListHighlight.add(recipeX);
        // </editor-fold>

        given(recipeRepository
                .getAllByRecipeStatus(RecipeStatus.EXPLORE))
                    .willReturn(returnListExplore);
        given(recipeRepository
                .getAllByRecipeStatus(RecipeStatus.HIGHLIGHT))
                    .willReturn(returnListHighlight);

        assertThatThrownBy(
                () -> recipeService
                        .retrieveStartingContent(
                                pageRequestExploreException,
                                pageRequestHighlight))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining(
                      "This page doesn't exists." +
                                " All elements are already displayed in %s page or pages." +
                                " Please enter a valid page",
                                pageRequestExploreException.getOffset());
    }
    @Test
    void retrieveStartingContent_should_throw_Exception_if_pageRequestHighlightRecipe_page_count_is_bigger_then_page_size() {
        // <editor-fold defaultstate="collapsed" desc="created PageRequest(normal_data)">
        PageRequest pageRequestExplore = PageRequest.of(
                0,
                3,
                Sort.by("createdAt"));

        PageRequest pageRequestHighlight = PageRequest.of(
                0,
                3,
                Sort.by("createdAt"));
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="created PageRequest(exception)">
        PageRequest pageRequestHighlightException = PageRequest.of(
                6,
                3,
                Sort.by("createdAt"));
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="created two RecipeDTO's">
        RecipeDTO recipeDTOforExplore = new RecipeDTO(
                LocalDateTime.now(),
                "Grandmother's Cookies",
                40,
                RecipeSkills.MIDDLE,
                foodCollection,
                memberDTO,
                foodTagList);

        RecipeDTO recipeDTOforHighlight = new RecipeDTO(
                LocalDateTime.now(),
                "Grandfather's Cookies",
                40,
                RecipeSkills.PROFESSIONAL,
                foodCollection,
                memberDTO,
                foodTagList);
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="created two empty List<RecipeDTO>">
        List<RecipeDTO> recipeDTOListExplore = new ArrayList<>();
        List<RecipeDTO> recipeDTOListHighlight = new ArrayList<>();
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="added data to two List<RecipeDTO>">
        recipeDTOListExplore.add(recipeDTOforExplore);
        recipeDTOListHighlight.add(recipeDTOforHighlight);
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="created two Page<RecipeDTO> (explore&highlight)">
        Page<RecipeDTO> recipeDTOPageExplore =
                converter.convertRecipeDTOListToPageOfRecipeDTO(
                            recipeDTOListExplore,
                            pageRequestExplore);

        Page<RecipeDTO> recipeDTOPageHighlight =
                converter.convertRecipeDTOListToPageOfRecipeDTO(
                            recipeDTOListHighlight,
                            pageRequestHighlight);
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="created HomeDTO & added the two Page<RecipeDTO>">
        HomeDTO homeDTO = new HomeDTO();
        homeDTO.setExploreRecipes(recipeDTOPageExplore);
        homeDTO.setHighlightRecipes(recipeDTOPageHighlight);
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="created two List<Recipe>">
        List<Recipe> returnListExplore = new ArrayList<>();
        List<Recipe> returnListHighlight = new ArrayList<>();
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="created MemberCard,Member & Recipe">
        MemberCard memberCardX = new MemberCard(
                LocalDateTime.now(),
                memberCardDTO.getUsername(),
                memberCardDTO.getPassword());

        Member memberX = new Member(
                memberDTO.getFirstName(),
                memberDTO.getLastName(),
                memberDTO.getEmail(),
                memberDTO.getAge(),
                memberDTO.getGender(),
                memberCardX);

        Recipe recipeX = new Recipe(
                LocalDateTime.now(),
                recipeDTO.getRecipeName(),
                recipeDTO.getDuration(),
                recipeDTO.getRecipeSkills(),
                recipeDTOforExplore.getFoods(),
                memberX,
                foodTagList);
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="added data to the two returnList">
        returnListExplore.add(recipeX);
        returnListHighlight.add(recipeX);
        // </editor-fold>

        given(recipeRepository
                .getAllByRecipeStatus(RecipeStatus.EXPLORE))
                    .willReturn(returnListExplore);
        given(recipeRepository
                .getAllByRecipeStatus(RecipeStatus.HIGHLIGHT))
                    .willReturn(returnListHighlight);

        assertThatThrownBy(
                () -> recipeService
                        .retrieveStartingContent(
                                pageRequestExplore,
                                pageRequestHighlightException))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining(
              "This page doesn't exists. " +
                        "All elements are already displayed in %s page or pages." +
                        " Please enter a valid page",
                        pageRequestHighlightException.getOffset());
    }

    @Test
    void check_if_RecipeService_isNotNull() {
        RecipeService checkedRecipeService = new RecipeService();

        assertThat(checkedRecipeService)
                .isNotNull();
    }
}