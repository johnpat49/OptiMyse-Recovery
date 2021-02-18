package com.example.dissspoonacular;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import com.example.dissspoonacular.user.DietaryRequirementsActivity;
import com.example.dissspoonacular.user.HeightActivity;
import com.example.dissspoonacular.user.IntolerancesActivity;
import com.example.dissspoonacular.user.WeightGoalActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class SettingsActivityTest {

    @Rule //states that this activity is launched
    public ActivityTestRule<SettingsActivity> mActivityTestRule = new ActivityTestRule<SettingsActivity>(SettingsActivity.class);
    private SettingsActivity settingsActivity = null;

    @Before
    public void setUp() throws Exception {

        settingsActivity = mActivityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {

        settingsActivity = null;

    }

    @Test
    public void testLaunchOfSettingsActivity() {
        View view = settingsActivity.findViewById(R.id.layout_height_weight);
        assertNotNull(view);
    }

    @Test
    public void weightGoalClicked() {
        Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(WeightGoalActivity.class.getName(), null, false);


        onView(withId(R.id.layout_weight_goal_active_level)).perform(click());
        Activity weightGoal = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);

        assertNotNull(weightGoal);
        weightGoal.finish();
    }

    @Test
    public void heightWeightClicked() {
        Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(HeightActivity.class.getName(), null, false);


        onView(withId(R.id.layout_height_weight)).perform(click());
        Activity heightActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);

        assertNotNull(heightActivity);
        heightActivity.finish();
    }

    @Test
    public void dietaryRequirementsClicked() {
        Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(DietaryRequirementsActivity.class.getName(), null, false);


        onView(withId(R.id.layout_diet_requirements_settings)).perform(click());
        Activity dietsActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);

        assertNotNull(dietsActivity);
        dietsActivity.finish();
    }

    @Test
    public void intoleranceClicked() {
        Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(IntolerancesActivity.class.getName(), null, false);


        onView(withId(R.id.layout_intolerance_settings)).perform(click());
        Activity intoleranceActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);

        assertNotNull(intoleranceActivity);
        intoleranceActivity.finish();

    }


}