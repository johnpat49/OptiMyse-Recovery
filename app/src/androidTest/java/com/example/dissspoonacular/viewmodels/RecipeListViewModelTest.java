package com.example.dissspoonacular.viewmodels;

import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;

import com.example.dissspoonacular.adapters.TestingUtils;
import com.example.dissspoonacular.models.recipemodels.Name;
import com.example.dissspoonacular.models.recipemodels.Nutrient;
import com.example.dissspoonacular.models.recipemodels.Nutrition;
import com.example.dissspoonacular.models.recipemodels.Result;
import com.example.dissspoonacular.models.recipemodels.Step;
import com.example.dissspoonacular.repositories.RecipeRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import util.LiveDataTestUtil;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class RecipeListViewModelTest {

    @Mock
    RecipeListViewModel recipeListViewModel;

    @Mock
    RecipeRepository recipeRepository;

    private LiveData<List<Result>> testGetRecipe;

    public LiveData<List<Result>> getRecipeTest() {
        return testGetRecipe;
    }

    @Before
    public void setUp() throws Exception {

    }

    /**
     * Test getRecipe method.
     */
    @Test
    public void getRecipes() {

        when(recipeListViewModel.getRecipes()).thenReturn(testGetRecipe);

    }
}