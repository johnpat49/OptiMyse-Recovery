package com.example.dissspoonacular.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.dissspoonacular.R;
import com.example.dissspoonacular.SettingsActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Class for user to Set their Dietary Requirements.
 */
public class DietaryRequirementsActivity extends AppCompatActivity {

    RadioButton one, two, three, four, five, six;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;
    private String userID;
    private TextView text;
    Button mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dietary_requirements);


        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        one = (RadioButton) findViewById(R.id.vegetarian);
        two = (RadioButton) findViewById(R.id.vegan);
        three = (RadioButton) findViewById(R.id.gluten);
        four = (RadioButton) findViewById(R.id.pesc);
        five = (RadioButton) findViewById(R.id.keto);
        six = (RadioButton) findViewById(R.id.no_requirements);


        userID = firebaseAuth.getCurrentUser().getUid();
        mNext = findViewById(R.id.nextDiet);
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), IntolerancesActivity.class));
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

    /**
     * The Spoonacular Api only accepts 1 diet in a call therefore use Radio button.
     * @param view
     */

    public void onRadioButtonClicked(View view) {
        DocumentReference documentReference = firestore.collection("users").document(userID);
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.vegetarian:
                if (checked) {
                    documentReference.update("diets", "vegetarian");

                }
                break;

            case R.id.vegan:
                if (checked) {
                    documentReference.update("diets", "vegan");

                }
                break;
            case R.id.gluten:
                if (checked) {
                    documentReference.update("diets", "gluten free");

                }
                break;

            case R.id.pesc:
                if (checked) {
                    documentReference.update("diets", "pescetarian");

                }
                break;
            case R.id.keto:
                if (checked) {
                    documentReference.update("diets", "ketonic");

                }
                break;

            case R.id.no_requirements:
                if (checked) {
                    documentReference.update("diets", null);

                }
                break;
            default:
                break;
        }

    }

}