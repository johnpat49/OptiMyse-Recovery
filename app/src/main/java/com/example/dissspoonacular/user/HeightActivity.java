package com.example.dissspoonacular.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dissspoonacular.R;
import com.example.dissspoonacular.SettingsActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Class for user to Set their Height and Weight.
 */
public class HeightActivity extends AppCompatActivity {

    EditText mFt, mIn, mKg;
    Button mNext;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height);

        mFt = (EditText) findViewById(R.id.inputHeightFt);
        mIn = (EditText) findViewById(R.id.inputHeightInch);
        mKg = (EditText) findViewById(R.id.inputKgs);
        mNext = (Button) findViewById(R.id.nextPgId);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();


        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference documentReference = firestore.collection("users").document(userID);
                String ft = mFt.getText().toString().trim();
                String inch = mIn.getText().toString().trim();
                Integer total = (Integer.parseInt(ft) * 12) + Integer.parseInt(inch);
                String kg = mKg.getText().toString().trim();

                //Ensure all fields are completed
                if (ft.isEmpty() || inch.isEmpty() || kg.isEmpty()) {
                    Toast.makeText(HeightActivity.this, "All Fields Must be filled", Toast.LENGTH_SHORT).show();
                } else {

                    documentReference.update("weight", kg);
                    documentReference.update("height", total);
                    startActivity(new Intent(getApplicationContext(), WeightGoalActivity.class));

                }


            }
        });
        //If this activity is launched with intent "fromSettings" means user is changing settings therefore need to return to Settings page not next page.
        if (getIntent().hasExtra("fromSettings")) {
            mNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                    finish();

                }
            });
        }


    }
}