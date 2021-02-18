package com.example.dissspoonacular.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dissspoonacular.LandingActivity;
import com.example.dissspoonacular.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText email, pass;
    private Button loginButton;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.LogEmailID);
        pass = (EditText) findViewById(R.id.LogPassID);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progress_bar);

        loginButton= (Button) findViewById(R.id.LoginID);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString().trim();
                String password = pass.getText().toString().trim();

                firebaseAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(), LandingActivity.class));
                        } else{
                            Toast.makeText(getApplicationContext(),"Email or Password Incorrect" ,Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });


    }

    public void registerClicked(View view) {
        startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));

    }
}