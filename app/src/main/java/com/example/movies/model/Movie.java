package com.example.movies.model;

public class Movie {
    private String title;
    private String year;
    private String posterUrl;

    public Movie(String title, String year, String posterUrl) {
        this.title = title;
        this.year = year;
        this.posterUrl = posterUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public Movie() {
    }
}
