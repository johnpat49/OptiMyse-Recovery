package com.example.dissspoonacular;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.dissspoonacular.models.fitbitmodels.AccessToken;
import com.example.dissspoonacular.models.fitbitmodels.Activities;
import com.example.dissspoonacular.models.fitbitmodels.Activity;

import com.example.dissspoonacular.requests.fitbitrequests.FitbitApi;
import com.example.dissspoonacular.requests.fitbitrequests.FitbitServiceGenerator;
import com.example.dissspoonacular.util.Constants;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



/**
 * Activity that acts as a return point for the Fitbit Servers to send access code and for the call to the Fitbit
 * Api to find the users last exercise statistics.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LOG";
    private String code;
    private String fitbitReturn;

    private static Integer calories =0;


    public static SharedPreferences sharedPreferences;
    public static final String aCODE = "Access_Code";
    public static final String SHARED_PREF = "com.example.android.DissSpoonacular";
//    private String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    LocalDate ld = LocalDate.now().plusDays(1);

    //creates reference to FitbitApi class where RetroFit calls are held
    private FitbitApi fitbitApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fitbitApi = FitbitServiceGenerator.getFitbitApi();




        // create shared preference file to store access code
        sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        onNewIntent(getIntent());

    }

    /**
     * Where the OAuth Access code is returned to and stored into a shared preference file.
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        fitbitReturn = intent.getDataString();
        code = fitbitReturn.substring(fitbitReturn.indexOf("=") + 1, fitbitReturn.indexOf("#"));
        // NEEDS CHANGED)
        Call<AccessToken> accessTokenCall = fitbitApi.getAccessToken( code, Constants.GRANT_TYPE, Constants.REDIRECT_URI, Constants.REDIRECT_URI, "3600" );
        accessTokenCall.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                if(response.code()==200){
                    if(!response.body().getAccessToken().equals(sharedPreferences.getString(aCODE, "key"))) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(aCODE, response.body().getAccessToken());
                        editor.apply();
                    }
                } else{
                    Toast.makeText(getApplicationContext(), "Oops Something went wrong there. Please Try Again", Toast.LENGTH_LONG).show();
                }


                getCalories();
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                // No Internet Connection
                if(t instanceof IOException) {
                    Toast.makeText(getApplicationContext(), "No Network Connection. Please Check your Connection", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    /**
     * A call is made to the Fitbit Api for the number of calories expended by the user on their most recent exercise.
     */
    private void getCalories(){
        Log.d(TAG, "getCalories: "+ld);
        Call<Activities> cals = fitbitApi.getCalories(ld.toString(), "desc", "0", "1", "Bearer "+sharedPreferences.getString(aCODE, null));
       final Intent intent = new Intent(this, RecipeListActivity.class);
        cals.enqueue(new Callback<Activities>() {
            @Override
            public void onResponse(Call<Activities> call, Response<Activities> response) {
                if(response.body() != null){
                    List<Activity> temp = response.body().getActivities();
                    for(int loop=0; loop<temp.size(); loop++){
                        calories = temp.get(loop).getCalories();
                        intent.putExtra("calories", calories);
                        startActivity(intent);
                        //removes activity from backstack
                        finish();
                    }
                } else{
                    Toast.makeText(getApplicationContext(), "No Recent Fitbit Activities Found. Try Again Later", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), LandingActivity.class);
                    startActivity(intent);
                }
                // 403 error did not give permissions
                if(response.code() ==403){
                    Toast.makeText(getApplicationContext(), "Necessary permissions were rejected. Try the request again.", Toast.LENGTH_LONG).show();

                }
            }
            @Override
            public void onFailure(Call<Activities> call, Throwable t) {
                // No Internet Connection
                if(t instanceof IOException) {
                    Toast.makeText(getApplicationContext(), "No Network Connection. Please Check your Connection", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

}