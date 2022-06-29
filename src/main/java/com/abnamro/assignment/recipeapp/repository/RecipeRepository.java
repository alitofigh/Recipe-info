package com.abnamro.assignment.recipeapp.repository;

import com.abnamro.assignment.recipeapp.domain.Ingredient;
import com.abnamro.assignment.recipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author Ali Tofigh 6/28/2022 4:23 PM
 */

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    List<Recipe> findRecipeByVegetarian(boolean vegetarian);

    List<Recipe> findRecipeByServings(Integer servings);

    List<Recipe> findAllByIngredientsIn(Collection<Set<Ingredient>> ingredients);

    List<Recipe> findAllByIngredientsIsNotIn(Collection<Set<Ingredient>> ingredients);

    //List<Recipe> findRecipeByIngredientsIsContaining(Set<Ingredient> ingredients);

    //List<Recipe> findRecipeByIngredientsIsNotContaining(Set<Ingredient> ingredients);

    Recipe findRecipeById(Long recipeId);

    int deleteRecipeById(Long id);
}
