package src.domain;

import java.io.Serializable;
/*
 Represents a movie in the Movie Rental System.
 Implements Serializable to allow saving and loading Movie objects from files.
 */
public class Movie implements Serializable {
    // Unique identifier for the movie
    private String movieId;
    // Title of the movie
    private String title;
     // Genre or category of the movie
    private String genre;
    // Price to rent the movie
    private double rentalPrice;
    // Indicates whether the movie is currently available for rental
    private boolean available;

    //constructs a new Movie with the provided attributes.
    //The movie is set as available by default.
    public Movie(String movieId, String title, String genre, double rentalPrice) {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.rentalPrice = rentalPrice;
        this.available = true;//Default value when movie is added.

    }

    //Return the movie's unique ID.

    public String getMovieId() {
        return movieId;
    }
    //Update the movie's unique ID.

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
    //Returns the movie title.

    public String getTitle() {
        return title;
    }
    //Update the movie title

    public void setTitle(String title) {
        this.title = title;
    }
    //Returns the movie genre.

    public String getGenre() {
        return genre;
    }
    //Update the movie genre.

    public void setGenre(String genre) {
        this.genre = genre;
    }
    //Returns the rental price of the movie.

    public double getRentalPrice() {
        return rentalPrice;
    }
    //Update the rental price of the movie

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }
    //Updates the movie's availability status.

    public boolean isAvailable() {
        return available;
    }
    //Update the movies's availability status.

    public void setAvailable(boolean available) {
        this.available = available;
    }
    //Returns a readable string representation of the Movie Object.

    public String toString() {
        return "MovieId: " + movieId + " | " +
                "Title: " + title + "  |  " +
                "Genre: " + genre + " | " +
                "Rental Price: " + rentalPrice + " | " +
                "Available: " + available;
    }

}