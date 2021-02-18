package com.example.dissspoonacular.repositories;

import androidx.lifecycle.LiveData;

import com.example.dissspoonacular.models.recipemodels.Name;
import com.example.dissspoonacular.models.recipemodels.Result;
import com.example.dissspoonacular.models.recipemodels.Step;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecipeRepositoryTest {

    @Mock
     RecipeRepository recipeRepository;

    private LiveData<List<Name>> testGetRecipe;
    private LiveData<List<Result>> testGetRecipes;
    private LiveData<List<Step>> testGetStep;

    public LiveData<List<Name>> getTestRecipe() {
        return testGetRecipe;
    }

    public LiveData<List<Result>> getTestRecipes() {
        return testGetRecipes;
    }

    public LiveData<List<Step>> getTestStep() {
        return testGetStep;
    }


    @Before
    public void setUp() throws Exception {



    }

    @Test
    public void getRecipes() {
        when(recipeRepository.getRecipe()).thenReturn(testGetRecipe);


    }

    @Test
    public void getRecipe() {

        when(recipeRepository.getRecipe()).thenReturn(testGetRecipe);

    }

    @Test
    public void getStep() {

        when(recipeRepository.getStep()).thenReturn(testGetStep);


    }

    @Test
    public void searchRecipesApi() {
    }

    @Test
    public void getRecipeApi() {


    }
}