package com.codingseahorse.tastylab.service;

import com.codingseahorse.tastylab.dto.HomeDTO;
import com.codingseahorse.tastylab.dto.LizzyDTO;
import com.codingseahorse.tastylab.dto.MemberDTO;
import com.codingseahorse.tastylab.dto.RecipeDTO;
import com.codingseahorse.tastylab.dto.converter.Converter;
import com.codingseahorse.tastylab.exception.BadRequestException;
import com.codingseahorse.tastylab.exception.NotFoundException;
import com.codingseahorse.tastylab.model.member.Member;
import com.codingseahorse.tastylab.model.recipe.Food;
import com.codingseahorse.tastylab.model.recipe.FoodTag;
import com.codingseahorse.tastylab.model.recipe.Recipe;
import com.codingseahorse.tastylab.repository.FoodTagRepository;
import com.codingseahorse.tastylab.repository.MemberRepository;
import com.codingseahorse.tastylab.repository.RecipeRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.codingseahorse.tastylab.model.recipe.RecipeStatus.EXPLORE;
import static com.codingseahorse.tastylab.model.recipe.RecipeStatus.HIGHLIGHT;

@Service
@NoArgsConstructor
public class RecipeService {
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    FoodTagRepository foodTagRepository;
    @Autowired
    Converter converter;

    // ===== CREATE =====
    public void createRecipe(RecipeDTO recipeDTO) {
        boolean recipeSearch =
                recipeRepository.existsByRecipeNameAndCreatorEmail(
                                recipeDTO.getRecipeName(),
                                recipeDTO.getCreatorEmail());

        if (recipeSearch) {
            throw new BadRequestException(String.format(
                    "You already created the Food with the name:%s",
                    recipeDTO.getRecipeName()));
        }

        Member findCreator =
                memberRepository.getMemberByEmail(recipeDTO.getCreatorEmail());

        Set<String> tagNames =
                recipeDTO
                        .getFoodTag()
                        .stream()
                            .map(FoodTag::getTagName)
                            .collect(Collectors.toSet());

        for(String tags:tagNames) {
            boolean alreadyExists = foodTagRepository.existsByTagName(tags);

            if (alreadyExists) {
                throw new BadRequestException("Already existed FoodTag: " + tags);
            }
        }

        foodTagRepository.saveAll(recipeDTO.getFoodTag());

        Recipe newRecipe = new Recipe(
                LocalDateTime.now(),
                recipeDTO.getRecipeName(),
                recipeDTO.getDuration(),
                recipeDTO.getRecipeSkills(),
                recipeDTO.getFoods(),
                findCreator,
                recipeDTO.getFoodTag());

        recipeRepository.save(newRecipe);
    }

    // ===== READ =====
    public MemberDTO retrieveRecipesFromMember(
            String username,
            PageRequest pageRequest) {

        Member member = memberRepository.getMemberByMembercardUsername(username);

        MemberDTO memberDTO = new MemberDTO(
                member.getFirstName(),
                member.getLastName(),
                member.getAge(),
                member.getGender()
        );

        List<Recipe> findRecipes =
                recipeRepository.getAllByCreatorEmail(member.getEmail());

        List<RecipeDTO> newRecipeList =
                converter.convertRecipeListToRecipeDTOList(findRecipes);

        final Page<RecipeDTO> page =
                converter.convertRecipeDTOListToPageOfRecipeDTO(
                            newRecipeList,
                            pageRequest);

        memberDTO.setRecipes(page);

        return memberDTO;
    }

    public HomeDTO retrieveStartingContent(
            PageRequest pageRequestExploreRecipe,
            PageRequest pageRequestHighlightRecipe
    ) {
        List<Recipe> exploreRecipeList =
                recipeRepository.getAllByRecipeStatus(EXPLORE);
        List<Recipe> highlightRecipeList =
                recipeRepository.getAllByRecipeStatus(HIGHLIGHT);

        if (pageRequestExploreRecipe.getOffset() > 0 &&
            pageRequestExploreRecipe.getPageSize() > exploreRecipeList.size()) {
            throw new BadRequestException(String.format(
                    "This page doesn't exists. " +
                    "All elements are already displayed in %s page or pages." +
                    " Please enter a valid page",
                    pageRequestExploreRecipe.getOffset()));
        }

        if (pageRequestHighlightRecipe.getOffset() > 0 &&
            pageRequestHighlightRecipe.getPageSize() > highlightRecipeList.size()) {
            throw new BadRequestException(String.format(
                    "This page doesn't exists. " +
                    "All elements are already displayed in %s page or pages." +
                    " Please enter a valid page",
                    pageRequestHighlightRecipe.getOffset()));
        }

        List<RecipeDTO> exploreRecipeDTOList =
                converter.convertRecipeListToRecipeDTOList(exploreRecipeList);
        List<RecipeDTO> highlightRecipeDTOList =
                converter.convertRecipeListToRecipeDTOList(highlightRecipeList);

        final Page<RecipeDTO> exploreRecipes =
                converter.convertRecipeDTOListToPageOfRecipeDTO(
                            exploreRecipeDTOList,
                            pageRequestExploreRecipe);
        final Page<RecipeDTO> highlightRecipes =
                converter.convertRecipeDTOListToPageOfRecipeDTO(
                            highlightRecipeDTOList,
                            pageRequestHighlightRecipe);

        HomeDTO homeDTO = new HomeDTO();

        homeDTO.setExploreRecipes(exploreRecipes);
        homeDTO.setHighlightRecipes(highlightRecipes);

        return homeDTO;
    }

    public LizzyDTO findRecipeByElements(String[] food, PageRequest pageRequest){
        try {
            Collection<Food> searchFoodList = Arrays
                    .stream(food)
                    .map(String::toUpperCase)
                    .map(Food::valueOf)
                    .collect(Collectors.toList());

            List<Recipe> searchedRecipes =
                    recipeRepository.getRecipesByFoodsCollection(searchFoodList);

            List<RecipeDTO> searchedRecipesConverted =
                    converter.convertRecipeListToRecipeDTOList(searchedRecipes);

            final Page<RecipeDTO> resultRecipes =
                    converter.convertRecipeDTOListToPageOfRecipeDTO(
                            searchedRecipesConverted,
                            pageRequest);

            LizzyDTO lizzyDTO = new LizzyDTO();
            lizzyDTO.setResultList(resultRecipes);

            return lizzyDTO;
        }catch (IllegalArgumentException e){
            throw new NotFoundException(
                    "Food not found." +
                    Arrays.toString(e.getStackTrace()));
        }
    }
}
