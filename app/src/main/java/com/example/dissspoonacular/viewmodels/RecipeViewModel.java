package com.example.dissspoonacular.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.dissspoonacular.models.recipemodels.Name;
import com.example.dissspoonacular.models.recipemodels.Step;
import com.example.dissspoonacular.repositories.RecipeRepository;

import java.util.List;

public class RecipeViewModel extends ViewModel {

    private RecipeRepository mRecipeRepository;

    public RecipeViewModel() {
        mRecipeRepository = RecipeRepository.getInstance();

    }


    public LiveData<List<Name>> getRecipe(){
        return mRecipeRepository.getRecipe();
    }

    public LiveData<List<Step>> getStep(){
        return mRecipeRepository.getStep();
    }

    public void getSteps(String recipeID){
        mRecipeRepository.getRecipeApi(recipeID);
    }




}
