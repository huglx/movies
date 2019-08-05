package com.example.movies.model;

public class Movie {
    private String title;
    private String year;
    private String posterUrl;
    private String imdbID;

    public Movie(String title, String year, String posterUrl, String imdbID) {
        this.title = title;
        this.year = year;
        this.posterUrl = posterUrl;
        this.imdbID = imdbID;
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

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public static class Additional{
        private String Genre;
        private String Director;
        private String Plot;
        private String Awards;

        public Additional(String genre, String director, String plot, String awards) {
            Genre = genre;
            Director = director;
            Plot = plot;
            Awards = awards;
        }

        public String getGenre() {
            return Genre;
        }

        public void setGenre(String genre) {
            Genre = genre;
        }

        public String getDirector() {
            return Director;
        }

        public void setDirector(String director) {
            Director = director;
        }

        public String getPlot() {
            return Plot;
        }

        public void setPlot(String plot) {
            Plot = plot;
        }

        public String getAwards() {
            return Awards;
        }

        public void setAwards(String awards) {
            Awards = awards;
        }

        public Additional() {
        }
    }
}
