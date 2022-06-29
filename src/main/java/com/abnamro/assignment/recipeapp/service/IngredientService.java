package com.abnamro.assignment.recipeapp.service;

import com.abnamro.assignment.recipeapp.repository.RecipeRepository;
import org.springframework.stereotype.Service;

/**
 * @author Ali Tofigh 6/29/2022 4:49 PM
 */

@Service
public class IngredientService {

    private final RecipeRepository recipeRepository;

    public IngredientService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


}
