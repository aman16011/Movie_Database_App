package com.example.movie_database_app.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movie_database_app.model.MovieDaoEntity;
import com.example.movie_database_app.model.MovieDetailResponse;
import com.example.movie_database_app.services.MovieDetailAPIImpl;
import com.example.movie_database_app.services.MovieSearchAPIImpl;
import com.example.movie_database_app.utils.Constants;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieSearchViewModel extends ViewModel {
    String TAG = "MovieSearchViewModel";
    MutableLiveData<List<MovieDaoEntity>> searchMovies = new MutableLiveData<>();
    MovieSearchAPIImpl movieDetailAPIImpl = new MovieSearchAPIImpl();
    public void fetchSearchedMovies(String query){
        Disposable dispose = movieDetailAPIImpl.getMovieSuggestions(query, Constants.API_KEY,
                Constants.ENG_LANG)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            searchMovies.postValue(result.results);
                            Log.d(TAG,"Details Fetched Succesfully");
                        },
                        throwable -> {
                            Log.d(TAG,throwable.getMessage());
                        })
                ;
    }

    public LiveData<List<MovieDaoEntity>> getLiveMovieDetailData() {
        return this.searchMovies;
    }
}
