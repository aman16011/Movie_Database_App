package com.example.movie_database_app.activity;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_database_app.R;
import com.example.movie_database_app.adapter.MoviesSearchAdapter;
import com.example.movie_database_app.model.MovieDaoEntity;
import com.example.movie_database_app.viewmodel.MovieSearchViewModel;

import java.util.List;


public class MovieSearchActivity extends AppCompatActivity {

    private String TAG="MovieDetailsActivity";
    private String movieId;
    private MovieSearchViewModel movieSearchViewModel;
    private EditText searchBox;
    private RecyclerView recyclerView;
    private MoviesSearchAdapter moviesSearchAdapter = new MoviesSearchAdapter(this);
    private Handler handler = new Handler();
    private long last_user_name_edit = 0;
    private String currentText = "";
    private int delay = 1000;
    private Runnable moveSearchRunnable = new Runnable() {
        @Override
        public void run() {
            String s = searchBox.getText().toString();
            currentText = s;
            if (s.length()!=0 && (System.currentTimeMillis() > last_user_name_edit + delay - 500)) {
                movieSearchViewModel.fetchSearchedMovies(s);
            }
        }
    };
    @Override
    public void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.movie_search_activity);
        searchBox = findViewById(R.id.search_bar);
        recyclerView = findViewById(R.id.search_movie_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(moviesSearchAdapter);
        movieSearchViewModel = new ViewModelProvider(this).get(MovieSearchViewModel.class);
        movieSearchViewModel.getLiveMovieDetailData().observe(this, new Observer<List<MovieDaoEntity>>() {
            @Override
            public void onChanged(List<MovieDaoEntity> movieDaoEntityList) {
                moviesSearchAdapter.updateData(movieDaoEntityList);
            }
        });
        initTextChangeListener();

    }

    private void initTextChangeListener() {
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                handler.removeCallbacks(moveSearchRunnable);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                last_user_name_edit = System.currentTimeMillis();
                if (!currentText.equals(editable.toString())) {
                    handler.postDelayed(moveSearchRunnable,delay);
                }

            }
        });
    }


}