package com.example.dissspoonacular.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dissspoonacular.R;
import com.example.dissspoonacular.database.FavouriteDatabase;
import com.example.dissspoonacular.models.recipemodels.Nutrient;
import com.example.dissspoonacular.models.recipemodels.Nutrition;
import com.example.dissspoonacular.models.recipemodels.Result;
import java.util.ArrayList;
import java.util.List;

/**
 * Adapter responsible for Setting views into the Recycler View.
 * One Recycler View is responsible for displaying RecipesLists, Favourites List and Loading Screen.
 * RecipeLists and Favourites are distinguished as Favourites do not have nutrients saved.
 *
 */
public class RecipeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int RECIPE_TYPE = 1;
    private static final int LOADING_TYPE = 2;
    private int clicked = 0;
    private Context context;

    private FavouriteDatabase favouriteDatabase;


    private List<Result> mRecipes;
    private OnRecipeListener mOnRecipeListener;

    private Double doubleCals, doubleCarbs, doubleProtein, doubleFats;


    public RecipeRecyclerAdapter(OnRecipeListener onRecipeListener, Context context) {
        this.mOnRecipeListener = onRecipeListener;
        mRecipes = new ArrayList<>();
        this.context = context;

    }

    /**
     * Inflates necessary View
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = null;
        switch (viewType) {

            case LOADING_TYPE: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_loading_list_item, parent, false);
                return new LoadingViewHolder(view, null);
            }
            default: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recipe_list_item, parent, false);
                return new RecipeViewHolder(view, mOnRecipeListener);
            }
        }
    }

    /**
     * Displays either the Loading or Recipe Screen based on Recipe Title.
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (mRecipes.get(position).getTitle().equals("LOADING...")) {
            return LOADING_TYPE;
        } else {
            mRecipes.get(position).setFavStatus(null);
            return RECIPE_TYPE;
        }
    }

    /**
     * Method to set the last title as "LOADING...".
     * If want to display the loading screen call this method.
     */
    public void displayLoading() {
        if (!isLoading()) {
            Result result = new Result();
            result.setTitle("LOADING...");
            List<Result> loadingList = new ArrayList<>();
            loadingList.add(result);
            mRecipes = loadingList;
            notifyDataSetChanged();
        } else {
            mRecipes = null;
        }
    }

    /**
     * Method to determine if Loading Screen is required.
     * @return
     */
    private boolean isLoading() {
        if (mRecipes != null) {
            if (mRecipes.size() > 0) {
                //get last index of recipe in list and checks if it is set to loading.
                return mRecipes.get(mRecipes.size() - 1).getTitle().equals("LOADING...");
            }
        }
        return false;
    }

    /**
     * Set the view in the ViewHolder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        int itemViewType = getItemViewType(position);
        if (itemViewType == RECIPE_TYPE) {
            favouriteDatabase = FavouriteDatabase.getInstance(context);

            final Result result = mRecipes.get(position);


            RequestOptions requestOptions = new RequestOptions()
                    .centerCrop()
                    .error(R.drawable.ic_launcher_background);

            //Use Glide Library to set Recipe Image
            Glide.with(((RecipeViewHolder) holder).itemView)
                    .setDefaultRequestOptions(requestOptions)
                    .load(mRecipes.get(position).getImage()).into(((RecipeViewHolder) holder).image);


            ((RecipeViewHolder) holder).title.setText(mRecipes.get(position).getTitle());

            //Get Recipe Nutrition
            Nutrition nutrition = mRecipes.get(position).getNutrition();
            if (nutrition != null) {
                List<Nutrient> nutrients = nutrition.getNutrients();
                for (int loop = 0; loop < nutrients.size(); loop++) {
                    switch (nutrients.get(loop).getTitle()) {
                        case "Calories":
                            doubleCals = nutrients.get(loop).getAmount();
                            break;
                        case "Carbohydrates":
                            doubleCarbs = nutrients.get(loop).getAmount();
                            break;
                        case "Protein":
                            doubleProtein = nutrients.get(loop).getAmount();
                            break;
                        case "Fat":
                            doubleFats = nutrients.get(loop).getAmount();
                            break;
                        default:
                            break;
                    }


                }

                String calString = String.format("%.2f", doubleCals);
                String carbString = String.format("%.2f", doubleCarbs);
                String calPro = String.format("%.2f", doubleProtein);
                String calFats = String.format("%.2f", doubleFats);

                ((RecipeViewHolder) holder).calories.setText(calString + " " + "Calories");
                ((RecipeViewHolder) holder).carbohydrates.setText(carbString + " " + "Carbohydrates");
                ((RecipeViewHolder) holder).protein.setText(calPro + " " + "Protein");
                ((RecipeViewHolder) holder).fats.setText(calFats + " " + "Fat");


                ((RecipeViewHolder) holder).favBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clicked++;
                        //An odd number of clicks means that the recipe has been liked. Add to the Favourites Database
                        if (clicked % 2 == 1) {
                            result.setFavStatus("1");
                            favouriteDatabase.getFavouriteDao().insertFavourites(result);
                            ((RecipeViewHolder) holder).favBtn.setBackgroundResource(R.drawable.favourite_selected);
                        }
                        //An even number of clicks means that the user has removed it from the Favourites Database
                        if (clicked % 2 == 0) {
                            result.setFavStatus("0");
                            favouriteDatabase.getFavouriteDao().deleteCertain(result.getId());
                            ((RecipeViewHolder) holder).favBtn.setBackgroundResource(R.drawable.favourite);
                        }

                    }

                });

            } else {
                // View Holder corresponding to the Favourites List
                ((RecipeViewHolder) holder).favBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clicked++;
                        //An odd number of clicks means that the recipe has been removed from favourites
                        if (clicked % 2 == 1) {
                            result.setFavStatus("0");
                            favouriteDatabase.getFavouriteDao().deleteCertain(result.getId());
                            ((RecipeViewHolder) holder).favBtn.setBackgroundResource(R.drawable.favourite);
                        }
                        //An even number of clicks means that the user has added it to the Favourites Database
                        if (clicked % 2 == 0) {
                            result.setFavStatus("1");
                            favouriteDatabase.getFavouriteDao().insertFavourites(result);
                            ((RecipeViewHolder) holder).favBtn.setBackgroundResource(R.drawable.favourite_selected);
                        }

                    }

                });



                ((RecipeViewHolder) holder).calories.setText("");

            }

            //Setting the Favourite button to Red if the user has previously placed the recipe into their favourites
            if (favouriteDatabase.getFavouriteDao().getCertain(mRecipes.get(position).getId()) == 1) {
                ((RecipeViewHolder) holder).favBtn.setBackgroundResource(R.drawable.favourite_selected);

            }


        }

    }


    @Override
    public int getItemCount() {
        if (mRecipes != null) {
            return mRecipes.size();
        }
        return 0;
    }

    /**
     * Sets the Recycler View to the list of results.
     * @param recipes
     */
    public void setRecipes(List<Result> recipes) {
        mRecipes = recipes;
        notifyDataSetChanged();
    }

    /**
     * Get Recipe at each position.
     * @param position
     * @return
     */
    public Result getRecipe(int position) {
        if (mRecipes != null) {
            if (mRecipes.size() > 0) {
                return mRecipes.get(position);
            }
        }
        return null;
    }


}