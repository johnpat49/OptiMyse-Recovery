package com.example.dissspoonacular.onboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.dissspoonacular.R;

public class OnboardingActivity extends AppCompatActivity {

    public static SharedPreferences sharedPreferences;
    public static final String SHARED_PREF = "com.example.android.DissSpoonacular";
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

//        sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
//        if (sharedPreferences.getBoolean("onboarding_complete", false)){
//            Intent intent = new Intent(this, FirstOnboarding.class);
//            startActivity(intent);
//        }

        viewPager = findViewById(R.id.viewPager);
        IntroAdapter introAdapter = new IntroAdapter(getSupportFragmentManager());
        viewPager.setAdapter(introAdapter);


    }
}