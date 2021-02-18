package com.example.dissspoonacular.onboarding;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dissspoonacular.R;

public class FirstOnboarding extends Fragment {

    TextView next;
    ViewPager viewPager;
    public static SharedPreferences sharedPreferences;
    public static final String SHARED_PREF = "com.example.android.DissSpoonacular";

    public FirstOnboarding() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first_onboarding, container, false);

        viewPager = getActivity().findViewById(R.id.viewPager);
//        next = view.findViewById(R.id.slideOneNext);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("onboarding complete", false);
        editor.apply();

//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewPager.setCurrentItem(1);
//            }
//        });
        return view;
    }
}