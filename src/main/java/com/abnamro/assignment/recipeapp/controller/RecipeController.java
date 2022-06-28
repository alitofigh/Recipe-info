package com.abnamro.assignment.recipeapp.controller;

import com.abnamro.assignment.recipeapp.service.RecipeService;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ali Tofigh 6/28/2022 5:09 PM
 */

@RestController
@RequestMapping("/api/v1")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/list-all-recipe")
    public String listAllRecipe() {
        return new Gson().toJson(recipeService.listAllRecipe());
    }
}
