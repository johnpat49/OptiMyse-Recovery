package com.example.dissspoonacular.models.recipemodels;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
/**
 *Model for Results of Spoonacular APi call. Acts as the entity in which is added into the FavouriteDatabase
 */
@Entity(tableName = "favouriteRecipes")
public class Result implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int autoId;

    @NonNull
    @ColumnInfo(name = "recipeID")
    private int id;

    @ColumnInfo(name = "recipeTitle")
    private String title;

    @ColumnInfo(name = "recipeImage")
    private String image;

    @ColumnInfo(name = "favouriteStatus")
    private String favStatus;

    @Ignore
    private Nutrition nutrition;

    public int getAutoId() {
        return autoId;
    }

    public void setAutoId(int autoId) {
        this.autoId = autoId;
    }

    public String getFavStatus() {
        return favStatus;
    }

    public void setFavStatus(String favStatus) {
        this.favStatus = favStatus;
    }

    public Result(String title, int id, String image, Nutrition nutrition) {
        this.title = title;
        this.id = id;
        this.image = image;
        this.nutrition = nutrition;
    }

    public Result() {

    }

    protected Result(Parcel in) {
        title = in.readString();
        id = in.readInt();
        image = in.readString();
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }

//    @Override
//    public String toString() {
//        return "Recipe{" +
//                "title='" + title + '\'' +
//                ", id=" + id +
//                ", image='" + image + '\'' +
//                ", nutrition=" + Arrays.toString(nutrition) +
//                '}';
//    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeInt(id);
        parcel.writeString(image);
    }
}
