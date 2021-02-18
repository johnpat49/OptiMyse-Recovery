package com.example.dissspoonacular.requests.reciperequests;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dissspoonacular.database.FavouriteDatabase;
import com.example.dissspoonacular.models.recipemodels.Name;
import com.example.dissspoonacular.models.recipemodels.Result;
import com.example.dissspoonacular.models.recipemodels.Step;
import com.example.dissspoonacular.requests.responses.RecipeResponses;
import com.example.dissspoonacular.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Where calls to Spoonacular APi are made.
 */
public class RecipeApiClient {

    private static RecipeApiClient instance;
    private MutableLiveData<List<Result>> mRecipes;
    private MutableLiveData<List<Name>> mSteps;
    private MutableLiveData<List<Step>> step;







    private RecipeApi recipeApi;


    public static RecipeApiClient getInstance() {
        if (instance == null) {
            instance = new RecipeApiClient();
        }
        return instance;
    }

    public RecipeApiClient() {
        mRecipes = new MutableLiveData<>();
        mSteps = new MutableLiveData<>();
        step = new MutableLiveData<>();

        recipeApi = RecipeServiceGenerator.getRecipeApi();

    }


    /**
     * Get Live data List of Recipes
     * @return
     */
    public LiveData<List<Result>> getRecipes() {
        return mRecipes;
    }


    public LiveData<List<Step>> getSteps() {
        return step;
    }

    public LiveData<List<Name>> getRecipe() {
        return mSteps;
    }

    /**
     * Call to Spoonacular Api
     *
     * @param minCalories
     * @param maxCalories
     * @param diet
     * @param intolerance
     * @param minCarbs
     * @param maxCarbs
     * @param minPro
     * @param maxPro
     * @param minFat
     * @param maxFat
     * @param type
     */
    public void setRecipeApi(String minCalories, String maxCalories, String diet, String intolerance, String minCarbs, String maxCarbs, String minPro, String maxPro, String minFat, String maxFat, String type) {

        final Call<RecipeResponses> recipesResponsesCall = recipeApi.searchRecipe("calories", "desc", type, diet, intolerance, minCarbs, maxCarbs, minPro, maxPro, minFat, maxFat, minCalories, maxCalories, Constants.API_KEY);
        recipesResponsesCall.enqueue(new Callback<RecipeResponses>() {
            @Override
            public void onResponse(Call<RecipeResponses> call, Response<RecipeResponses> response) {
                if (response.body().getRecipes() != null) {
                    List<Result> list = new ArrayList<>(response.body().getRecipes());
                    mRecipes.postValue(list);

                } else {

                }
            }

            @Override
            public void onFailure(Call<RecipeResponses> call, Throwable t) {

                mRecipes.postValue(null);
            }
        });


    }

    /**
     * Query get individual recipe
     *
     * @return
     */

    public void getRecipeApi(String recipeID) {
        Call<List<Name>> getRecipe = recipeApi.getRecipe(recipeID, true, Constants.API_KEY);
        getRecipe.enqueue(new Callback<List<Name>>() {
            @Override
            public void onResponse(Call<List<Name>> call, Response<List<Name>> response) {
                if (response.code() == 200) {
                    List<Name> steps = response.body();
                    mSteps.postValue(steps);
                }
            }

            @Override
            public void onFailure(Call<List<Name>> call, Throwable t) {

                mSteps.postValue(null);
            }
        });
    }







}