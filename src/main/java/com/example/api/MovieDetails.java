package com.example.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieDetails {

    @SerializedName("Title")
    private String title;

    @SerializedName("Released")
    private String releaseDate;

    @SerializedName("Runtime")
    private String runtime;

    @SerializedName("Genre")
    private String genre;

    @SerializedName("Writer")
    private String writer;

    @SerializedName("Language")
    private String language;

    @SerializedName("Actors")
    private String actors;

    @SerializedName("Ratings")
    private ArrayList<Ratings> ratings;

    @SerializedName("Poster")
    private String poster;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public ArrayList<Ratings> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<Ratings> ratings) {
        this.ratings = ratings;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String[] getActorsArray()
    {
        return actors.split(", ");
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

}
