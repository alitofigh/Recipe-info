package com.abnamro.assignment.recipeapp.dto;

import com.abnamro.assignment.recipeapp.domain.MeasurementUnit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Ali Tofigh 6/29/2022 7:42 AM
 */

@Getter
@Setter
@NoArgsConstructor
public class IngredientDTO implements Serializable {
    private Long id;
    private Long recipeId;
    private String description;
    private String amount;
    private MeasurementUnit measurementUnit;
}
