package com.example.movie_database_app.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.movie_database_app.model.MovieBookmarkedEntity;
import com.example.movie_database_app.model.MovieDaoEntity;
import com.example.movie_database_app.model.NowPlayingMovieDaoEntity;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM trendingmoviedata")
    LiveData<List<MovieDaoEntity>> getTrendingMoviesData();

    @Query("SELECT * FROM nowplayingmoviedata")
    LiveData<List<NowPlayingMovieDaoEntity>> getNowPlayingMoviesData();

    @Query("SELECT * FROM bookmarkedmoviesdata WHERE is_bookmarked=1")
    LiveData<List<MovieBookmarkedEntity>> getBookmarkedMoviesData();

    @Query("SELECT * FROM bookmarkedmoviesdata WHERE is_bookmarked=1 AND id = :id")
    LiveData<List<MovieBookmarkedEntity>> getBookmarkedMoviesData(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertTrendingMoviesData(List<MovieDaoEntity> movieDaoEntityList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertNowPlayingMoviesData(List<NowPlayingMovieDaoEntity> nowPlayingMovieDaoEntityList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertBookMarkMoviesData(MovieBookmarkedEntity movieBookmarkedEntity);
}
