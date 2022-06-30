package com.abnamro.assignment.recipeapp.controller;

import com.abnamro.assignment.recipeapp.domain.Ingredient;
import com.abnamro.assignment.recipeapp.domain.Recipe;
import com.abnamro.assignment.recipeapp.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * @author Ali Tofigh 6/30/2022 12:51 PM
 */

@WebMvcTest
class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    RecipeService recipeService;

    @Autowired
    private ObjectMapper objectMapper;

    Recipe recipe;

    @BeforeEach
    void setUp() {
        recipe = new Recipe();
        recipe.setServings(6);
        recipe.setName("Taco Soup");
        recipe.setCookTime(10);
        recipe.setVegetarian(true);
        recipe.setDescription("What’s easier than taco night? Taco SOUP night! Rather than fussing around " +
                "with warming tortillas in one pan, and seasoned beef sizzling in another, we’ve taken the ease " +
                "of taco night and made it even better by soupifying it!");
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        recipe
                .addIngredient(ingredient1)
                .addIngredient(ingredient2)
                .addIngredient(ingredient3);
    }

    @Test
    void listAllRecipe() {
    }

    @Test
    void findRecipeById() throws Exception {
        mockMvc.perform(get("/api/v1/list-all-recipe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Object())));
        System.out.println("");
    }

    @Test
    void findRecipeByInstruction() {
    }

    @Test
    void addRecipe() {
    }

    @Test
    void updateRecipe() {
    }

    @Test
    void deleteRecipe() {
    }

    @Test
    void handleError() {
    }

    @Test
    void handleMediaTypeError() {
    }

    @Test
    void handleNotFoundException() {
    }

    @Test
    void handleBindingError() {
    }

    @Test
    void handleJsonError() {
    }
}