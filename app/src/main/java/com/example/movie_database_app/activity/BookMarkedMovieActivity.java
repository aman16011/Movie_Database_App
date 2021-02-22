package com.example.movie_database_app.activity;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_database_app.R;
import com.example.movie_database_app.adapter.BookMarkedMoviesAdapter;
import com.example.movie_database_app.model.MovieBookmarkedEntity;
import com.example.movie_database_app.viewmodel.MovieViewModel;

import java.util.List;

public class BookMarkedMovieActivity extends AppCompatActivity {

    private RecyclerView bookMarkedMovieRecycler;
    private BookMarkedMoviesAdapter bookMarkedMoviesAdapter;
    private MovieViewModel movieViewModel;
    private EditText searchBar;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.bookmark_movie_activity);

        bookMarkedMovieRecycler = findViewById(R.id.bookmark_movie_recycler);

        bookMarkedMovieRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.funStarted(this);
        bookMarkedMoviesAdapter = new BookMarkedMoviesAdapter(this);

        movieViewModel.getBookmarkMoviesLiveData().observe(this, new Observer<List<MovieBookmarkedEntity>>() {
            @Override
            public void onChanged(List<MovieBookmarkedEntity> movieBookmarkedEntities) {
                bookMarkedMoviesAdapter.updateData(movieBookmarkedEntities);
            }
        });
        bookMarkedMovieRecycler.setAdapter(bookMarkedMoviesAdapter);


    }
}
