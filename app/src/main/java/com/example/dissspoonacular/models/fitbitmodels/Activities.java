package com.example.dissspoonacular.models.fitbitmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Model of the Fitbit Activities Response.
 */
public class Activities {

    @SerializedName("activities")
    @Expose
    private List<Activity> activities = null;
    @SerializedName("pagination")
    @Expose
    private Pagination pagination;

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }


}
