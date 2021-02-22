package com.example.movie_database_app.repo;


import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.movie_database_app.database.MovieDatabase;
import com.example.movie_database_app.model.MovieBookmarkedEntity;
import com.example.movie_database_app.model.MovieDaoEntity;
import com.example.movie_database_app.model.NowPlayingMovieDaoEntity;
import com.example.movie_database_app.services.MovieAPIImpl;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class MovieDataRepo {

    private String section;
    private MovieAPIImpl movieAPIImpl = new MovieAPIImpl();

    public MovieDataRepo(String section) {
        this.section  = section;
    }

    public LiveData<List<NowPlayingMovieDaoEntity>> getNowPlayingMovie(Context context) {
        return getLocalNowPlayingMovie(context);
    }

    public LiveData<List<NowPlayingMovieDaoEntity>> getLocalNowPlayingMovie(final Context context) {
        return MovieDatabase.getInstance(context).movieDao().getNowPlayingMoviesData();
    }

    public void getServerNowPlayingMovie(Context context, String apiKey, String lang, Integer pageNumber) {
        movieAPIImpl.getNowPlayingMovies(apiKey,lang,pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(result -> {
                    Log.d("MovieDataRepo","Data fetched Successfully");
                    MovieDatabase.getInstance(context).movieDao().insertNowPlayingMoviesData(result.results);

                }, throwable -> {
                    Log.d("MovieDataRepo",throwable.getMessage());
                });
    }

    public LiveData<List<MovieDaoEntity>> getTrendingMovies(Context context) {
        return getLocalTrendingMovies(context);
    }

    public LiveData<List<MovieDaoEntity>> getLocalTrendingMovies(final Context context) {
        return MovieDatabase.getInstance(context).movieDao().getTrendingMoviesData();
    }

    public void getServerTrendingMovies(Context context, String apiKey, String lang, Integer pageNumber) {
        movieAPIImpl.getTrendingMovies(apiKey,lang,pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(result -> {
                    Log.d("MovieDataRepo","Data fetched Successfully");
                    MovieDatabase.getInstance(context).movieDao().insertTrendingMoviesData(result.results);

                }, throwable -> {
                    Log.d("MovieDataRepo",throwable.getMessage());
                });
    }

    public void bookMarkLocation(Context context, MovieBookmarkedEntity movieBookmarkedEntity) {
        Observable.fromCallable(() -> MovieDatabase.getInstance(context).movieDao())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(db -> db.insertBookMarkMoviesData(movieBookmarkedEntity),
                        throwable -> {Log.d(section,throwable.getMessage());});
    }

    public LiveData<List<MovieBookmarkedEntity>> getBookMarkedMovies(Context context) {
        return MovieDatabase.getInstance(context).movieDao().getBookmarkedMoviesData();
    }

    public LiveData<List<MovieBookmarkedEntity>> getBookMarkedMoviesById(Context context, int id) {
        return MovieDatabase.getInstance(context).movieDao().getBookmarkedMoviesData(id);
    }




}
