package com.example.dissspoonacular.onboarding;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dissspoonacular.LandingActivity;
import com.example.dissspoonacular.R;

public class ThirdOnboarding extends Fragment {


    TextView done;
    TextView back;
    ViewPager viewPager;
    public static SharedPreferences sharedPreferences;
    public static final String SHARED_PREF = "com.example.android.DissSpoonacular";


    public ThirdOnboarding() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third_onboarding, container, false);

        viewPager = getActivity().findViewById(R.id.viewPager);
        done = view.findViewById(R.id.slideThreeDone);

        back = view.findViewById(R.id.slideThreeBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishOnboarding();
            }
        });

        return view;
    }

    private void finishOnboarding() {
        // Get the shared preferences
       SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("onboarding complete", true);
        editor.apply();

        // Launch the landing Activity
        Intent intent = new Intent(getActivity(), LandingActivity.class);
        startActivity(intent);

        // Close the OnboardingActivity
        getActivity().finish();
    }


}