package com.example.dissspoonacular.requests.fitbitrequests;

import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;

import com.example.dissspoonacular.MainActivity;
import com.example.dissspoonacular.models.fitbitmodels.AccessToken;
import com.example.dissspoonacular.models.fitbitmodels.Activities;
import com.example.dissspoonacular.models.fitbitmodels.Activity;
import com.example.dissspoonacular.util.Constants;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Where calls to Fitbit APi are made.
 */
public class FitbitApiClient {

    private static FitbitApiClient instance;
    private FitbitApi fitbitApi;

    public static  String aCODE = "Access_Code";
    public static SharedPreferences sharedPreferences;
    String accessToken;
    String calorieTotal;


    /**
     * Singleton Pattern.
     * @return
     */
    public static FitbitApiClient getInstance() {
        if (instance == null) {
            instance = new FitbitApiClient();
        }
        return instance;
    }

    public FitbitApiClient() {
        fitbitApi = FitbitServiceGenerator.getFitbitApi();
        sharedPreferences = MainActivity.sharedPreferences;
        aCODE = MainActivity.aCODE;
    }

    /**
     * Build the URL for initial call to Fitbit Servers.
     * @return
     */
    public String  buildURL() {
        Uri builtUri = Uri.parse(Constants.fBASE_URL).buildUpon()
                .appendQueryParameter(Constants.RESPONSE, "code")
                .appendQueryParameter(Constants.ID, "22BMXB")
                .appendQueryParameter(Constants.SCOPE, "activity heartrate location nutrition profile weight")
                .appendQueryParameter(Constants.REDIRECT, "myapp://auth/fitbit")
                .build();
        URL requestURL = null;

        try {
            requestURL = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        String request = requestURL.toString();
        return request;

    }


    public String getAccessToken(String code) {
        Call<AccessToken> accessTokenCall = fitbitApi.getAccessToken(
                code, Constants.GRANT_TYPE, Constants.REDIRECT_URI, Constants.REDIRECT_URI, "3600" // will eventually go
        );
        accessTokenCall.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                if (response.code() == 200) {

                   if (!response.body().getAccessToken().equals(sharedPreferences.getString(aCODE, "key"))) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(aCODE, response.body().getAccessToken());
                        editor.apply();
                    }
                } else {

                }
                if (response.code() == 401) {
                    accessToken = "401";
                }
                accessToken = sharedPreferences.getString(aCODE, null);

            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                accessToken = t.getMessage();
            }
        });
        return accessToken;
    }

    public String getCalories(){

        Call<Activities> cals = fitbitApi.getCalories("2020-08-25", "desc", "0", "1", "Bearer "+sharedPreferences.getString(aCODE, null));

        cals.enqueue(new Callback<Activities>() {
            @Override
            public void onResponse(Call<Activities> call, Response<Activities> response) {
                if(response.body() != null){
                    List<Activity> temp = response.body().getActivities();
                    for(int loop=0; loop<temp.size(); loop++){
                        calorieTotal = temp.get(loop).getCalories().toString();
                    }
                }
            }
            @Override
            public void onFailure(Call<Activities> call, Throwable t) {
                calorieTotal=t.getMessage();
            }
        });

        return calorieTotal;
    }




}
