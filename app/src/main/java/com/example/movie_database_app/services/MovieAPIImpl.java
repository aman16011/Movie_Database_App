package com.example.movie_database_app.services;

import com.example.movie_database_app.model.APIResponse;
import com.example.movie_database_app.model.MovieDaoEntity;
import com.example.movie_database_app.model.NowPlayingMovieDaoEntity;
import com.example.movie_database_app.utils.Constants;
import com.example.movie_database_app.utils.Utils;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class MovieAPIImpl implements MovieAPI{
    @Override
    public Observable<APIResponse<NowPlayingMovieDaoEntity>> getNowPlayingMovies(String apiKey, String lang, Integer pageNumber) {
        Retrofit retrofit = Utils.getRestAdapter(Constants.BASE_URL);
        return retrofit.create(MovieAPI.class).getNowPlayingMovies(apiKey,lang,pageNumber);
    }

    @Override
    public Observable<APIResponse<MovieDaoEntity>> getTrendingMovies(String apiKey, String lang, Integer pageNumber) {
        Retrofit retrofit = Utils.getRestAdapter(Constants.BASE_URL);
        return retrofit.create(MovieAPI.class).getTrendingMovies(apiKey,lang,pageNumber);
    }
}
