package com.example.movie_database_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_database_app.R;
import com.example.movie_database_app.model.MovieDaoEntity;
import com.example.movie_database_app.viewholder.SearchMovieViewHolder;

import java.util.List;

public class MoviesSearchAdapter extends RecyclerView.Adapter<SearchMovieViewHolder> {

    private Context context;
    private List<MovieDaoEntity> data;


    public MoviesSearchAdapter(Context context) {
        this.context= context;
    }

    @NonNull
    @Override
    public SearchMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.search_movie_viewholder,parent,false);
        return new SearchMovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchMovieViewHolder holder, int position) {
        holder.updateViewholderData(data.get(position));
    }

    @Override
    public int getItemCount() {
        if (data == null) return 0;
        return data.size();
    }

    public void updateData(List<MovieDaoEntity> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}