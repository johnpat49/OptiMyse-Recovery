package com.example.dissspoonacular;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class FavouritesActivityTest {

    @Rule
    public ActivityTestRule<FavouritesActivity> favouritesTester = new ActivityTestRule<FavouritesActivity>(FavouritesActivity.class);



    @Test
    public void recycleTest(){

        Espresso.onView(withId(R.id.recipe_list_favourite)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }


}