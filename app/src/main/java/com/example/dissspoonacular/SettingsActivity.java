package com.example.dissspoonacular;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.dissspoonacular.models.usermodel.UserProfile;
import com.example.dissspoonacular.user.DietaryRequirementsActivity;
import com.example.dissspoonacular.user.HeightActivity;
import com.example.dissspoonacular.user.IntolerancesActivity;
import com.example.dissspoonacular.user.LoginActivity;
import com.example.dissspoonacular.user.WeightGoalActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Class for the user to edit a chosen selection of details input in registration.
 */
public class SettingsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

    }

    /**
     * Change Weight Goal or Activity Level.
     * @param view
     */

    public void weightGoalClicked(View view) {
        Intent intent = new Intent(this, WeightGoalActivity.class);
        intent.putExtra("fromSettings", true);
        startActivity(intent);
        finish();
    }

    /**
     * Change Height or Weight.
     * @param view
     */
    public void heightWeightClicked(View view) {
        Intent intent = new Intent(this, HeightActivity.class);
        intent.putExtra("fromSettings", true);
        startActivity(intent);
        finish();
    }

    /**
     * Change any user dietary requirements.
     * @param view
     */
    public void dietaryRequirementsClicked(View view) {
        Intent intent = new Intent(this, DietaryRequirementsActivity.class);
        intent.putExtra("fromSettings", true);
        startActivity(intent);
        finish();
    }

    /**
     * Change any user intolerances.
     * @param view
     */
    public void intoleranceClicked(View view) {
        Intent intent = new Intent(this, IntolerancesActivity.class);
        intent.putExtra("fromSettings", true);
        startActivity(intent);
        finish();
    }

    public void logoutClicked(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}