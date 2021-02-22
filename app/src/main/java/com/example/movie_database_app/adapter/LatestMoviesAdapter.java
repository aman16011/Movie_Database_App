package com.example.movie_database_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_database_app.AdapterInterface;
import com.example.movie_database_app.R;
import com.example.movie_database_app.model.NowPlayingMovieDaoEntity;
import com.example.movie_database_app.viewholder.NowPlayingMovieViewHolder;

import java.util.List;

public class LatestMoviesAdapter extends RecyclerView.Adapter<NowPlayingMovieViewHolder> {

    private AdapterInterface adapterInterface;
    private Context context;
    private List<NowPlayingMovieDaoEntity> data;


    public LatestMoviesAdapter(Context context, AdapterInterface adapterInterface) {
        this.context= context;
        this.adapterInterface = adapterInterface;
    }

    @NonNull
    @Override
    public NowPlayingMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.movie_viewholder,parent,false);
        return new NowPlayingMovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NowPlayingMovieViewHolder holder, int position) {
        if (position >= getItemCount()-1) {
            adapterInterface.fetchNowPlayingMovies();
        }
        holder.updateViewholderData(data.get(position));
    }

    @Override
    public int getItemCount() {
        if (data == null) return 0;
        return data.size();
    }

    public void updateData(List<NowPlayingMovieDaoEntity> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
