package com.example.dissspoonacular.onboarding;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class IntroAdapter extends FragmentPagerAdapter {

    public IntroAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FirstOnboarding();
            case 1:
                return new SecondOnboarding();
            case 2:
                return new ThirdOnboarding();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}
