package com.example.dissspoonacular.adapters;

import com.example.dissspoonacular.models.recipemodels.Nutrient;
import com.example.dissspoonacular.models.recipemodels.Nutrition;
import com.example.dissspoonacular.models.recipemodels.Result;

import java.util.ArrayList;
import java.util.List;

public class TestingUtils {

    public static final Nutrient NUTRITIENT_RESULT_CARBS = new Nutrient("carbs", 84.0, "grams" );
    public static final Nutrient NUTRITIENT_RESULT_PROTEIN = new Nutrient("protein", 19.0, "grams" );
    public static final List<Nutrient> NUTRIENT_LIST_1 = new ArrayList<Nutrient>(){
        {
            add(NUTRITIENT_RESULT_PROTEIN);
            add(NUTRITIENT_RESULT_CARBS);
        }
    };
    public static final Nutrition NUTRITION_RESULT_1 = new Nutrition(NUTRIENT_LIST_1);
    public static final Result TEST_RESULT_1 = new Result("Pasta with Garlic, Scallions, Cauliflower & Breadcrumbs",715538, "https://spoonacular.com/recipeImages/715538-312x231.jpg", NUTRITION_RESULT_1);






}
