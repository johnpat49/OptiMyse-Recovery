package com.example.dissspoonacular.models.recipemodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 *Model for Ingredient Recipe Response.
 */
public class Ingredient {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("localizedName")
    @Expose
    private String localizedName;
    @SerializedName("image")
    @Expose
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Ingredient(Integer id, String name, String localizedName, String image) {
        this.id = id;
        this.name = name;
        this.localizedName = localizedName;
        this.image = image;
    }
}
