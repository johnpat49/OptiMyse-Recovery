package com.example.dissspoonacular;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.google.android.gms.common.data.DataBufferUtils.hasData;
import static org.junit.Assert.*;

public class InputPersonalDetailsActivityTest {

    public ActivityTestRule<InputPersonalDetailsActivity> mActivityTestRule = new ActivityTestRule<InputPersonalDetailsActivity>(InputPersonalDetailsActivity.class);

    private String input= "120";

    @Before
    public void setUp() throws Exception {


    }

    @After
    public void tearDown() throws Exception {



    }

    @Test
    public void recognisesInput() {
        //input text in edit text
        onView(withId(R.id.customCal)).perform(typeText(input));
        //close soft keyboard
        Espresso.closeSoftKeyboard();
        //perform button click




    }

    @Test
    public void customClicked() {




    }
}