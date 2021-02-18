package com.example.dissspoonacular.requests.reciperequests;

import com.example.dissspoonacular.models.recipemodels.Name;
import com.example.dissspoonacular.requests.responses.RecipeResponses;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
/**
 * Retrofit calls to Spoonacular Api
 */
public interface RecipeApi {

    @GET("recipes/complexSearch")
    Call<RecipeResponses> searchRecipe(
                @Query("sort") String sortBy,
        @Query("sortDirection") String direction,
                @Query("type") String type,
            @Query("diet") String diet,
        @Query("intolerances") String intolerances,
        @Query("minCarbs") String minCarbs,
        @Query("maxCarbs") String maxCarbs,
        @Query("minProtein") String minProtein,
        @Query("maxProtein") String maxProtein,
        @Query("minFat")    String minFat,
        @Query("maxFat")    String maxFat,
        @Query("minCalories")   String minCalories,
        @Query("maxCalories")   String maxCalories,
        @Query("apiKey")    String apiKey

    );



    @GET("recipes/{id}/analyzedInstructions")
    Call<List<Name>> getRecipe(
            @Path("id") String id,
            @Query("stepBreakdown") boolean breakdown,
            @Query("apiKey") String apiKey

    );

}
