package com.example.dissspoonacular.requests.responses;

import com.example.dissspoonacular.models.recipemodels.Result;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
/**
 * Model of Response expected for Call for a list of Recipes.
 */
public class RecipeResponses {

    @SerializedName("results")
    @Expose
    private List<Result> recipes;

    public List<Result> getRecipes() {
        return recipes;
    }

    @Override
    public String toString() {
        return "RecipeResponses{" +
                "recipes=" + recipes +
                '}';
    }
}
