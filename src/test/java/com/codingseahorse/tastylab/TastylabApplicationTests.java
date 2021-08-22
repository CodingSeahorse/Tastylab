package com.codingseahorse.tastylab;

import com.codingseahorse.tastylab.controller.RecipeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TastylabApplicationTests {

	@Autowired
    RecipeController recipeController;

	@Test
	void contextLoads() {
		assertThat(recipeController).isNotNull();
	}

}
