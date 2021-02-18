package com.example.dissspoonacular.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.dissspoonacular.R;
import com.example.dissspoonacular.SettingsActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Class for user to Set their Weight Targets and current Activity Level.
 */
public class WeightGoalActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    String userID;
    Button mNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_goal);
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        userID = firebaseAuth.getCurrentUser().getUid();

        mNext = (Button) findViewById(R.id.nextPgGoal);
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DietaryRequirementsActivity.class));

            }
        });


        //If this activity is launched with intent "fromSettings" means user is changing settings therefore need to return to Settings page not next page.
        if(getIntent().hasExtra("fromSettings")){
            mNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                    finish();

                }
            });
        }
    }




    public void onRadioButtonClicked(View view) {
        DocumentReference documentReference = firestore.collection("users").document(userID);
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.loss:
                if (checked) {
                    documentReference.update("goal", 1);

                }
                break;

            case R.id.maintain:
                if (checked) {
                    documentReference.update("goal", 2);
                }
                break;
            case R.id.gain:
                if (checked) {
                    documentReference.update("goal", 3);
                }
                break;
            default:
                break;
        }

    }

    /**
     * provisions if put in none cant select others
     *
     * @param view
     */
    public void onRadioButtonClicked2(View view) {
        DocumentReference documentReference = firestore.collection("users").document(userID);
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.sedentary:
                if (checked) {
                    documentReference.update("activityLevel", 1.2);

                }
                break;

            case R.id.lightActive:
                if (checked) {
                    documentReference.update("activityLevel", 1.375);

                }
                break;
            case R.id.moderateActive:
                if (checked) {
                    documentReference.update("activityLevel", 1.55);

                }
                break;
            case R.id.veryActive:
                if (checked) {
                    documentReference.update("activityLevel", 1.75);
                }
                break;
            default:
                break;
        }

    }
}