package com.example.dissspoonacular.requests.fitbitrequests;

import com.example.dissspoonacular.models.fitbitmodels.AccessToken;
import com.example.dissspoonacular.models.fitbitmodels.Activities;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Retrofit calls to Fitbit Api
 */
public interface FitbitApi {

    //Do Want to encode Authorization in app

    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "Authorization: Basic MjJCTVhCOjg0M2M2MzQ4OWYzMGQ1NjVhOTU5NGY2YTY4YTBjM2Fj"
    })
    @POST("oauth2/token")
    @FormUrlEncoded
    Call<AccessToken> getAccessToken(
            @Field("code") String code,
            @Field("grant_type") String type,
            @Field ("redirect_uri") String uri,
            @Field("client_id") String client_id,
            @Field("expires_in") String expires

    );

    @GET("1/user/-/activities/list.json")
     Call<Activities> getCalories(
            @Query("beforeDate") String date,
            @Query("sort") String sortBy,
            @Query("offset") String offset,
            @Query("limit") String limit,
            @Header("Authorization") String code

    );



}
