package com.example.movie_database_app.services;

import com.example.movie_database_app.model.APIResponse;
import com.example.movie_database_app.model.MovieDaoEntity;
import com.example.movie_database_app.model.NowPlayingMovieDaoEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieAPI {

    @GET("/3/movie/now_playing")
    Observable<APIResponse<NowPlayingMovieDaoEntity>> getNowPlayingMovies(@Query("api_key") String apiKey,
                                                                          @Query("language") String lang,
                                                                          @Query("page") Integer pageNumber);


    @GET("/3/movie/popular")
    Observable<APIResponse<MovieDaoEntity>> getTrendingMovies(@Query("api_key") String apiKey,
                                                                        @Query("language") String lang,
                                                                        @Query("page") Integer pageNumber);
}
