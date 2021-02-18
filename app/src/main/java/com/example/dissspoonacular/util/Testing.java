package com.example.dissspoonacular.util;

import android.util.Log;

import com.example.dissspoonacular.models.recipemodels.Result;

import java.util.List;

public class Testing {

    public static void printRecipes(List<Result> recipes, String tag){
        for(Result result: recipes) {
            Log.d(tag, "onChanged: "+result.getTitle());
        }
    }




}
