package com.codingseahorse.tastylab.repository;

import com.codingseahorse.tastylab.model.recipe.FoodTag;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class FoodTagRepositoryTest {
    @Autowired
    FoodTagRepository foodTagRepository;

    Set<FoodTag> foodTags = new HashSet<>();

    @BeforeEach
    void setup() {
        foodTags.add(new FoodTag("tasty"));
        foodTags.add(new FoodTag("fresh"));

        foodTagRepository.saveAll(foodTags);
    }

    @Test
    void should_existsByTagName_return_true_or_false() {
        boolean existsByTagName = foodTagRepository.existsByTagName("tasty");

        assertThat(existsByTagName)
                .isTrue();
    }

    @Test
    void should_getFoodTagByTagName() {
        FoodTag foodTag = foodTagRepository.getFoodTagByTagName("fresh");

        assertThat(foodTag.getTagName())
                .isEqualTo("fresh");
    }
}