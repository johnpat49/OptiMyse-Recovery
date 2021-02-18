package com.example.dissspoonacular;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dissspoonacular.models.usermodel.UserProfile;
import com.example.dissspoonacular.onboarding.FirstOnboarding;
import com.example.dissspoonacular.repositories.FitbitRepository;
import com.example.dissspoonacular.user.RegistrationActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Landing activity for the app where to user is presented with 4 options to proceed and their daily Calorific Goal
 */
public class LandingActivity extends AppCompatActivity {

    private FitbitRepository mFitbitRepository;
    private TextView goalView;
    private Integer weight;
    private Integer height;
    private Integer age;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private String userID = firebaseAuth.getCurrentUser().getUid();
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private UserProfile userProfile = new UserProfile();

    public static SharedPreferences sharedPreferences;
    public static final String SHARED_PREF = "com.example.android.DissSpoonacular";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);


        mFitbitRepository = FitbitRepository.getInstance();
        goalView = (TextView) findViewById(R.id.dailyGoal);
        final DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                userProfile = documentSnapshot.toObject(userProfile.getClass());
                weight = Integer.parseInt(userProfile.getWeight());
                height = userProfile.getHeight();
                age = Integer.parseInt(userProfile.getAge());
                setDailyGoal();
            }
        });



        //ensures that the user sees the onboarding screen
        sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        if (sharedPreferences.getBoolean("onboarding_complete", false)){
            Intent intent = new Intent(this, FirstOnboarding.class);
            startActivity(intent);
        }

    }

    /**
     * Method to the Mifflin St. Jeor Calculator. This calculates the amount of energy expended per day at rest therefore this number
     * gives the user the minimum calorie target for the day
     */
    private void setDailyGoal() {
        Integer goal = 0;
        Double goalDouble;
        //Equation is different for men and women
        if (userProfile.getGender() == 1) {
            goalDouble = (10 * weight + 6.25 * height - 5 * age + 5) * userProfile.getActivityLevel();
            goal = goalDouble.intValue();
        } else if (userProfile.getGender() == 2) {
            goalDouble = (10 * weight + 6.25 * height - 5 * age - 161) * userProfile.getActivityLevel();
            goal = goalDouble.intValue();
            goalView.setText(String.valueOf(goal));
        } else {
            goalView.setText(R.string.no_BMR);
        }

        if(userProfile.getGoal()==1){
            goalView.setText(String.valueOf(goal-500));
        } else if(userProfile.getGoal() ==2 ){
            goalView.setText(String.valueOf(goal));
        } else if(userProfile.getGoal() ==3){
            goalView.setText(String.valueOf(goal+300));
        }
    }

    /**
     * For using their latest Fitbit data to calculate recommended recipes
     * @param view
     */
    public void autoGenerateClicked(View view) {
        String url = mFitbitRepository.getUrl();
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));

    }

    /**
     * To change their settings input on registration
     * @param view
     */
    public void settingsClicked(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    /**
     * To search for recipes of their desired calorie
     * @param view
     */
    public void userInputClicked(View view) {
        Intent intent = new Intent(this, InputPersonalDetailsActivity.class);
        startActivity(intent);
    }

    /**
     * To view their favourite recipes
     * @param view
     */
    public void favouritesClicked(View view) {
        Intent intent = new Intent(this, FavouritesActivity.class);
        startActivity(intent);
    }
}

