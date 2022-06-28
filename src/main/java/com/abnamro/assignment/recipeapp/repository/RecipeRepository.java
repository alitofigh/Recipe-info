package com.abnamro.assignment.recipeapp.repository;

import com.abnamro.assignment.recipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Ali Tofigh 6/28/2022 4:23 PM
 */

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
