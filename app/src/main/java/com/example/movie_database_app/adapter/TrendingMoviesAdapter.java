package com.example.movie_database_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_database_app.AdapterInterface;
import com.example.movie_database_app.R;
import com.example.movie_database_app.model.MovieDaoEntity;
import com.example.movie_database_app.viewholder.TrendingMovieViewHolder;

import java.util.List;

public class TrendingMoviesAdapter extends RecyclerView.Adapter<TrendingMovieViewHolder> {

    private AdapterInterface adapterInterface;
    private Context context;
    private List<MovieDaoEntity> data;

    public TrendingMoviesAdapter(Context context, AdapterInterface adapterInterface) {
        this.context = context;
        this.adapterInterface = adapterInterface;
    }

    @NonNull
    @Override
    public TrendingMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.movie_viewholder,parent,false);
        return new TrendingMovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingMovieViewHolder holder, int position) {
        if (position >= getItemCount()-1) {
            adapterInterface.fetchTrendingMovies();
        }
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
