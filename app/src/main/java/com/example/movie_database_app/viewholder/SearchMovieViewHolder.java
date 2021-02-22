package com.example.movie_database_app.viewholder;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_database_app.R;
import com.example.movie_database_app.model.MovieDaoEntity;
import com.example.movie_database_app.utils.Utils;

public class SearchMovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView title;
    private MovieDaoEntity data;

    public SearchMovieViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.title = itemView.findViewById(R.id.search_title);

    }

    public void updateViewholderData(MovieDaoEntity data) {
        this.data = data;
        this.title.setText(data.title);
    }

    @Override
    public void onClick(View view) {
        Intent intent = Utils.getIntentForMovieDetailsActivity(String.valueOf(data.id));
        view.getContext().startActivity(intent);
    }
}
