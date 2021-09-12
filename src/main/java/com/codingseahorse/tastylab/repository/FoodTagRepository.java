package com.codingseahorse.tastylab.repository;

import com.codingseahorse.tastylab.model.recipe.FoodTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodTagRepository extends JpaRepository<FoodTag,Long> {
    boolean existsByTagName(String tagName);
    FoodTag getFoodTagByTagName(String tagName);
}
