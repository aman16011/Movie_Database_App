package com.example.movie_database_app.services;

import com.example.movie_database_app.model.MovieDetailResponse;
import com.example.movie_database_app.utils.Constants;
import com.example.movie_database_app.utils.Utils;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class MovieDetailAPIImpl implements MovieDetailAPI {
    @Override
    public Observable<MovieDetailResponse> getMovieDetail(String apiKey, String lang, String movieId) {
        Retrofit retrofit = Utils.getRestAdapter(Constants.BASE_URL);
        return retrofit.create(MovieDetailAPI.class).getMovieDetail(apiKey,lang,movieId);
    }
}
