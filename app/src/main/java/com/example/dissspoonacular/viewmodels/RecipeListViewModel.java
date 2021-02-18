package com.example.dissspoonacular.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.dissspoonacular.database.FavouriteDatabase;
import com.example.dissspoonacular.models.recipemodels.FavouriteItem;
import com.example.dissspoonacular.models.recipemodels.Result;
import com.example.dissspoonacular.repositories.FavouritesRepository;
import com.example.dissspoonacular.repositories.RecipeRepository;

import java.util.List;

public class RecipeListViewModel extends ViewModel {

    private RecipeRepository recipeRepository;
    private FavouritesRepository favouritesRepository;


    public RecipeListViewModel() { //holds list recipes
       recipeRepository = RecipeRepository.getInstance();
       favouritesRepository = FavouritesRepository.getInstance();
    }

    public LiveData<List<Result>> getRecipes() {
        return recipeRepository.getRecipes();
    }

    public void searchRecipesApi(String minCalories, String maxCalories,String diet, String intolerances,String minCarbs, String maxCarbs,String minPro, String maxPro,String minFat, String maxFat,String type){
        recipeRepository.searchRecipesApi(minCalories, maxCalories,diet,intolerances,minCarbs, maxCarbs,minPro, maxPro,minFat, maxFat,type);
    }




}
