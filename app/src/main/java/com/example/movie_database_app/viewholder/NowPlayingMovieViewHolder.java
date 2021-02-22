package com.example.movie_database_app.viewholder;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_database_app.R;
import com.example.movie_database_app.model.NowPlayingMovieDaoEntity;
import com.example.movie_database_app.utils.Utils;

public class NowPlayingMovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView title, popularity, voteCount, releaseDate, popularityContainer, releaseDateContainer;
    private TextView isadult;
    private NowPlayingMovieDaoEntity data;

    public NowPlayingMovieViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.title = itemView.findViewById(R.id.title);
        this.isadult = itemView.findViewById(R.id.adult);
        this.popularity = itemView.findViewById(R.id.popularity);
        this.voteCount = itemView.findViewById(R.id.vote_count);
        this.releaseDate = itemView.findViewById(R.id.release_date);
        this.popularityContainer = itemView.findViewById(R.id.popularity_container);
        this.releaseDateContainer = itemView.findViewById(R.id.release_date_container);

    }

    public void updateViewholderData(NowPlayingMovieDaoEntity data) {
        this.data = data;
        this.title.setText(data.title);
        if(data.isAdult) {
            this.isadult.setText("YES");
        } else {
            this.isadult.setText("NO");
        }
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
