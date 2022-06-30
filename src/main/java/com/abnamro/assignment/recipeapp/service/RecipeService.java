package com.abnamro.assignment.recipeapp.service;

import com.abnamro.assignment.recipeapp.convertor.IngredientConvertor;
import com.abnamro.assignment.recipeapp.convertor.RecipeConvertor;
import com.abnamro.assignment.recipeapp.domain.Ingredient;
import com.abnamro.assignment.recipeapp.domain.Recipe;
import com.abnamro.assignment.recipeapp.dto.IngredientDTO;
import com.abnamro.assignment.recipeapp.dto.RecipeDTO;
import com.abnamro.assignment.recipeapp.repository.RecipeRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Ali Tofigh 6/28/2022 5:08 PM
 */

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeConvertor recipeConvertor;
    private final IngredientConvertor ingredientConvertor;

    public RecipeService(RecipeRepository recipeRepository, RecipeConvertor recipeConvertor,
                         IngredientConvertor ingredientConvertor) {
        this.recipeRepository = recipeRepository;
        this.recipeConvertor = recipeConvertor;
        this.ingredientConvertor = ingredientConvertor;
    }

    public List<RecipeDTO> listAllRecipe() {
        List<Recipe> recipeList = (List<Recipe>) recipeRepository.findAll();
        List<RecipeDTO> result = new ArrayList<>();
        recipeList.forEach(recipe -> result.add(recipeConvertor.convertToDTO(recipe)));
        return result;
    }

    public List<RecipeDTO> findRecipeByVegetarian(boolean vegetarian) {
        List<Recipe> recipes = recipeRepository.findRecipeByVegetarian(vegetarian);
        List<RecipeDTO> recipeDTOList = new ArrayList<>();
        recipes.forEach(recipe -> recipeDTOList.add(recipeConvertor.convertToDTO(recipe)));
        return  recipeDTOList;
    }

    public List<RecipeDTO> findRecipeByInstruction(String text) {
        List<Recipe> recipes = recipeRepository.findRecipesByDescriptionContains(text);
        List<RecipeDTO> recipeDTOList = new ArrayList<>();
        recipes.forEach(recipe -> recipeDTOList.add(recipeConvertor.convertToDTO(recipe)));
        return  recipeDTOList;
    }

    public List<RecipeDTO> findRecipeByServing(Integer serving) {
        List<Recipe> recipes = recipeRepository.findRecipeByServings(serving);
        List<RecipeDTO> recipeDTOList = new ArrayList<>();
        recipes.forEach(recipe -> recipeDTOList.add(recipeConvertor.convertToDTO(recipe)));
        return  recipeDTOList;
    }

    public List<RecipeDTO> findRecipeContainingIngredients(Set<IngredientDTO> ingredientDTOS) {
        Set<Ingredient> ingredients = new HashSet<>();
        ingredientDTOS.forEach(ingredientDTO -> ingredients.add(ingredientConvertor.convertFromDTO(ingredientDTO)));
        //List<Recipe> recipes = recipeRepository.findRecipeByIngredientsIsContaining(ingredients);
        //List<Recipe> recipes = recipeRepository.findAllByIngredientsIn( ingredients);
        List<RecipeDTO> recipeDTOList = new ArrayList<>();
        //recipes.forEach(recipe -> recipeDTOList.add(recipeConvertor.convertToDTO(recipe)));
        return  recipeDTOList;
    }

    public List<RecipeDTO> findRecipeNotContainingIngredients(Set<IngredientDTO> ingredientDTOS) {
        Set<Ingredient> ingredients = new HashSet<>();
        ingredientDTOS.forEach(ingredientDTO -> ingredients.add(ingredientConvertor.convertFromDTO(ingredientDTO)));
        List<Recipe> distinctByIngredientsNotIn = recipeRepository.findDistinctFirstByIngredientsNotIn(ingredients);
        List<RecipeDTO> recipeDTOList = new ArrayList<>();
        distinctByIngredientsNotIn.forEach(recipe -> recipeDTOList.add(recipeConvertor.convertToDTO(recipe)));
        return  recipeDTOList;
    }

    public RecipeDTO findRecipeById(Long recipeId) {
        return recipeConvertor.convertToDTO(recipeRepository.findRecipeById(recipeId));
    }

    public Recipe saveRecipe(RecipeDTO recipeDTO) {
        Recipe recipe = recipeConvertor.convertFromDTO(recipeDTO);
        return recipeRepository.save(recipe);
    }

    public void updateRecipe(RecipeDTO recipeDTO) throws NotFoundException {
        Recipe recipe = recipeRepository.findRecipeById(recipeDTO.getRecipeId());
        if (recipe == null)
            throw new NotFoundException("There is no recipe with id " + recipeDTO.getRecipeId());



    }

    @Transactional
    public int removeRecipeById(Long id) {
        return recipeRepository.deleteRecipeById(id);
    }
}
