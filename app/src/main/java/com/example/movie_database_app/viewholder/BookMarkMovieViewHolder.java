package com.example.movie_database_app.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_database_app.R;
import com.example.movie_database_app.model.MovieBookmarkedEntity;

public class BookMarkMovieViewHolder extends RecyclerView.ViewHolder{

    private TextView title, popularity, voteCount, releaseDate;
    private MovieBookmarkedEntity data;
    private TextView isadult;

    public BookMarkMovieViewHolder(View itemView) {
        super(itemView);
        this.title = itemView.findViewById(R.id.title);
        this.isadult = itemView.findViewById(R.id.adult);
        this.popularity = itemView.findViewById(R.id.popularity);
        this.voteCount = itemView.findViewById(R.id.vote_count);
        this.releaseDate = itemView.findViewById(R.id.release_date);

    }

    public void updateViewholderData(MovieBookmarkedEntity data) {
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
}
