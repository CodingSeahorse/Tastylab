package com.codingseahorse.tastylab.dto.converter;

import com.codingseahorse.tastylab.dto.MemberCardDTO;
import com.codingseahorse.tastylab.dto.MemberDTO;
import com.codingseahorse.tastylab.dto.RecipeDTO;
import com.codingseahorse.tastylab.model.member.Member;
import com.codingseahorse.tastylab.model.member.MemberCard;
import com.codingseahorse.tastylab.model.recipe.Recipe;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
@RequiredArgsConstructor
public class Converter {
    @NonNull
    ModelMapper modelMapper;

    // <editor-fold defaultstate="collapsed" desc="Recipe & RecipeDTO *Converter">
    public void convertToEntityRecipe(RecipeDTO recipeDTO){
        modelMapper.map(recipeDTO, Recipe.class);
    }

    public void convertToRecipeDTO(Recipe recipe){
        modelMapper.map(recipe, RecipeDTO.class);
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Member & MemberDTO *Converter">
    public void convertToEntityMember(MemberDTO memberDTO){
        modelMapper.map(memberDTO, Member.class);
    }

    public void convertToMemberDTO(Member member){
        modelMapper.map(member, MemberDTO.class);
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="MemberCard & MemberCardDTO *Converter">
    public void convertToEntityMemberCard(MemberCardDTO memberCardDTO){
        modelMapper.map(memberCardDTO, MemberCard.class);
    }

    public void convertToMemberCardDTO(MemberCard memberCard){
        modelMapper.map(memberCard, MemberCardDTO.class);
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="RecipeDTOListToPage & RecipeListToRecipeDTOList *Converter">
    public Page<RecipeDTO> convertRecipeDTOListToPageOfRecipeDTO(List<RecipeDTO> recipeDTOList, PageRequest pageRequest){
        final int start = (int)pageRequest.getOffset();
        final int end = Math.min((start + pageRequest.getPageSize()), recipeDTOList.size());
        return new PageImpl<>(recipeDTOList.subList(start, end), pageRequest, recipeDTOList.size());
    }

    public List<RecipeDTO> convertRecipeListToRecipeDTOList(List<Recipe> recipeList){
        return recipeList.stream()
                .map( recipe ->
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
    }
    // </editor-fold>
}
