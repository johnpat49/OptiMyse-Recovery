package com.example.dissspoonacular.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.dissspoonacular.LandingActivity;
import com.example.dissspoonacular.R;
import com.example.dissspoonacular.SettingsActivity;
import com.example.dissspoonacular.onboarding.OnboardingActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
/**
 * Class for user to Set their Dietary Intolerances.
 */
public class IntolerancesActivity extends AppCompatActivity {

    private static final String TAG = "Intolerances";
    CheckBox one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;
    private String userID;
    Button mNext;
    CheckBox[] option = new CheckBox[12];
    List<String> intolerances = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intolerances);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        one = (CheckBox) findViewById(R.id.dairy);
        two = (CheckBox) findViewById(R.id.egg);
        three = (CheckBox) findViewById(R.id.gluten);
        four = (CheckBox) findViewById(R.id.grain);
        five = (CheckBox) findViewById(R.id.peanut);
        six = (CheckBox) findViewById(R.id.shell);
        seven = (CheckBox) findViewById(R.id.sesame);
        eight = (CheckBox) findViewById(R.id.sea);
        nine = (CheckBox) findViewById(R.id.soy);
        ten = (CheckBox) findViewById(R.id.sulfite);
        eleven = (CheckBox) findViewById(R.id.tree);
        twelve = (CheckBox) findViewById(R.id.wheat);
        thirteen = (CheckBox) findViewById(R.id.no_intolerances);

        option = new CheckBox[]{one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve};


        userID = firebaseAuth.getCurrentUser().getUid();
        mNext = (Button) findViewById(R.id.button_finish_reg);
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), OnboardingActivity.class));
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
     * Several Intolerances can be selected at once therefore need to use CheckBox.
     * @param view
     */
    public void onButtonClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();
        DocumentReference documentReference = firestore.collection("users").document(userID);

        //Ensures a selection is made
        if (!((CheckBox) view).isChecked()) {
            Toast.makeText(IntolerancesActivity.this, "Please Make a Selection", Toast.LENGTH_SHORT).show();
        } else if (thirteen.isChecked()) {
            documentReference.update("intolerances", null);
        } else { // adds any selected to an array
            for (CheckBox checkBox : option) {
                if (checkBox.isChecked()) {
                    intolerances.add(checkBox.getText().toString());
                }
            }
            removeDuplicates(intolerances);
            documentReference.update("intolerances", intolerances);
        }


    }

    /**
     * Removes the possibility of the same item being added twice into the database by following pattern check, uncheck, check.
     * @param list
     * @return
     */
    public List<String> removeDuplicates(List<String> list) {
        Set<String> set = new LinkedHashSet<>();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        return list;
    }


}




