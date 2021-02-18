package com.example.dissspoonacular.requests.firebaserequests;

import com.example.dissspoonacular.models.usermodel.UserProfile;
import com.example.dissspoonacular.requests.fitbitrequests.FitbitApiClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.List;

public class FirebaseApiClient {

    private static FirebaseApiClient instance;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    String userID;
    UserProfile user = new UserProfile();

    public static FirebaseApiClient getInstance() {
        if (instance == null) {
            instance = new FirebaseApiClient();
        }
        return instance;
    }


    public FirebaseApiClient() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();

    }

    public UserProfile getProfile() {
        DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                UserProfile userProfile = documentSnapshot.toObject(UserProfile.class);
                user = userProfile;
            }
        });

        return user;
    }


}
