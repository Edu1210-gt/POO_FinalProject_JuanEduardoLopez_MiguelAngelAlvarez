package domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Rental implements Serializable {
    // atributes
    private LocalDate rentalInitial;
    private int rentalDays;
    private Movie movie;
    private Customer customer;
    private String idRental;
    private double totalCost;

    // constructor
    public Rental(int rentalDays, Movie movie, Customer customer) {
        this.rentalInitial = LocalDate.now();
        this.rentalDays = rentalDays;
        this.movie = movie;
        this.customer = customer;
        this.idRental = "RM-" + (int) (Math.random() * Math.pow(10, 3));
        this.movie.setAvailability(false);
    }

    // methods
    public LocalDate getRentalInitial() {
        return rentalInitial;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public LocalDate getReturnDate() {
        LocalDate returnDate = rentalInitial.plusDays((long) rentalDays);
        return returnDate;
    }

    // Calculate rental cost
    public void calculateRentalCost() {
        this.totalCost = rentalDays * movie.getcostToday();
    }

    public Movie getMovie() {
        return movie;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getIdRental() {
        return idRental;
    }

    public Customer getCustomer() {
        return customer;
    }

}