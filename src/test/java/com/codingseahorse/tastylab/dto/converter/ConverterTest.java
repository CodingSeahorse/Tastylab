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
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.codingseahorse.tastylab.model.member.Gender.FEMALE;
import static com.codingseahorse.tastylab.model.member.Gender.MALE;
import static com.codingseahorse.tastylab.model.recipe.RecipeSkills.EASY;
import static com.codingseahorse.tastylab.model.recipe.RecipeSkills.MIDDLE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ConverterTest {
    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void check_if_the_RecipeDTO_converts_into_Recipe(){
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

        Recipe map = modelMapper.map(recipeDTO, Recipe.class);

        assertThat(map)
                .isNotNull()
                .isInstanceOf(Recipe.class);
    }

    @Test
    public void check_if_the_Recipe_converts_into_RecipeDTO(){

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

        RecipeDTO recipeDTO = modelMapper.map(recipe,RecipeDTO.class);

        assertThat(recipeDTO)
                .isNotNull()
                .isInstanceOf(RecipeDTO.class);

    }

    @Test
    public void check_if_MemberDTO_converts_into_Member_Entity(){
        MemberDTO memberDTO = new MemberDTO(
                "Peter",
                "Parker",
                25,
                MALE
        );
        memberDTO.setEmail("Peter.Parker@gmail.com");
        Member member = modelMapper.map(memberDTO,Member.class);

        assertThat(member)
                .isNotNull()
                .isInstanceOf(Member.class);
    }

    @Test
    public void check_if_Member_Entity_converts_into_MemberDTO(){
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

        MemberDTO memberDTO = modelMapper.map(member,MemberDTO.class);

        assertThat(memberDTO)
                .isNotNull()
                .isInstanceOf(MemberDTO.class);
    }

    @Test
    public void check_if_MemberCardDTO_converts_To_MemberCard_Entity(){
        MemberCardDTO memberCardDTO = new MemberCardDTO(
                "shaggy",
                "scooby"
        );

        MemberCard memberCard = modelMapper.map(memberCardDTO,MemberCard.class);

        assertThat(memberCard)
                .isNotNull()
                .isInstanceOf(MemberCard.class);
    }

    @Test
    public void check_if_MemberCard_Entity_converts_into_MemberCardDTO(){
        MemberCard memberCard = new MemberCard(
                LocalDateTime.now(),
                "velma",
                "daphne"
        );

        MemberCardDTO memberCardDTO = modelMapper.map(memberCard,MemberCardDTO.class);

        assertThat(memberCardDTO)
                .isNotNull()
                .isInstanceOf(MemberCardDTO.class);
    }

    @Test
    public void check_if_RecipeDTO_List_converts_into_Page_of_RecipeDTO(){
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

        final int start = (int)pageRequest.getOffset();
        final int end = Math.min((start + pageRequest.getPageSize()), recipeDTOList.size());

        Page<RecipeDTO> recipeDTOPage =  new PageImpl<>(recipeDTOList.subList(start, end), pageRequest, recipeDTOList.size());

        assertThat(recipeDTOPage)
                .isNotEmpty()
                .isNotNull()
                .isInstanceOf(Page.class);
    }

    @Test
    public void check_if_Recipe_Entity_List_converts_into_List_of_RecipeDTO(){
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

        List<RecipeDTO> listofRecipeDTO =
                recipeList
                        .stream()
                        .map(recipe ->
                                new RecipeDTO(
                                        recipe.getCreatedAt(),
                                        recipe.getRecipeName(),
                                        recipe.getDuration(),
                                        recipe.getRecipeSkills(),
                                        recipe.getFoods(),
                                        new MemberDTO(
                                                recipe.getCreator().getFirstName(),
                                                recipe.getCreator().getLastName(),
                                                recipe.getCreator().getAge(),
                                                recipe.getCreator().getGender()
                                        ),
                                        recipe.getFoodTag()
                                )).collect(Collectors.toList());

        assertThat(listofRecipeDTO)
                .isNotNull()
                .isNotEmpty()
                .isInstanceOf(List.class);
    }
}