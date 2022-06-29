package com.abnamro.assignment.recipeapp.controller;

import com.abnamro.assignment.recipeapp.dto.IngredientDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ali Tofigh 6/29/2022 4:43 PM
 */

@RestController
@RequestMapping("/api/v1")
public class IngredientController {

    @PostMapping("/new-ingredient")
    public String newIngredient(@RequestBody IngredientDTO ingredientDTO) {
return "";
    }

}
