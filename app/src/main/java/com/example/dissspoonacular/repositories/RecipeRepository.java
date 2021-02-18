package com.example.dissspoonacular.repositories;

import androidx.lifecycle.LiveData;
import com.example.dissspoonacular.models.recipemodels.Name;
import com.example.dissspoonacular.models.recipemodels.Result;
import com.example.dissspoonacular.models.recipemodels.Step;
import com.example.dissspoonacular.requests.reciperequests.RecipeApiClient;

import java.util.List;
/**
 * Repository Responsible for dealing with actions related to the Spoonacular Api.
 */
public class RecipeRepository {

    private static RecipeRepository instance;
    private RecipeApiClient mRecipeApiClient;

    //Singleton Pattern
    public static RecipeRepository getInstance(){
        if(instance==null){
            instance = new RecipeRepository();
        }
        return instance;
    }


    private RecipeRepository(){
        mRecipeApiClient = RecipeApiClient.getInstance();

    }

    public LiveData<List<Result>> getRecipes(){
        return mRecipeApiClient.getRecipes();

    }

    public LiveData<List<Name>> getRecipe(){
        return mRecipeApiClient.getRecipe();
    }

    public LiveData<List<Step>> getStep(){
        return mRecipeApiClient.getSteps();
    }

    public void searchRecipesApi(String minCalories, String maxCalories,String diet, String intolerances,String minCarbs, String maxCarbs,String minPro, String maxPro, String minFat, String maxFat,String type){
                mRecipeApiClient.setRecipeApi(minCalories, maxCalories,diet,intolerances,minCarbs, maxCarbs,minPro, maxPro,minFat,maxFat,type);
    }
    public void getRecipeApi(String recipeID){
        mRecipeApiClient.getRecipeApi(recipeID);
    }




}
