package src.domain;

import java.io.Serializable;

public class Movie implements Serializable{
    private String movieId;
    private String title;
    private String genre;
    private double rentalPrice;
    private boolean available;

    public Movie(String movieId, String title, String genre, double rentalPrice) {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.rentalPrice = rentalPrice;
        this.available = true;
    }

    public String getMovieId() {
        return movieId;
    }
    
    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public double getRentalPrice() {
        return rentalPrice;
    }
    
    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }
    
    public boolean isAvailable() {
        return available;
    }
    
    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String toString() {
        return  "MovieId: " + movieId + " | " +
                "Title: " + title + "  |  " +
                "Genre: " + genre + " | " +
                "Rental Price: " + rentalPrice + " | " +
                "Available: " + available;
    }

}