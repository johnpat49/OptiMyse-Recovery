package com.example.dissspoonacular;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Activity for user to input their chosen calorie amount to search for.
 */
public class InputPersonalDetailsActivity extends AppCompatActivity {

    //user input
    private EditText inputCalories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_personal_details);
        inputCalories = findViewById(R.id.customCal);

    }

    /**
     * Creates intent to send to be viewed
     * @param view
     */
    public void customClicked(View view) {
        Integer calories = Integer.parseInt(inputCalories.getText().toString());
        Intent intent = new Intent(this, DisplayInputActivity.class);
        intent.putExtra("calories", calories);
        startActivity(intent);
    }

}
