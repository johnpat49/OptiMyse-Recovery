package com.example.dissspoonacular.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dissspoonacular.models.recipemodels.Result;

/**
 * Creates Favourite Database to store users Favourite Recipes.
 */
@Database(entities = {Result.class}, version = 2)
public abstract class FavouriteDatabase extends RoomDatabase{

    public static final String DATABASE_NAME = "favourites_db";
    private static FavouriteDatabase instance;

    /**
     * Implementing Singleton Pattern
     * @param context
     * @return
     */
    public static FavouriteDatabase getInstance(final Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    FavouriteDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract FavouritesDao getFavouriteDao();

}
