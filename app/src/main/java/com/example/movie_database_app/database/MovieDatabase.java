package com.example.movie_database_app.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.movie_database_app.dao.MovieDao;
import com.example.movie_database_app.model.MovieBookmarkedEntity;
import com.example.movie_database_app.model.MovieDaoEntity;
import com.example.movie_database_app.model.NowPlayingMovieDaoEntity;

@Database(entities = {MovieDaoEntity.class, NowPlayingMovieDaoEntity.class, MovieBookmarkedEntity.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
    private static MovieDatabase instance;
    public static MovieDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context,
                    MovieDatabase.class, "movie-database").build();
        }
        return instance;
    }
}
