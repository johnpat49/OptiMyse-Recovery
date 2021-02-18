package com.example.dissspoonacular.requests.fitbitrequests;

import com.example.dissspoonacular.util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Builds Retrofit calls to Fitbit Api
 */
public class FitbitServiceGenerator {

    private static Retrofit.Builder fitbitBuilder =
            new Retrofit.Builder()
            .baseUrl(Constants.AUTHORIZE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofitbit = fitbitBuilder.build();

    private static FitbitApi fitbitApi = retrofitbit.create(FitbitApi.class);

    public static FitbitApi getFitbitApi(){
        return fitbitApi;
    }

    public static void setApi(FitbitApi Api) {
        fitbitApi = Api;
    }


}
