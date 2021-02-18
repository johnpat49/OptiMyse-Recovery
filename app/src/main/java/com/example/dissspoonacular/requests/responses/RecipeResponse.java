package com.example.dissspoonacular.requests.responses;

import com.example.dissspoonacular.models.recipemodels.Name;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Model of Response expected for Call for individual recipe
 */
public class RecipeResponse {

    @SerializedName("name")
    @Expose
    private List<Name> step;

    public List<Name> getStep(){
        return step;
    }

    @Override
    public String toString() {
        return "RecipeResponses{" +
                "recipes=" + step +
                '}';
    }

}
