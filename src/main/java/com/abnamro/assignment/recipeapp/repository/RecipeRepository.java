package com.abnamro.assignment.recipeapp.repository;

import com.abnamro.assignment.recipeapp.domain.Ingredient;
import com.abnamro.assignment.recipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Ali Tofigh 6/28/2022 4:23 PM
 */

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    List<Recipe> findRecipeByVegetarian(boolean vegetarian);

    List<Recipe> findRecipesByDescriptionContains(String description);

    List<Recipe> findRecipeByServings(Integer servings);

    List<Recipe> findDistinctFirstByIngredientsNotIn(Iterable<Ingredient> ingredients);

    Recipe findRecipeById(Long recipeId);

    int deleteRecipeById(Long id);
}
