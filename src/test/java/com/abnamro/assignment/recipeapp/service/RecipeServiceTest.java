package com.abnamro.assignment.recipeapp.service;

import com.abnamro.assignment.recipeapp.domain.Ingredient;
import com.abnamro.assignment.recipeapp.domain.Recipe;
import com.abnamro.assignment.recipeapp.dto.RecipeDTO;
import com.abnamro.assignment.recipeapp.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Ali Tofigh 6/30/2022 12:49 PM
 */
@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

    @Mock
    RecipeRepository recipeRepository;

    @InjectMocks
    RecipeService recipeService;

    @BeforeEach
    public void setUp() {
        Recipe recipe = new Recipe();
        recipe.setServings(6);
        recipe.setName("Taco Soup");
        recipe.setCookTime(10);
        recipe.setVegetarian(true);
        recipe.setDescription("What’s easier than taco night? Taco SOUP night! Rather than fussing around " +
                "with warming tortillas in one pan, and seasoned beef sizzling in another, we’ve taken the ease " +
                "of taco night and made it even better by soupifying it!");
        Ingredient ingredient1 = new Ingredient();
        Ingredient ingredient2 = new Ingredient();
        Ingredient ingredient3 = new Ingredient();
    }


    @Test
    void findRecipeByVegetarian() {
        boolean flag = false;
        List<RecipeDTO> recipeByVegetarian = recipeService.findRecipeByVegetarian(flag);
        if (recipeByVegetarian.size() > 0) {
            RecipeDTO recipe = recipeByVegetarian.iterator().next();
            assertEquals(flag, recipe.isVegetarian());
        }
    }

    @Test
    void findRecipeByInstruction() {
        String text = "some";
        //List<Recipe> recipesByDescriptionContains = recipeRepository.findRecipesByDescriptionContains(text);


    }

    @Test
    void findRecipeByServing() {
    }

    @Test
    void findRecipeContainingIngredients() {
    }

    @Test
    void findRecipeNotContainingIngredients() {
    }

    @Test
    void findRecipeById() {
        verify(recipeRepository, times(1)).findRecipeById(anyLong());
    }

    @Test
    void saveRecipe() {
    }

    @Test
    void updateRecipe() {
    }

    @Test
    void removeRecipeById() {
    }
}