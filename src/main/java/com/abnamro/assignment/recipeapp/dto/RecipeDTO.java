package com.abnamro.assignment.recipeapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;

/**
 * @author Ali Tofigh 6/28/2022 6:19 PM
 */
@Getter
@Setter
public class RecipeDTO implements Serializable {

    private Long recipeId;
    private String name;
    private String description;
    private Integer cookTime;
    private Integer servings;
    private boolean vegetarian;
    private Set<IngredientDTO> ingredients;

    public RecipeDTO() {
        ingredients  = new HashSet<>();
    }
}
