package com.example.movie_database_app.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.movie_database_app.model.MovieBookmarkedEntity;
import com.example.movie_database_app.model.MovieDaoEntity;
import com.example.movie_database_app.model.NowPlayingMovieDaoEntity;
import com.example.movie_database_app.repo.MovieDataRepo;
import com.example.movie_database_app.utils.Constants;

import java.util.List;

public class MovieViewModel extends ViewModel {

    String TAG = "MovieViewModel";
    MovieDataRepo movieDataRepo = new MovieDataRepo(TAG);

    LiveData<List<MovieDaoEntity>> trendingMovies;
    LiveData<List<NowPlayingMovieDaoEntity>> nowPlyaingMovies;
    LiveData<List<MovieBookmarkedEntity>> bookmarkedMovies;
    LiveData<List<MovieBookmarkedEntity>> bookmarkedMoviesById;

    int trendingMoviesPageCount = 1;
    int nowPlayingMoviesPageCount = 1;

    public void funStarted(Context context) {
        trendingMovies = movieDataRepo.getTrendingMovies(context);
        nowPlyaingMovies = movieDataRepo.getNowPlayingMovie(context);
        bookmarkedMovies = movieDataRepo.getBookMarkedMovies(context);
    }

    public LiveData<List<MovieDaoEntity>> getTrendingMovies() {
        return trendingMovies;
    }

    public void loadMoreTrendingMovies(Context context) {
        movieDataRepo.getServerTrendingMovies(context, Constants.API_KEY,
                Constants.ENG_LANG,this.trendingMoviesPageCount);

    }

    public void incrementTrendingMoviesPageCount() {
        this.trendingMoviesPageCount += 1;
    }

    public LiveData<List<NowPlayingMovieDaoEntity>> getNowPlayingMovies() {
        return nowPlyaingMovies;
    }

    public void loadMoreNowPlayingMovies(Context context) {
        movieDataRepo.getServerNowPlayingMovie(context, Constants.API_KEY,
                Constants.ENG_LANG,this.nowPlayingMoviesPageCount);

    }

    public void incrementNowPlayingMoviesPageCount()
    {
        this.nowPlayingMoviesPageCount += 1;
    }

    public void insertBookmarkMovie(Context context,MovieBookmarkedEntity movieBookmarkedEntity) {
        movieDataRepo.bookMarkLocation(context,movieBookmarkedEntity);
    }

    public LiveData<List<MovieBookmarkedEntity>> getBookmarkMoviesLiveData() {
        return bookmarkedMovies;
    }

    public LiveData<List<MovieBookmarkedEntity>> getBookmarkedMoviesLiveDataById(Context context,int id) {
        return movieDataRepo.getBookMarkedMoviesById(context,id);
    }

}
