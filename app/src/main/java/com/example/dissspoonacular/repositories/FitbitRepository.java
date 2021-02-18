package com.example.dissspoonacular.repositories;

import com.example.dissspoonacular.requests.fitbitrequests.FitbitApiClient;
/**
 * Repository Responsible for dealing with actions related to Fitbit Api Calls.
 */
public class FitbitRepository {

    private static FitbitRepository instance;
    private FitbitApiClient mFitbitApiClient;

   //creates a singleton pattern
    public static FitbitRepository getInstance(){
        if(instance==null){
            instance = new FitbitRepository();
        }
        return instance;
    }

    private FitbitRepository(){
        mFitbitApiClient = FitbitApiClient.getInstance();
    }


    public String getUrl(){
        return mFitbitApiClient.buildURL();
    }
}
