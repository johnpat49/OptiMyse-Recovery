package com.example.dissspoonacular.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dissspoonacular.database.FavouriteDatabase;
import com.example.dissspoonacular.models.recipemodels.Result;
import com.example.dissspoonacular.requests.reciperequests.RecipeApiClient;

import java.util.List;

/**
 * Repository Responsible for dealing with actions related to the FavouritesDatabase.
 */
public class FavouritesRepository {

    private FavouriteDatabase favouriteDatabase;
    private RecipeApiClient recipeApiClient;
    private static FavouritesRepository instance;

    public static FavouritesRepository getInstance(){
        if(instance==null){
            instance = new FavouritesRepository();
        }
        return instance;
    }

    public FavouritesRepository() {
        recipeApiClient = RecipeApiClient.getInstance();
    }

    public FavouritesRepository(Context context){
        favouriteDatabase = FavouriteDatabase.getInstance(context);
    }





}
