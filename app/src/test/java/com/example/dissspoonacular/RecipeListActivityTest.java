package com.example.dissspoonacular;


import com.example.dissspoonacular.models.usermodel.UserProfile;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;



import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class RecipeListActivityTest {

    UserProfile user1 = new UserProfile();
    UserProfile user2 = new UserProfile();
    List<String> intolerances = new ArrayList<>();

    Integer minCalories, maxCalories,calories,newCalorieTotal;
    private Integer minFatty, maxFatty, minProtein, maxProtein, minCarbohydrates, maxCarbohydrates;
    private Double carbs, fats, protein;



    @Before
    public void setUp() throws Exception {

        user1.setDiets("vegetarian");
        intolerances.add("Dairy");
        intolerances.add("Gluten");
        user1.setIntolerances(intolerances);
        user1.setGoal(1);

        user2.setDiets(null);
        user2.setIntolerances(null);


    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void onRecipeClick() {
    }

    @Test
    public void buildUserProfileDietsNotNull(){

        String diets = "vegetarian";
        assertEquals(diets, user1.getDiets());

    }

    @Test
    public void buildUserProfileDietsNull(){

        String diets = null;
        assertEquals(diets, user2.getDiets());

    }

    @Test
    public void buildUserProfileIntoleranceNotNull(){

         List<String> intolerancesTest = new ArrayList<>();
         intolerancesTest.add("Dairy");
         intolerancesTest.add("Gluten");
        assertEquals(intolerancesTest, user1.getIntolerances());

    }

    @Test
    public void buildUserProfileIntoleranceNull(){

        List<String> intoleranceTest = null;
        assertEquals(intoleranceTest, user2.getIntolerances());

    }

    @Test
    public void buildUserProfileGetGoal(){
        Integer goal = 1;
        assertEquals(goal, user1.getGoal());
    }

    /**
     * Tests Switch Statement
     */
    @Test
    public void buildUserProfileSwitchTest(){
        Integer goal = 1;
        Integer goal2 = 2;
        Integer goal3 = 3;
        switch (user1.getGoal()){
            case 1 :
                assertEquals(goal, user1.getGoal());
                break;
            case 2:
                assertNotEquals(goal2,user1.getGoal() );
                break;
            case 3:
                assertNotEquals(goal3,user1.getGoal());
                break;
            default:
        }
    }

    /**
     * Tests correct values are calculated when the goal is 1 with minimum values
     */
    @Test
    public void buildUserProfileGoal1Min(){

      calories= 150;
      Integer testNewCalorieTotal = 100;
      Integer testMinCalories = 75;
        Integer testMaxCalories = 150;
        Double testCarbs = 16.5;
        Double testProtein = 5.75;
        Double testFat = 1.2;


        if (calories  > 600) {
            newCalorieTotal = calories - 500;
            minCalories = newCalorieTotal - 50;
        } else {
            newCalorieTotal = 100;
            minCalories = newCalorieTotal - 25;
        }

        assertEquals(testNewCalorieTotal, newCalorieTotal);
        assertEquals(testMinCalories, minCalories);


        maxCalories = newCalorieTotal + 50;
        carbs = (newCalorieTotal * 0.66) / 4;
        protein = (newCalorieTotal * 0.23) / 4;
        fats = (newCalorieTotal * 0.11) / 9;

        assertEquals(testMaxCalories, maxCalories);
        assertEquals(testCarbs, carbs);
        assertEquals(testProtein, protein);
        assertEquals(testFat, fats, 0.1);

    }
    /**
     * Tests correct values are calculated when the goal is 1 with maximum value
     */
    @Test
    public void buildUserProfileGoal1Max(){

        calories= 599;
        Integer testNewCalorieTotal = 100;
        Integer testMinCalories = 75;


        if (calories  > 600) {
            newCalorieTotal = calories - 500;
            minCalories = newCalorieTotal - 50;
        } else {
            newCalorieTotal = 100;
            minCalories = newCalorieTotal - 25;
        }

        assertEquals(testNewCalorieTotal, newCalorieTotal);
        assertEquals(testMinCalories, minCalories);

        maxCalories = newCalorieTotal + 50;
        carbs = (newCalorieTotal * 0.66) / 4;
        protein = (newCalorieTotal * 0.23) / 4;
        fats = (newCalorieTotal * 0.11) / 9;


    }

    /**
     * Tests correct values are calculated when the goal is 1 with value larger than 600
     */
    @Test
    public void buildUserProfileGoal1Larger(){

        calories= 601;
        Integer testNewCalorieTotal = 101;
        Integer testMinCalories = 51;
        Integer testMaxCalories = 151;
        Double testCarbs = 16.665;
        Double testProtein = 5.8075;
        Double testFat = 1.234;


        if (calories  > 600) {
            newCalorieTotal = calories - 500;
            minCalories = newCalorieTotal - 50;
        } else {
            newCalorieTotal = 100;
            minCalories = newCalorieTotal - 25;
        }

        assertEquals(testNewCalorieTotal, newCalorieTotal);
        assertEquals(testMinCalories, minCalories);

        maxCalories = newCalorieTotal + 50;
        carbs = (newCalorieTotal * 0.66) / 4;
        protein = (newCalorieTotal * 0.23) / 4;
        fats = (newCalorieTotal * 0.11) / 9;

        assertEquals(testMaxCalories, maxCalories);
        assertEquals(testCarbs, carbs);
        assertEquals(testProtein, protein);
        assertEquals(testFat, fats, 0.1);


    }

    /**
     * Tests correct values are calculated when the goal is 2
     */
    @Test
    public void buildUserProfileGoal2(){

        calories= 300;
        Integer testNewCalorieTotal = 300;
        Integer testMinCalories = 250;
        Integer testMaxCalories = 350;
        Double testCarbs = 49.5;
        Double testProtein = 17.25;
        Double testFat = 3.66666;


        newCalorieTotal = calories;
        minCalories = newCalorieTotal - 50;
        maxCalories = newCalorieTotal + 50;
        carbs = (newCalorieTotal * 0.66) / 4;
        protein = (newCalorieTotal * 0.23) / 4;
        fats = (newCalorieTotal * 0.11) / 9;

        assertEquals(testNewCalorieTotal, newCalorieTotal);
        assertEquals(testMinCalories, minCalories);
        assertEquals(testMaxCalories,maxCalories);

        assertEquals(testCarbs, carbs);
        assertEquals(testProtein, protein);
        assertEquals(testFat, fats, 0.1);

    }

    /**
     * Tests correct values are calculated when the goal is 3
     */
    @Test
    public void buildUserProfileGoal3(){

        calories= 300;
        Integer testNewCalorieTotal = 600;
        Integer testMinCalories = 550;
        Integer testMaxCalories = 650;
        Double testCarbs = 99.0;
        Double testProtein = 34.5;
        Double testFat = 7.3333;


        newCalorieTotal = calories + 300;
        minCalories = newCalorieTotal - 50;
        maxCalories = newCalorieTotal + 50;
        carbs = (newCalorieTotal * 0.66) / 4;
        protein = (newCalorieTotal * 0.23) / 4;
        fats = (newCalorieTotal * 0.11) / 9;

        assertEquals(testNewCalorieTotal, newCalorieTotal);
        assertEquals(testMinCalories, minCalories);
        assertEquals(testMaxCalories,maxCalories);

        assertEquals(testCarbs, carbs);
        assertEquals(testProtein, protein);
        assertEquals(testFat, fats, 0.1);

    }

    @Test
    public void testMinMaxProtein(){

        protein = 10.0;
        Integer testMinProtein = 10;
        Integer testMaxProtein = 11;

        minProtein = (int) Math.ceil(protein * 0.95);
        maxProtein = (int) Math.ceil(protein * 1.05);

        assertEquals(testMinProtein, minProtein);
        assertEquals(testMaxProtein, maxProtein);
    }

    @Test
    public void testMinMaxProteinEqual(){

        minProtein = 10;
        maxProtein=10;

        Integer testMinProtein = 9;
        Integer testMaxProtein = 11;

        if(minProtein==maxProtein){
            minProtein-=1;
            maxProtein+=1;
        }

        assertEquals(testMinProtein, minProtein);
        assertEquals(testMaxProtein, maxProtein);
    }

    @Test
    public void testMinMaxCarbs(){

        carbs = 99.0;
        Integer testMinCarbs = 95;
        Integer testMaxCarbs = 104;

        minCarbohydrates = (int) Math.ceil(carbs * 0.95);
        maxCarbohydrates = (int) Math.ceil(carbs * 1.05);

        assertEquals(testMinCarbs, minCarbohydrates);
        assertEquals(testMaxCarbs, maxCarbohydrates);
    }

    @Test
    public void testMinMaxCarbsEqual(){

        minCarbohydrates = 99;
        maxCarbohydrates=99;

        Integer testMinCarbs = 98;
        Integer testMaxCarbs = 100;

        if(minCarbohydrates==maxCarbohydrates){
            minCarbohydrates-=1;
            maxCarbohydrates+=1;
        }

        assertEquals(testMinCarbs, minCarbohydrates);
        assertEquals(testMaxCarbs, maxCarbohydrates);
    }

    @Test
    public void testMinMaxFatty(){

        fats = 7.333;
        Integer testMinFat = 7;
        Integer testMaxFat = 8;

        minFatty = (int) Math.ceil(fats * 0.95);
        maxFatty = (int) Math.ceil(fats * 1.05);

        assertEquals(testMinFat, minFatty);
        assertEquals(testMaxFat, maxFatty);
    }

    @Test
    public void testMinMaxFattyEqual(){

        minFatty = 8;
        maxFatty=8;

        Integer testMinFat = 7;
        Integer testMaxFat = 9;

        if(minFatty==maxFatty){
            minFatty-=1;
            maxFatty+=1;
        }

        assertEquals(testMinFat, minFatty);
        assertEquals(testMaxFat, maxFatty);
    }



}