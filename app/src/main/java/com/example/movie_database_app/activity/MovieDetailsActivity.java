package com.example.movie_database_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.movie_database_app.R;
import com.example.movie_database_app.model.MovieBookmarkedEntity;
import com.example.movie_database_app.model.MovieDetailResponse;
import com.example.movie_database_app.utils.Constants;
import com.example.movie_database_app.viewmodel.MovieDetailViewModel;
import com.example.movie_database_app.viewmodel.MovieViewModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG="MovieDetailsActivity";
    private String movieId;
    private ConstraintLayout movieDetailContainer;
    private ContentLoadingProgressBar loader;
    private MovieDetailViewModel movieDetailViewModel;
    private MovieDetailResponse movieDetail;
    private MovieViewModel movieViewModel;
    private TextView originalTitle, budget, releaseDate, tagLine, status, vote, revenue, home_button, bookmark_button, title;
    private TextView adult;
    @Override
    public void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.movie_detail_activity);
        extractArguments(getIntent().getExtras());

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.getBookmarkedMoviesLiveDataById(this,Integer.parseInt(movieId)).observe(this,
                new Observer<List<MovieBookmarkedEntity>>() {
                    @Override
                    public void onChanged(List<MovieBookmarkedEntity> movieBookmarkedEntities) {
                        if (movieBookmarkedEntities.size() == 0) {
                            bookmark_button.setText(Constants.BOOKMARK);
                        } else {
                            bookmark_button.setText(Constants.UNBOOKMARK);
                        }
                    }
                });
        movieDetailViewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);
        movieDetailViewModel.getLiveMovieDetailData().observe(this, new Observer<MovieDetailResponse>() {
            @Override
            public void onChanged(MovieDetailResponse movieDetailResponse) {
                movieDetail = movieDetailResponse;
                updateUI(movieDetailResponse);
            }
        }
        );
        fetchDetails();
    }

    private void extractArguments(Bundle bundle) {
        movieId = (String) bundle.getSerializable(Constants.BUNDLE_MOVIE_ID);
        adult =  findViewById(R.id.adult);
        movieDetailContainer = findViewById(R.id.detail_container);
        loader = findViewById(R.id.loader);
        originalTitle = findViewById(R.id.original_title);
        budget = findViewById(R.id.budget);
        releaseDate = findViewById(R.id.release_date);
        tagLine = findViewById(R.id.tagline);
        status = findViewById(R.id.status);
        vote = findViewById(R.id.vote);
        revenue = findViewById(R.id.revenue);
        home_button = findViewById(R.id.home_button);
        bookmark_button = findViewById(R.id.bookmark_button);
        home_button.setOnClickListener(this);
        title = findViewById(R.id.title);
        bookmark_button.setOnClickListener(this);
        loader.setVisibility(View.VISIBLE);
        movieDetailContainer.setVisibility(View.GONE);
    }

    private void fetchDetails() {
        movieDetailViewModel.fetchMovieDetail(movieId);
    }

    private void updateUI(MovieDetailResponse movieDetailResponse) {
        loader.setVisibility(View.GONE);
        movieDetailContainer.setVisibility(View.VISIBLE);
        title.setText(movieDetailResponse.title);
        if(movieDetailResponse.adult) {
            this.adult.setText(Constants.YES);
        } else {
            this.adult.setText(Constants.NO);
        }
        originalTitle.setText(movieDetailResponse.original_title);
        budget.setText(String.valueOf(movieDetailResponse.budget));
        releaseDate.setText(movieDetailResponse.release_date);
        tagLine.setText(movieDetailResponse.tagline);
        status.setText(movieDetailResponse.status);
        vote.setText(String.valueOf(movieDetailResponse.vote_count));
        revenue.setText(String.valueOf(movieDetailResponse.revenue));
    }

    @Override
    public void onClick(View view) {
        if (view == home_button) {
            Intent intent = new Intent(this,HomeActivity.class);
            startActivity(intent);
        } else {
            Gson gson = new Gson();
            String jsonString = "";
            jsonString = gson.toJson(movieDetail);
            gson = new GsonBuilder().create();
            MovieBookmarkedEntity movieBookmarkedEntity = gson.fromJson(jsonString, MovieBookmarkedEntity.class);
            if (bookmark_button.getText().equals(Constants.BOOKMARK)) {
                movieBookmarkedEntity.bookmarked = true;
                Toast toast = Toast.makeText(this, "The movie is bookmarked", Toast.LENGTH_SHORT);
                toast.show();
                bookmark_button.setText(Constants.UNBOOKMARK);
            } else {
                movieBookmarkedEntity.bookmarked = false;
                Toast toast = Toast.makeText(this, "The movie is Unbookmarked", Toast.LENGTH_SHORT);
                toast.show();
                bookmark_button.setText(Constants.BOOKMARK);
            }
            movieViewModel.insertBookmarkMovie(this, movieBookmarkedEntity);
            Log.d(TAG, "The movie bookmarked state changed");
        }

    }
}
