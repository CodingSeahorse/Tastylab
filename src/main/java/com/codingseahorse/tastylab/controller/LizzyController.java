package com.codingseahorse.tastylab.controller;

import com.codingseahorse.tastylab.dto.LizzyDTO;
import com.codingseahorse.tastylab.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lizzy")
public class LizzyController {
    @Autowired
    RecipeService recipeService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public LizzyDTO postFoods(@RequestParam String[] foods,
                              @RequestParam Integer page,
                              @RequestParam Integer size){
       PageRequest pageRequest = PageRequest.of(
               page,
               size,
               Sort.by("createdAt").ascending()
       );
       return recipeService.findRecipeByElements(foods,pageRequest);
    }
}