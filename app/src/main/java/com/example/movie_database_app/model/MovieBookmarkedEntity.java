package com.example.movie_database_app.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


@Entity(tableName = "BookmarkedMoviesData",indices = {@Index(value={"id"},unique = true)})
public class MovieBookmarkedEntity implements Serializable {
    @PrimaryKey
    public int id;

    @SerializedName("poster_path")
    @ColumnInfo(name="poster_path")
    public String posterPath;

    @SerializedName("adult")
    @ColumnInfo(name="adult")
    public boolean isAdult;

    @SerializedName("overview")
    @ColumnInfo(name="overview")
    public boolean overview;

    @SerializedName("release_date")
    @ColumnInfo(name="release_date")
    public String releaseDate;

    @SerializedName("original_title")
    @ColumnInfo(name="original_title")
    public String originalTitle;

    @SerializedName("original_language")
    @ColumnInfo(name="original_language")
    public String originalLanguage;

    @SerializedName("title")
    @ColumnInfo(name="title")
    public String title;

    @SerializedName("backdrop_path")
    @ColumnInfo(name="backdrop_path")
    public String backdropPath;

    @SerializedName("popularity")
    @ColumnInfo(name="popularity")
    public double popularity;

    @SerializedName("vote_count")
    @ColumnInfo(name="vote_count")
    public String voteCount;

    @SerializedName("video")
    @ColumnInfo(name="video")
    public boolean video;

    @SerializedName("vote_average")
    @ColumnInfo(name="vote_average")
    public double voteAverage;

    @ColumnInfo(name="is_bookmarked")
    public boolean bookmarked;

}
