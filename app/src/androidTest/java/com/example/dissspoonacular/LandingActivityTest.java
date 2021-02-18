package com.example.dissspoonacular;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class LandingActivityTest {

    @Rule //states that this activity is launched
    public ActivityTestRule<LandingActivity> mActivityTestRule = new ActivityTestRule<LandingActivity>(LandingActivity.class);


    private LandingActivity landingActivity = null;

    @Before
    public void setUp() throws Exception {

        landingActivity = mActivityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {

        landingActivity = null;

    }


    @Test
    public void testLaunchOfLandingActivity() {
        View view = landingActivity.findViewById(R.id.dailyGoal);
        assertNotNull(view);
    }


//    @Test
//    public void autoGenerateClicked() {
//        Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);
//
//        onView(withId(R.id.layoutAutoGenerate)).perform(click());
//        Activity mainActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
//
//        assertNotNull(mainActivity);
//        mainActivity.finish();
//
//    }

    @Test
    public void settingsClicked() {
        Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(SettingsActivity.class.getName(), null, false);


        onView(withId(R.id.layoutSettings)).perform(click());
        Activity settingsActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);

        assertNotNull(settingsActivity);
        settingsActivity.finish();

    }

    @Test
    public void userInputClicked() {
        Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(InputPersonalDetailsActivity.class.getName(), null, false);

        onView(withId(R.id.layoutUserInput)).perform(click());
        Activity InputPersonalDetailsActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);

        assertNotNull(InputPersonalDetailsActivity);
        InputPersonalDetailsActivity.finish();
    }

    @Test
    public void favouritesClicked() {
        Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(FavouritesActivity.class.getName(), null, false);

        onView(withId(R.id.layoutFavourites)).perform(click());
        Activity FavouritesActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);

        assertNotNull(FavouritesActivity);
        FavouritesActivity.finish();
    }
}