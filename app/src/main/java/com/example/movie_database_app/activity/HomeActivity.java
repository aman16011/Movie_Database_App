package com.example.movie_database_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_database_app.AdapterInterface;
import com.example.movie_database_app.R;
import com.example.movie_database_app.adapter.LatestMoviesAdapter;
import com.example.movie_database_app.adapter.TrendingMoviesAdapter;
import com.example.movie_database_app.model.MovieDaoEntity;
import com.example.movie_database_app.model.NowPlayingMovieDaoEntity;
import com.example.movie_database_app.viewmodel.MovieViewModel;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, AdapterInterface {

    private RecyclerView latestMoviesRecycler, trendingMoviesRecycler;
    private LatestMoviesAdapter latestMoviesAdapter;
    private TrendingMoviesAdapter trendingMoviesAdapter;
    private MovieViewModel movieViewModel;
    private int previousLatestMoviesSize = 0;
    private int previousTrendingMoviesSize = 0;
    private EditText searchBar;
    private TextView bookMarkedMovies;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.home_activity);
        searchBar = findViewById(R.id.search_bar);
        bookMarkedMovies = findViewById(R.id.bookmarked_movies);
        searchBar.setOnClickListener(this);
        bookMarkedMovies.setOnClickListener(this);

        latestMoviesRecycler = findViewById(R.id.latest_movie_recycler);
        trendingMoviesRecycler = findViewById(R.id.trending_movie_recycler);

        latestMoviesRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        trendingMoviesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.funStarted(this);
        latestMoviesAdapter = new LatestMoviesAdapter(this,this);
        trendingMoviesAdapter = new TrendingMoviesAdapter(this,this);

        movieViewModel.getNowPlayingMovies().observe(this, new Observer<List<NowPlayingMovieDaoEntity>>() {
            @Override
            public void onChanged(List<NowPlayingMovieDaoEntity> nowPlayingMovieDaoEntityList) {
                if (nowPlayingMovieDaoEntityList.size() == previousLatestMoviesSize) {
                    movieViewModel.incrementNowPlayingMoviesPageCount();
                    movieViewModel.loadMoreNowPlayingMovies(HomeActivity.this);
                }
                previousLatestMoviesSize = nowPlayingMovieDaoEntityList.size();
                latestMoviesAdapter.updateData(nowPlayingMovieDaoEntityList);
            }
        });

        movieViewModel.getTrendingMovies().observe(this, new Observer<List<MovieDaoEntity>>() {
            @Override
            public void onChanged(List<MovieDaoEntity> movieDaoEntityList) {
                if (movieDaoEntityList.size() == previousTrendingMoviesSize) {
                    movieViewModel.incrementTrendingMoviesPageCount();
                    movieViewModel.loadMoreTrendingMovies(HomeActivity.this);
                }
                previousLatestMoviesSize = movieDaoEntityList.size();
               trendingMoviesAdapter.updateData(movieDaoEntityList);
            }
        });
        latestMoviesRecycler.setAdapter(latestMoviesAdapter);
        trendingMoviesRecycler.setAdapter(trendingMoviesAdapter);

    }

    @Override
    public void onClick(View view) {
        if (view == searchBar) {
            Intent intent = new Intent(this, MovieSearchActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this,BookMarkedMovieActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void fetchTrendingMovies() {
        movieViewModel.incrementTrendingMoviesPageCount();
        movieViewModel.loadMoreTrendingMovies(HomeActivity.this);
    }

    @Override
    public void fetchNowPlayingMovies() {
        movieViewModel.incrementNowPlayingMoviesPageCount();
        movieViewModel.loadMoreNowPlayingMovies(HomeActivity.this);

    }
}
