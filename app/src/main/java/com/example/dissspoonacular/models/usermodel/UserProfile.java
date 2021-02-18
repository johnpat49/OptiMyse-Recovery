package com.example.dissspoonacular.models.usermodel;

import java.util.List;
/**
 *Model for User created during registration.
 */
public class UserProfile {

    private String email;
    private String lastName;
    private String firstName;
    private String id;
    private String weight;
    private Integer height;
    private Integer gender;
    private String age;
    private Integer goal;
    private Double activityLevel;
    private List<String> intolerances;
    private String diets;

    public UserProfile() {

    }

    public UserProfile(String weight, Integer height, Integer gender, String age, Integer goal, Double activityLevel, List<String> intolerances, String diets) {
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.age = age;
        this.goal = goal;
        this.activityLevel = activityLevel;
        this.intolerances = intolerances;
        this.diets = diets;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Double getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(Double activityLevel) {
        this.activityLevel = activityLevel;
    }

    public List<String> getIntolerances() {
        return intolerances;
    }

    public void setIntolerances(List<String> intolerances) {
        this.intolerances = intolerances;
    }

    public String getDiets() {
        return diets;
    }

    public void setDiets(String dietaryRequirements) {
        this.diets = dietaryRequirements;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
