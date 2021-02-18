package com.example.dissspoonacular.models.recipemodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
/**
 *Model for List of Nutrition Recipe Response.
 */
public class Nutrition {

    @SerializedName("nutrients")
    @Expose
    private List<Nutrient> nutrients = null;

    public Nutrition(List<Nutrient> nutrients) {
        this.nutrients = nutrients;
    }

    public List<Nutrient> getNutrients() {
        return nutrients;
    }

    public void setNutrients(List<Nutrient> nutrients) {
        this.nutrients = nutrients;
    }

}
