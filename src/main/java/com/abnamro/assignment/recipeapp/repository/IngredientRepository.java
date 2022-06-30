package com.abnamro.assignment.recipeapp.repository;

import com.abnamro.assignment.recipeapp.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * @author Ali Tofigh 6/30/2022 8:18 AM
 */

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    Set<Ingredient> findDistinctByDescriptionIn(Iterable<String> description);

    Set<Ingredient> findDistinctByDescriptionNotIn(Iterable<String> description);

    Integer deleteIngredientById(Long id);
}
