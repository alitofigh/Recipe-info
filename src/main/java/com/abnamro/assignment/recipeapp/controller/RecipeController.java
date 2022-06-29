package com.abnamro.assignment.recipeapp.controller;

import com.abnamro.assignment.recipeapp.domain.Recipe;
import com.abnamro.assignment.recipeapp.dto.RecipeDTO;
import com.abnamro.assignment.recipeapp.service.RecipeService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ali Tofigh 6/28/2022 5:09 PM
 */

@Api(value = "User management", tags = "These services are to add user, grant user, activate services, and ...")
@RestController
@RequestMapping("/api/v1")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @ApiOperation(value = "List all of recipes.", response = String.class)
    @GetMapping("/list-all-recipe")
    public String listAllRecipe() {
        return new Gson().toJson(recipeService.listAllRecipe());
    }

    @ApiOperation(value = "Get a recipe by recipeId", response = String.class)
    @GetMapping("find-recipe-by-id")
    public String findRecipeById(@RequestParam long recipeId) {
        RecipeDTO recipeDTO = recipeService.findRecipeById(recipeId);
        return new Gson().toJson(recipeDTO);
    }

    @ApiOperation(value = "Filter recipes", response = String.class)
    @GetMapping("filter-recipe")
    public String filterRecipe() {
        return "";
    }

    @ApiOperation(value = "Add a new recipe", response = String.class)
    @PostMapping(value = "/add-recipe", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addRecipe(@RequestBody RecipeDTO recipeDTO) {

        recipeService.saveRecipe(recipeDTO);
        return "recipe saved successfully.";
    }

    @ApiOperation(value = "Update a recipe", response = String.class)
    @PostMapping(value = "/update-recipe", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateRecipe(@RequestBody RecipeDTO recipeDTO) {
        RecipeDTO recipeD = recipeService.findRecipeById(recipeDTO.getRecipeId());
        if (recipeD != null) {
            recipeService.saveRecipe(recipeDTO);
            return "recipe updated successfully.";
        } else {
            return "There is not any recipe with id: " + recipeDTO.getRecipeId();
        }
    }

    @ApiOperation(value = "Remove a recipe", response = String.class)
    @GetMapping("/remove-recipe")
    public String deleteRecipe(@RequestParam("recipe-id") long recipeId) {
        int result = recipeService.removeRecipeById(recipeId);
        if (result > 0)
            return "recipe with id: " + recipeId + " removed.";
        else
            return "there is no recipe with id: " + recipeId;

    }
}
