package com.example.movie_database_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_database_app.R;
import com.example.movie_database_app.model.MovieBookmarkedEntity;
import com.example.movie_database_app.viewholder.BookMarkMovieViewHolder;

import java.util.List;

public class BookMarkedMoviesAdapter extends RecyclerView.Adapter<BookMarkMovieViewHolder> {

    private Context context;
    private List<MovieBookmarkedEntity> data;


    public BookMarkedMoviesAdapter(Context context) {
        this.context= context;
    }

    @NonNull
    @Override
    public BookMarkMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.movie_viewholder,parent,false);
        return new BookMarkMovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookMarkMovieViewHolder holder, int position) {
        holder.updateViewholderData(data.get(position));
    }

    @Override
    public int getItemCount() {
        if (data == null) return 0;
        return data.size();
    }

    public void updateData(List<MovieBookmarkedEntity> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
