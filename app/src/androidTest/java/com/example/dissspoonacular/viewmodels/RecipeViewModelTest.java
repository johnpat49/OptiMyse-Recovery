package com.example.dissspoonacular.viewmodels;

import androidx.lifecycle.LiveData;

import com.example.dissspoonacular.models.recipemodels.Name;
import com.example.dissspoonacular.models.recipemodels.Step;
import com.example.dissspoonacular.repositories.RecipeRepository;

import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class RecipeViewModelTest {

    @Mock
    RecipeViewModel recipeViewModel;

    private LiveData<List<Name>> testGetRecipe;
    public LiveData<List<Name>> getTestRecipe() {
        return testGetRecipe;
    }

    private LiveData<List<Step>> testGetStep;
    public LiveData<List<Step>> getTestStep() {
        return testGetStep;
    }



    @Test
    public void getRecipe() {
        when(recipeViewModel.getRecipe()).thenReturn(testGetRecipe);

    }

    @Test
    public void getStep() {
        when(recipeViewModel.getStep()).thenReturn(testGetStep);

    }


}