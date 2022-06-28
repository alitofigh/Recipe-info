package com.abnamro.assignment.recipeapp.service;

import com.abnamro.assignment.recipeapp.domain.Ingredient;
import com.abnamro.assignment.recipeapp.domain.Recipe;
import com.abnamro.assignment.recipeapp.dto.RecipeDTO;
import com.abnamro.assignment.recipeapp.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ali Tofigh 6/28/2022 5:08 PM
 */

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<RecipeDTO> listAllRecipe() {
        List<Recipe> recipeList = (List<Recipe>) recipeRepository.findAll();
        List<RecipeDTO> result = new ArrayList<>();
        for (Recipe recipe : recipeList) {
            RecipeDTO dto = new RecipeDTO();
            dto.setName(recipe.getName());
            dto.setDescription(recipe.getDescription());
            dto.setVegetarian(recipe.isVegetarian());
            dto.setServings(recipe.getServings());
            dto.setCookTime(recipe.getCookTime());
            for (Ingredient ingredient : recipe.getIngredients()) {
                Map<String, String> map = new HashMap<>();
                map.put(ingredient.getDescription(), "" + ingredient.getAmount());
                dto.setIngredients(map);
            }
            result.add(dto);
        }
        return result;
    }
}
