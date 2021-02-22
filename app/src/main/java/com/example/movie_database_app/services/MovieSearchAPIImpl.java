package com.example.movie_database_app.services;

import com.example.movie_database_app.model.APIResponse;
import com.example.movie_database_app.model.MovieDaoEntity;
import com.example.movie_database_app.model.MovieDetailResponse;
import com.example.movie_database_app.utils.Constants;
import com.example.movie_database_app.utils.Utils;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class MovieSearchAPIImpl implements MovieSearchAPI {

    @Override
    public Observable<APIResponse<MovieDaoEntity>> getMovieSuggestions(String query, String apiKey, String lang) {
        Retrofit retrofit = Utils.getRestAdapter(Constants.BASE_URL);
        return retrofit.create(MovieSearchAPI.class).getMovieSuggestions(query,apiKey,lang);
    }
}
