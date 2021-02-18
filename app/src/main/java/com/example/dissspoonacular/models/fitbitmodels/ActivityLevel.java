package com.example.dissspoonacular.models.fitbitmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Model of the Fitbit Activity Level Response.
 */
public class ActivityLevel {

    @SerializedName("minutes")
    @Expose
    private Integer minutes;
    @SerializedName("name")
    @Expose
    private String name;

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
