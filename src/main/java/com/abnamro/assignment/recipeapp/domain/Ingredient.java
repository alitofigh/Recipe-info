package com.abnamro.assignment.recipeapp.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Ali Tofigh 6/28/2022 3:43 PM
 */

@Getter
@Setter
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String amount;
    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    private MeasurementUnit measurementUnit;

    @ManyToOne
    private Recipe recipe;

    public Ingredient() {
    }

    public Ingredient(String description, String amount, MeasurementUnit measurementUnit) {
        this.description = description;
        this.amount = amount;
        this.measurementUnit = measurementUnit;
    }
}
