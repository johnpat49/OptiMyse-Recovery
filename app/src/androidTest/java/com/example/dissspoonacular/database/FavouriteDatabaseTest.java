package com.example.dissspoonacular.database;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.example.dissspoonacular.database.FavouriteDatabase;
import com.example.dissspoonacular.database.FavouritesDao;

import org.junit.After;
import org.junit.Before;

public class FavouriteDatabaseTest {

    //system under test
    private FavouriteDatabase favouriteDatabase;

    public FavouritesDao getFavouritesDao(){
        return favouriteDatabase.getFavouriteDao();
    }

    @Before
    public void init(){
        favouriteDatabase = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
                FavouriteDatabase.class).build();
    }

    @After
    public void finish(){
        favouriteDatabase.close();
    }

}
