package com.example.dissspoonacular.models.fitbitmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Model of the Fitbit Activities Response for Manually inserted values on the Fitbit app.
 */
public class ManualValuesSpecified {

    @SerializedName("calories")
    @Expose
    private Boolean calories;
    @SerializedName("distance")
    @Expose
    private Boolean distance;
    @SerializedName("steps")
    @Expose
    private Boolean steps;

    public Boolean getCalories() {
        return calories;
    }

    public void setCalories(Boolean calories) {
        this.calories = calories;
    }

    public Boolean getDistance() {
        return distance;
    }

    public void setDistance(Boolean distance) {
        this.distance = distance;
    }

    public Boolean getSteps() {
        return steps;
    }

    public void setSteps(Boolean steps) {
        this.steps = steps;
    }

}
