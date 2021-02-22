package com.example.movie_database_app.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movie_database_app.model.MovieDetailResponse;
import com.example.movie_database_app.services.MovieDetailAPIImpl;
import com.example.movie_database_app.utils.Constants;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MovieDetailViewModel extends ViewModel {
    String TAG = "MovieDetailViewModel";
    MutableLiveData<MovieDetailResponse> trendingMovies = new MutableLiveData<>();
    MovieDetailAPIImpl movieDetailAPIImpl = new MovieDetailAPIImpl();
    public void fetchMovieDetail(String id){
        Disposable dispose = movieDetailAPIImpl.getMovieDetail(id,Constants.API_KEY,
                Constants.ENG_LANG)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(result -> {
            trendingMovies.postValue(result);
            Log.d(TAG,"Details Fetched Succesfully");
        },
        throwable -> {
            Log.d(TAG,throwable.getMessage());
        })
        ;
    }

    public LiveData<MovieDetailResponse> getLiveMovieDetailData() {
        return this.trendingMovies;
    }
}
