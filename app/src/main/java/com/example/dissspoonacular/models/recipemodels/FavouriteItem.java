package com.example.dissspoonacular.models.recipemodels;
/**
 *Model for Recipe Response from a Spoonacular Api call.
 */
public class FavouriteItem {

    private String title;
    private String id;
    private int image;

    public FavouriteItem() {
    }

    public FavouriteItem(String title, String id, int image) {
        this.title = title;
        this.id = id;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
