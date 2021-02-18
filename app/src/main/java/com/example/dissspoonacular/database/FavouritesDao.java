package com.example.dissspoonacular.database;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dissspoonacular.models.recipemodels.Result;

import java.util.List;

@Dao
public interface FavouritesDao {

    @Insert
    void insertFavourites(Result result);

    @Query("SELECT * FROM favouriteRecipes")
    List<Result> getFavourites();

    @Query("SELECT count(*) FROM favouriteRecipes WHERE recipeID = :id")
    int getCertain(int id);

    @Query("DELETE FROM favouriteRecipes WHERE recipeID = :id")
    void deleteCertain(int id);


}
