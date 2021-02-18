package com.example.dissspoonacular.adapters;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dissspoonacular.R;

/**
 * Initialize Views in the Recycler View.
 */
public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView title, calories, carbohydrates, protein, fats;
    Button favBtn;
    ImageView image;
    OnRecipeListener onRecipeListener;


    public RecipeViewHolder(@NonNull View itemView, OnRecipeListener onRecipeListener) {
        super(itemView);
        this.onRecipeListener = onRecipeListener;
        title = itemView.findViewById(R.id.recipe_title);
        calories = itemView.findViewById(R.id.recipe_calories);
        carbohydrates = itemView.findViewById(R.id.recipe_carbohydrates);
        protein = itemView.findViewById(R.id.recipe_protein);
        fats = itemView.findViewById(R.id.recipe_fats);
        image = itemView.findViewById(R.id.recipe_image);
        favBtn = itemView.findViewById(R.id.likedRecipeBtn);

        itemView.setOnClickListener(this);
            }


        @Override
        public void onClick(View view) {
        onRecipeListener.onRecipeClick(getAdapterPosition()); }


    }






