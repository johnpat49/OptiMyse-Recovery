package com.example.dissspoonacular.models.recipemodels;
/**
 *Model for individual Nutrient Recipe Response.
 */
public class Nutrient {

    private String title;
    private Double amount;
    private String unit;

    public Nutrient() {
    }

    public Nutrient(String title, double amount, String unit) {
        this.title = title;
        this.amount = amount;
        this.unit = unit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Nutrition{" +
                "title='" + title + '\'' +
                ", amount=" + amount +
                ", cal='" + unit + '\'' +
                '}';
    }
}
