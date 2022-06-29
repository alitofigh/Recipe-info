package com.abnamro.assignment.recipeapp.service;

import com.abnamro.assignment.recipeapp.convertor.RecipeConvertor;
import com.abnamro.assignment.recipeapp.domain.Ingredient;
import com.abnamro.assignment.recipeapp.domain.Recipe;
import com.abnamro.assignment.recipeapp.dto.RecipeDTO;
import com.abnamro.assignment.recipeapp.repository.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ali Tofigh 6/28/2022 5:08 PM
 */

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeConvertor recipeConvertor;

    public RecipeService(RecipeRepository recipeRepository, RecipeConvertor recipeConvertor) {
        this.recipeRepository = recipeRepository;
        this.recipeConvertor = recipeConvertor;
    }

    public List<RecipeDTO> listAllRecipe() {
        List<Recipe> recipeList = (List<Recipe>) recipeRepository.findAll();
        List<RecipeDTO> result = new ArrayList<>();

        recipeList.forEach(recipe -> result.add(recipeConvertor.convertToDTO(recipe)));
        return result;
    }

    public RecipeDTO findRecipeById(Long id) {
        return recipeConvertor.convertToDTO(recipeRepository.findRecipeById(id));
    }

    public void saveRecipe(RecipeDTO recipeDTO) {
        Recipe recipe = recipeConvertor.convertFromDTO(recipeDTO);
        recipeRepository.save(recipe);
    }

    @Transactional
    public int removeRecipeById(Long id) {
        return recipeRepository.deleteRecipeById(id);
    }
}
