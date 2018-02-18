package com.mblonyox.iaktrivia;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mblonyox.iaktrivia.http.OpenTriviaAPI;
import com.mblonyox.iaktrivia.model.ResponseCategories;
import com.mblonyox.iaktrivia.model.ResponseToken;
import com.mblonyox.iaktrivia.model.TriviaCategoriesItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Sukirno on 17/02/2018.
 */

public class AppState extends Application {

    private SharedPreferences sharedPreferences;
    private String sessionToken;
    private List<TriviaCategoriesItem> triviaCategories;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = getSharedPreferences(
                getString(R.string.app_file),
                Context.MODE_PRIVATE
        );
        sessionToken = getSessionToken();
        triviaCategories = getTriviaCategories();
    }

    public String getSessionToken() {
        if(sessionToken == null) {
            String sharedToken = sharedPreferences.getString(getString(R.string.app_token), "");
            int now = (int) (new Date().getTime() / 1000);
            int lastAccess = sharedPreferences.getInt("last_access", now);
            if(sharedToken.equals("") || (now - 21600 > lastAccess)) {
                requestSessionToken();
            } else {
                sessionToken = sharedToken;
            }
        }
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
        int now = (int) (new Date().getTime() / 1000);
        sharedPreferences.edit()
                .putString(getString(R.string.app_token), sessionToken)
                .putInt("last_access",now)
                .commit();
    }

    public List<TriviaCategoriesItem> getTriviaCategories() {
        if(triviaCategories == null) {
            String sharedCategories = sharedPreferences.getString(getString(R.string.app_categories), "");
            if(sharedCategories == "") {
                requestListCategories();
            }else{
                triviaCategories = new Gson().fromJson(sharedCategories, new TypeToken<List<TriviaCategoriesItem>>() {}.getType());
            }
        }
        return triviaCategories;
    }

    public void setTriviaCategories(List<TriviaCategoriesItem> triviaCategories) {
        this.triviaCategories = triviaCategories;
        String serializedCategories = new Gson().toJson(triviaCategories, new TypeToken<List<TriviaCategoriesItem>>() {}.getType());
        sharedPreferences.edit()
                .putString(
                        getString(R.string.app_categories),
                        serializedCategories
                        )
                .commit();
        Log.d("sharedPref", serializedCategories);
    }


    private void requestSessionToken() {
        OpenTriviaAPI.getClient()
                .requestToken()
                .enqueue(
                        new Callback<ResponseToken>() {
                            @Override
                            public void onResponse(Call<ResponseToken> call, Response<ResponseToken> response) {
                                if (response.body().getResponseCode() == 0) {
                                    setSessionToken(response.body().getToken());
                                }
                                logToast(response.body().getResponseMessage());
                            }

                            @Override
                            public void onFailure(Call<ResponseToken> call, Throwable t) {
                                logToast(t.getMessage());
                            }
                        }
                );
    }

    private void requestListCategories() {
        OpenTriviaAPI.getClient()
                .listCategories()
                .enqueue(
                        new Callback<ResponseCategories>() {
                            @Override
                            public void onResponse(Call<ResponseCategories> call, Response<ResponseCategories> response) {
                                setTriviaCategories(response.body().getTriviaCategories());
                            }

                            @Override
                            public void onFailure(Call<ResponseCategories> call, Throwable t) {
                                logToast(t.getMessage());
                            }
                        }
                );
    }

    private void logToast(String message) {
        Toast.makeText(
                getApplicationContext(),
                message,
                Toast.LENGTH_SHORT
        );
    }
}
