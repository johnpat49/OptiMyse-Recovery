package com.example.dissspoonacular.user;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;

import com.example.dissspoonacular.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
/**
 * Class for user to Set their Gender and DOB.
 */
public class GenderActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;
    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();



        findViewById(R.id.showDialogue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialogue();
            }
        });


    }

    public void onRadioButtonClicked(View view) {
        DocumentReference documentReference = firestore.collection("users").document(userID);
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.male:
                if (checked) {
                    documentReference.update("gender", 1);
                }
                break;

            case R.id.female:
                if (checked) {
                    documentReference.update("gender", 2);

                }
                break;
            default:
                break;
        }
    }

    private void showDatePickerDialogue() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        //Stop user picking dates in future
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();

    }

    //Gets the Users Age from Specified DOB
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Integer age;
        age = Period.between(
                LocalDate.of(year, month, dayOfMonth),
                LocalDate.now()
        ).getYears();

        DocumentReference documentReference = firestore.collection("users").document(userID);
        documentReference.update("age", age.toString());
        startActivity(new Intent(getApplicationContext(), HeightActivity.class));

    }


}