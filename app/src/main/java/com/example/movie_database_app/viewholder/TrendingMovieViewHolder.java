package com.example.movie_database_app.viewholder;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_database_app.R;
import com.example.movie_database_app.model.MovieDaoEntity;
import com.example.movie_database_app.utils.Utils;

public class TrendingMovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView title, popularity, voteCount, releaseDate;
    private MovieDaoEntity data;
    private TextView isadult;

    public TrendingMovieViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.title = itemView.findViewById(R.id.title);
        this.isadult = itemView.findViewById(R.id.adult);
        this.popularity = itemView.findViewById(R.id.popularity);
        this.voteCount = itemView.findViewById(R.id.vote_count);
        this.releaseDate = itemView.findViewById(R.id.release_date);

    }

    public void updateViewholderData(MovieDaoEntity data) {
        this.data = data;
        this.title.setText(data.title);
        if(data.isAdult) {
            this.isadult.setText("YES");
        } else {
            this.isadult.setText("NO");
        }
        this.isadult.setSelected(data.isAdult);
        this.popularity.setText( String.valueOf(data.popularity));
        this.releaseDate.setText(data.releaseDate);
        this.voteCount.setText(data.voteCount);
    }

    @Override
    public void onClick(View view) {
        Intent intent = Utils.getIntentForMovieDetailsActivity(String.valueOf(data.id));
        view.getContext().startActivity(intent);
    }
}
