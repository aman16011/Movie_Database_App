package com.example.movie_database_app.services;

import com.example.movie_database_app.model.MovieDetailResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDetailAPI {

    @GET("/3/movie/{movie_id}")
    Observable<MovieDetailResponse> getMovieDetail(@Path("movie_id") String movieId,
                                                        @Query("api_key") String apiKey,
                                                        @Query("language") String lang
                                                   );
}