package com.example.dissspoonacular.models.recipemodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 *Model for Temperature Recipe Response.
 */
public class Temperature {

    @SerializedName("number")
    @Expose
    private Double number;
    @SerializedName("unit")
    @Expose
    private String unit;

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
