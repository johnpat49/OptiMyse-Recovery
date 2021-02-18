package com.example.dissspoonacular;

import com.example.dissspoonacular.models.fitbitmodels.AccessToken;
import com.example.dissspoonacular.models.fitbitmodels.Activities;
import com.example.dissspoonacular.models.fitbitmodels.Activity;
import com.example.dissspoonacular.repositories.FitbitRepository;
import com.example.dissspoonacular.requests.fitbitrequests.FitbitApi;
import com.example.dissspoonacular.requests.fitbitrequests.FitbitApiClient;
import com.example.dissspoonacular.requests.fitbitrequests.FitbitServiceGenerator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static org.junit.Assert.*;
@Config(constants = BuildConfig.class, sdk = 21,
        manifest = "app/src/main/AndroidManifest.xml")
@RunWith(RobolectricGradleTestRunner.class)
public class MainActivityTest {

    private MainActivity mainActivity;

    @Mock
    FitbitApi fitbitApi;

    @Captor
    private ArgumentCaptor<Callback<List<Activities>>> cb;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
        mainActivity = controller.get();
        FitbitServiceGenerator.setApi(fitbitApi);
        controller.create();

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void onNewIntent() {


    }

    @Test
    public void getCalories(){
//        LocalDate ld = LocalDate.now().plusDays(1);
//
//        Mockito.verify(fitbitApi).getCalories();
//
//        Activities testActivity = new Activities();
//        testActivity.setActivities();
//
//        cb.getValue().success(testRepos, null);
//
//        assertThat(activity.getListAdapter()).hasCount(2);




    }
}