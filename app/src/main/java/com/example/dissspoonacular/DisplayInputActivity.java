package com.example.dissspoonacular;
import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.transition.Explode;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.dissspoonacular.adapters.OnRecipeListener;
import com.example.dissspoonacular.adapters.RecipeRecyclerAdapter;
import com.example.dissspoonacular.models.recipemodels.Result;
import com.example.dissspoonacular.models.usermodel.UserProfile;
import com.example.dissspoonacular.util.VerticalSpacingItemDecorator;
import com.example.dissspoonacular.viewmodels.RecipeListViewModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Calendar;
import java.util.List;

public class DisplayInputActivity extends BaseActivity implements OnRecipeListener {

    private static final String TAG = "DISPLAYINPUT";

    private Calendar calendar = Calendar.getInstance();
    private int timeOfDay = calendar.get(Calendar.HOUR_OF_DAY);

    private RecipeListViewModel mRecipeListViewModel;
    private RecyclerView mRecyclerView;
    private RecipeRecyclerAdapter mRecipeRecyclerAdapter;
    private Integer calories, minCalories, maxCalories, newCalorieTotal, goal;
    private String dietaryRequirements, intolerances, type;
    private Integer minFatty, maxFatty, minProtein, maxProtein, minCarbohydrates, maxCarbohydrates;

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private String userID = firebaseAuth.getCurrentUser().getUid();
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private UserProfile userProfile = new UserProfile();
    private Double carbs, fats, protein;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_input);
        mRecyclerView = findViewById(R.id.recipe_list_input);
        mRecipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModel.class);

        if (timeOfDay >= 0 && timeOfDay < 12) {
            type = "breakfast";
        } else {
            type = "main course";
        }


        getIncomingIntent();
        buildProfile();
        initRecyclerView();
        subscriberObservers();


    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("calories")) {
            calories = getIntent().getIntExtra("calories", 0);
            Log.d("TAG", "getIncomingIntent: " + calories);
        }// end of if intent has

    }
     private void buildProfile(){


        DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                userProfile = documentSnapshot.toObject(userProfile.getClass());
                if (userProfile.getDiets() != null) {
                    dietaryRequirements = userProfile.getDiets();

                } else {
                    dietaryRequirements = null;
                    Log.d(TAG, "onSuccess: diets " + dietaryRequirements);

                }
                if (userProfile.getIntolerances() != null) {
                    intolerances = String.join(",", userProfile.getIntolerances());
                    Log.d(TAG, "onSuccess: intolerances" + intolerances);

                } else {
                    intolerances = null;
                    Log.d(TAG, "onSuccess: intolerances " + intolerances);

                }
                if (userProfile.getGoal() != null) {
                    goal = userProfile.getGoal();
                    Log.d(TAG, "GOAL: " + goal);


                    switch (goal) {
                        case 1:
                            if (calories > 800) {
                                newCalorieTotal = calories - 700;
                                minCalories = newCalorieTotal - 50;
                                maxCalories = newCalorieTotal + 50;
                                carbs = (newCalorieTotal * 0.66) / 4;
                                protein = (newCalorieTotal * 0.23) / 4;
                                fats = (newCalorieTotal * 0.11) / 9;
                            }
                            break;
                        case 2:
                            newCalorieTotal = calories;
                            minCalories = newCalorieTotal - 50;
                            maxCalories = newCalorieTotal + 50;
                            carbs = (newCalorieTotal * 0.66) / 4;
                            protein = (newCalorieTotal * 0.23) / 4;
                            fats = (newCalorieTotal * 0.11) / 9;
                            break;

                        case 3:
                            newCalorieTotal = calories + 300;
                            minCalories = newCalorieTotal - 50;
                            maxCalories = newCalorieTotal + 50;
                            carbs = (newCalorieTotal * 0.66) / 4;
                            protein = (newCalorieTotal * 0.23) / 4;
                            fats = (newCalorieTotal * 0.11) / 9;
                            break;
                        default:
                            //put some in
                            break;
                    }


                    Log.d(TAG, "Calories: " + minCalories);
                    Log.d(TAG, "Calories: " + maxCalories);
                    minFatty = (int) Math.ceil(fats * 0.95);
                    maxFatty = (int) Math.ceil(fats * 1.05);
                    if (minFatty == maxFatty) {
                        minFatty = 0;
                    }
                    Log.d(TAG, "Fat: " + minFatty);
                    Log.d(TAG, "FAT: " + maxFatty);

                    minCarbohydrates = (int) Math.ceil(carbs * 0.95);
                    maxCarbohydrates = (int) Math.ceil(carbs * 1.05);
                    Log.d(TAG, "Carbs: " + minCarbohydrates);
                    Log.d(TAG, "Carbs: " + maxCarbohydrates);

                    minProtein = (int) Math.ceil(protein * 0.95);
                    maxProtein = (int) Math.ceil(protein * 1.05);
                    Log.d(TAG, "PRO: " + maxProtein);
                    Log.d(TAG, "Protein:  " + minProtein);

                } else {
                    Log.d(TAG, "NO GOAL: " + goal);
                }

                searchRecipesApi(minCalories.toString(), maxCalories.toString(),
                        dietaryRequirements, intolerances,
                        minCarbohydrates.toString(), maxCarbohydrates.toString(),
                        minProtein.toString(), maxProtein.toString(),
                        minFatty.toString(), maxFatty.toString(), type);
                showPopUp();
            }
        });
        // end of incoming intent
    }

    //only thing observe is that with Livedata- getRecipes any changes to recipe list
    private void subscriberObservers() {
        mRecipeListViewModel.getRecipes().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                if (results != null) {
                    mRecipeRecyclerAdapter.setRecipes(results);
                } else {
                    mRecipeRecyclerAdapter.setRecipes(results);
                }

            }
        });
    }

    private void searchRecipesApi(String minCalories, String maxCalories, String diet, String intolerance, String minCarbs, String maxCarbs, String minPro, String maxPro, String minFat, String maxFat, String type) {
        mRecipeRecyclerAdapter.displayLoading();
        mRecipeListViewModel.searchRecipesApi(minCalories, maxCalories, diet, intolerance, minCarbs, maxCarbs, minPro, maxPro, minFat, maxFat, type);
    }

    private void initRecyclerView() {
        mRecipeRecyclerAdapter = new RecipeRecyclerAdapter(this, getApplicationContext());
        VerticalSpacingItemDecorator verticalSpacingItemDecorator = new VerticalSpacingItemDecorator(50);
        mRecyclerView.addItemDecoration(verticalSpacingItemDecorator);
        mRecyclerView.setAdapter(mRecipeRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    /**
     * Shows the pop up informing the user of the recommended Micro-Nutrient Breakdown so they can make an informed choice.
     */
    private void showPopUp(){
        //We need to get the instance of the LayoutInflater, use the context of this activity
        LayoutInflater inflater = (LayoutInflater) DisplayInputActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Inflate the view from a predefined XML layout (no need for root id, using entire layout)
        View layout = inflater.inflate(R.layout.layout_nutritional_breakdown,null);

        final ViewGroup root = (ViewGroup) getWindow().getDecorView().getRootView();
        applyDim(root,0.5f);

        ((TextView)layout.findViewById(R.id.popupCalsMin)).setText((int)Math.ceil(newCalorieTotal) + " Calories");
        ((TextView)layout.findViewById(R.id.popupCarbsMin)).setText(maxCarbohydrates + " Carbohydrates");
        ((TextView)layout.findViewById(R.id.popupFatMin)).setText(maxFatty +" Fat");
        ((TextView)layout.findViewById(R.id.popupProteinMin)).setText(maxProtein+ " Protein");

        //Get the devices screen density to calculate correct pixel sizes
        float density=DisplayInputActivity.this.getResources().getDisplayMetrics().density;
        // create a focusable PopupWindow with the given layout and correct size
        final PopupWindow pw = new PopupWindow(layout, (int)density*450, (int)density*650, true);


        //Button to close the pop-up
        ((Button) layout.findViewById(R.id.closepopup)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pw.dismiss();
                clearDim(root);
            }
        });


        // display the pop-up in the center
        pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
        pw.setEnterTransition(new Explode());
    }// show popup

    /**
     * Dims the Activity in the Background so as to draw focus to the popup
     * @param parent
     * @param dimAmount
     */
    private static void applyDim(@NonNull ViewGroup parent, float dimAmount){
        Drawable dim = new ColorDrawable(Color.BLACK);
        dim.setBounds(0, 0, parent.getWidth(), parent.getHeight());
        dim.setAlpha((int) (255 * dimAmount));

        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.add(dim);
    }

    /**
     * Removes the dim over the Activity when the popup is closed
     * @param parent
     */
    private static void clearDim(@NonNull ViewGroup parent){
        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.clear();
    }



    @Override
    public void onRecipeClick(int position) {
        Intent intent = new Intent(this, RecipeActivity.class);
        intent.putExtra("result", mRecipeRecyclerAdapter.getRecipe(position));
        startActivity(intent);
    }


}