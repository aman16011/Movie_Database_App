package com.example.movie_database_app.utils;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.RenderScript;

import com.example.movie_database_app.model.MovieDaoEntity;
import com.example.movie_database_app.model.NowPlayingMovieDaoEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utils {

    public static <T> T fromJson(String jsonString, Type typeOfT) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(jsonString,typeOfT);
    }

    public static Retrofit getRestAdapter(String baseUrl) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .addConverterFactory(GsonConverterFactory.create(new Gson())).build();
        return retrofit;
    }

    public static Intent getIntentForMovieDetailsActivity(String id) {
        Intent intent = new Intent(Constants.INTENT_LAUNCH_MOVIE_DETAILS_ACTIVITY);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.BUNDLE_MOVIE_ID,id);
        intent.putExtras(bundle);
        return intent;
    }

}
