package com.example.movie_database_app.services;

import com.example.movie_database_app.model.APIResponse;
import com.example.movie_database_app.model.MovieDaoEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieSearchAPI {

    @GET("/3/search/movie")
    Observable<APIResponse<MovieDaoEntity>> getMovieSuggestions(@Query(value = "query",encoded = true) String query,
                                                               @Query("api_key") String apiKey,
                                                               @Query("language") String lang
    );
}