package com.example.dissspoonacular.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dissspoonacular.LandingActivity;
import com.example.dissspoonacular.R;
import com.example.dissspoonacular.models.usermodel.UserProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * Class where inital registration occurs. Saves users email, First name, Surname and password.
 */
public class RegistrationActivity extends AppCompatActivity {
    public static EditText fName, lName, email, pass, cnfPass;
    TextView registered;
    Button next;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    String userID;
    FirebaseFirestore firestore;
    public static UserProfile userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        fName = (EditText) findViewById(R.id.RegFnameID);
        lName = (EditText) findViewById(R.id.RegSnameID);
        email = (EditText) findViewById(R.id.RegEmailID);
        pass = (EditText) findViewById(R.id.RegPassID);
        cnfPass = (EditText) findViewById(R.id.RegCnfpassID);
        registered = (TextView) findViewById(R.id.registeredID);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        next = (Button) findViewById(R.id.registerID);

        fAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

//        checks if user is logged in and if they're takes them to the Landing activity.
        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), LandingActivity.class));
            finish();
        }


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                final String firstName = fName.getText().toString().trim();
                final String lastName = lName.getText().toString().trim();
                final String mail = email.getText().toString().trim();
                String password = pass.getText().toString().trim();
                String confirm = cnfPass.getText().toString().trim();

                if (firstName.isEmpty() || lastName.isEmpty() || mail.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "All Fields must be filled in", Toast.LENGTH_SHORT).show();
                } else if (!confirm.equals(password)) {
                    Toast.makeText(RegistrationActivity.this, "Passwords Do Not Match", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 8) {
                    Toast.makeText(RegistrationActivity.this, "Password must be at least 8 characters in length", Toast.LENGTH_SHORT).show();
                } else {


                    fAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                userID = fAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = firestore.collection("users").document(userID);
                                Map<String, Object> clients = new HashMap<>();
                                clients.put("firstName", firstName);
                                clients.put("lastName", lastName);
                                clients.put("email", mail);
                                documentReference.set(clients).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        startActivity(new Intent(getApplicationContext(), GenderActivity.class));
                                    }
                                });

                            } else {
                                Toast.makeText(RegistrationActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }


            }
        });

        registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });


    }




}

