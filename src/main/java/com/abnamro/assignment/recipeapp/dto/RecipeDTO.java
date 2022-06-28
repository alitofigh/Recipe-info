package com.abnamro.assignment.recipeapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Ali Tofigh 6/28/2022 6:19 PM
 */
@Getter
@Setter
public class RecipeDTO {

    private String name;
    private String description;
    private Integer cookTime;
    private Integer servings;
    private boolean vegetarian;
    private List<Map<String, String>> ingredients;

    public RecipeDTO() {
        ingredients = new ArrayList<>();
    }

    public void setIngredients(Map<String, String> ingredient) {
        this.ingredients.add(ingredient);
    }
}
