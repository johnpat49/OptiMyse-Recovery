package com.example.dissspoonacular;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.dissspoonacular.adapters.OnRecipeListener;
import com.example.dissspoonacular.adapters.RecipeRecyclerAdapter;
import com.example.dissspoonacular.database.FavouriteDatabase;
import com.example.dissspoonacular.models.recipemodels.Result;
import com.example.dissspoonacular.requests.reciperequests.RecipeApiClient;
import com.example.dissspoonacular.util.VerticalSpacingItemDecorator;
import com.example.dissspoonacular.viewmodels.RecipeListViewModel;

import java.util.List;

public class FavouritesActivity extends BaseActivity implements OnRecipeListener {

    private static final String TAG = "FAVOURITEACTIVITY" ;
    private RecyclerView recyclerView;
    private RecipeListViewModel recipeListViewModel;
    private RecipeRecyclerAdapter recipeRecyclerAdapter;
    private FavouriteDatabase favouriteDatabase;
    private List<Result> mFavourites;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        recyclerView = findViewById(R.id.recipe_list_favourite);
        recipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModel.class);
        favouriteDatabase = FavouriteDatabase.getInstance(this);
        mFavourites = favouriteDatabase.getFavouriteDao().getFavourites();


        for(int loop = 0 ; loop < mFavourites.size(); loop++){
            Log.d(TAG, "onCreate: "+mFavourites.get(loop).getTitle());
            Log.d(TAG, "onCreate: "+mFavourites.get(loop).getFavStatus());
        }

        Log.d(TAG, "onCreate: "+mFavourites.size());


        initRecyclerView();
        popRecyclerView();


    }

    /**
     * Initialise the RecyclerView.
     */
    private void initRecyclerView() {
        recipeRecyclerAdapter = new RecipeRecyclerAdapter(this, getApplicationContext());
        VerticalSpacingItemDecorator verticalSpacingItemDecorator = new VerticalSpacingItemDecorator(50);
        recyclerView.addItemDecoration(verticalSpacingItemDecorator);
        recyclerView.setAdapter(recipeRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Populates the RecyclerView.
     */
    private void popRecyclerView(){
        if(mFavourites.size()>0) {
            recipeRecyclerAdapter.setRecipes(mFavourites);
        }
    }

    @Override
    public void onRecipeClick(int position) {
        Intent intent = new Intent(this, RecipeActivity.class);
        intent.putExtra("result", recipeRecyclerAdapter.getRecipe(position));
        startActivity(intent);
    }
}