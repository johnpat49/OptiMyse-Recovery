package com.example.dissspoonacular.database;

import com.example.dissspoonacular.models.recipemodels.Result;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Class to test the database operations in the Favourites Dao
 */
public class FavouriteDaoTest extends FavouriteDatabaseTest{

    /**
     * Tests the insertion, reading and the delete certain operations
     */
    @Test
    public void insertReadDelete() {

        Result result = new Result();
        result.setId(715538);
        result.setTitle("Pasta with Garlic, Scallions, Cauliflower & Breadcrumbs");
        result.setImage("https://spoonacular.com/recipeImages/715538-312x231.jpg");
        result.setFavStatus("1");


        //insert
        getFavouritesDao().insertFavourites(result);

        // read
        List<Result> insertedRecipes = getFavouritesDao().getFavourites();

        assertNotNull(insertedRecipes);

        assertEquals(result.getTitle(), insertedRecipes.get(0).getTitle());
        assertEquals(result.getImage(), insertedRecipes.get(0).getImage());
        assertEquals(result.getId(), insertedRecipes.get(0).getId());
        assertEquals(result.getFavStatus(), insertedRecipes.get(0).getFavStatus());


        // delete
        getFavouritesDao().deleteCertain(insertedRecipes.get(0).getId());
        // confirm the database is empty
        insertedRecipes = getFavouritesDao().getFavourites();
        assertEquals(0, insertedRecipes.size());

    }

    /**
     * Tests the getCertain operation
     */
    @Test
    public void getCertain() {

        Result result = new Result();
        result.setId(715538);
        result.setTitle("Pasta with Garlic, Scallions, Cauliflower & Breadcrumbs");
        result.setImage("https://spoonacular.com/recipeImages/715538-312x231.jpg");
        result.setFavStatus("1");

        //insert
        getFavouritesDao().insertFavourites(result);

        List<Result> insertedRecipes = getFavouritesDao().getFavourites();

        assertNotNull(insertedRecipes);
        assertEquals(result.getId(), insertedRecipes.get(0).getId());
        int id = insertedRecipes.get(0).getId();


        assertEquals(1, getFavouritesDao().getCertain(id));



    }

}
