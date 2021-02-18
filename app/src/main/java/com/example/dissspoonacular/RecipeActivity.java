package com.example.dissspoonacular;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dissspoonacular.models.recipemodels.Ingredient;
import com.example.dissspoonacular.models.recipemodels.Name;
import com.example.dissspoonacular.models.recipemodels.Result;
import com.example.dissspoonacular.models.recipemodels.Step;
import com.example.dissspoonacular.viewmodels.RecipeViewModel;


import java.util.ArrayList;
import java.util.List;

public class RecipeActivity extends BaseActivity {

    private static final String TAG = "RECIPE";
    private ImageView recipeImage;
    private TextView recipeTitle;
    private LinearLayout recipeIngredientsContainer;
    private LinearLayout recipeInstructionsContainer;
    private ScrollView scrollView;
    private RecipeViewModel mRecipeVM;
    private String imgUrl;
    private int recipeId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        recipeImage = findViewById(R.id.recipe_image);
        recipeTitle = findViewById(R.id.recipe_title);
        recipeIngredientsContainer = findViewById(R.id.ingredients_container);
        recipeInstructionsContainer = findViewById(R.id.instructions_container);
        scrollView = findViewById(R.id.parent);
        mRecipeVM = ViewModelProviders.of(this).get(RecipeViewModel.class);



        showProgressBar(true);
        subscribeObserve();
        getIncomingIntent();


    }

    /**
     * Get recipe information from the onClick
     */
    private void getIncomingIntent() {
        if (getIntent().hasExtra("result")) {
            Result result = getIntent().getParcelableExtra("result");
            recipeTitle.setText(result.getTitle());
            imgUrl = result.getImage();
            recipeId = result.getId();
            mRecipeVM.getSteps(String.valueOf(recipeId));
            Log.d(TAG, "getIncomingIntent: "+result.getTitle());

        }
    }

    /**
     * Observes changes in recipe
     */
    private void subscribeObserve() {
        mRecipeVM.getRecipe().observe(this, new Observer<List<Name>>() {
            @Override
            public void onChanged(List<Name> names) {
                if (names != null) {
                    setRecipeProperties(names);
                }
            }
        });

    }

    /**
     * Sets the Views for the chosen recipe
     * @param name
     */
    private void setRecipeProperties(List<Name> name) {
        if (name != null) {
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background);

            //using the Glide image library to set the image
            Glide.with(this).setDefaultRequestOptions(requestOptions).load(imgUrl).into(recipeImage);

            //have to dynamically set the container as size of instructions is not fixed
            recipeInstructionsContainer.removeAllViews();
            for (Name names : name) {
                List<Step> temp = names.getSteps();
                for (int loop = 0; loop < temp.size(); loop++) {
                    TextView textView = new TextView(this);
                    textView.setText(temp.get(loop).getNumber() + ". " + temp.get(loop).getStep());
                    textView.setTextSize(15);
                    textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    recipeInstructionsContainer.addView(textView);
                }

            }
            //have to dynamically set the container as size of ingredients is not fixed
            recipeIngredientsContainer.removeAllViews();
            for (Name names : name) {
                List<Step> temp = names.getSteps();
                List<Ingredient> ingredients = new ArrayList<>();
                for (int loop = 0; loop < temp.size(); loop++) {
                    ingredients = temp.get(loop).getIngredients();
                    for (int inner = 0; inner < ingredients.size(); inner++) {
                        TextView textView = new TextView(this);
                        textView.setText(ingredients.get(inner).getName());
                        textView.setTextSize(15);
                        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        recipeIngredientsContainer.addView(textView);
                    }

                }


            }

        }
        showParent();
        showProgressBar(false);
    }

    private void showParent() {
        scrollView.setVisibility(View.VISIBLE);
    }

}
