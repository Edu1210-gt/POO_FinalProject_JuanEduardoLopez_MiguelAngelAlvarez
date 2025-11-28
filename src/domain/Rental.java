package src.domain;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents a rental transaction within the system. This class models all the
 * necessary information required to manage the process of renting a movie,
 * including dates, the associated customer, total cost, and parameters used to
 * compute the return date and rental charges.
 *
 * This class functions as a domain entity (Domain Model) and encapsulates:
 * <ul>
 * <li>The date on which the rental begins.</li>
 * <li>The number of days the rental lasts.</li>
 * <li>The rented movie and its rental price.</li>
 * <li>The customer who performs the rental.</li>
 * <li>A unique identifier for the rental transaction.</li>
 * <li>Calculation of the total rental cost based on days and movie
 * pricing.</li>
 * </ul>
 *
 * It also implements {@link java.io.Serializable} to allow persistence through
 * serialization.
 */
public class Rental implements Serializable {
    // atributes
    private LocalDate rentalInitial;
    private int rentalDays;
    private Movie movie;
    private Customer customer;
    private String idRental;
    private double totalCost;

    /**
     * Creates a new rental instance, assigning the current date as the start date,
     * initializing the rental duration, and associating both the movie and the
     * customer.
     * A unique random identifier is automatically generated for the transaction.
     *
     * @param rentalDays Number of days for which the movie will be rented.
     * @param movie      The movie selected for rental.
     * @param customer   The customer performing the rental.
     */
    // constructor
    public Rental(int rentalDays, Movie movie, Customer customer) {
        this.rentalInitial = LocalDate.now();
        this.rentalDays = rentalDays;
        this.movie = movie;
        this.customer = customer;
        this.idRental = "RM-" + (int) (Math.random() * Math.pow(10, 3));

    }

    // methods
    /**
     * Retrieves the date on which the rental began.
     *
     * @return The starting date of the rental.
     */
    public LocalDate getRentalInitial() {
        return rentalInitial;
    }

    /**
     * Returns the number of days assigned to this rental.
     *
     * @return Rental duration in days.
     */
    public int getRentalDays() {
        return rentalDays;
    }

    /**
     * Updates the rental duration.
     *
     * @param rentalDays The new number of rental days.
     */
    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    /**
     * Calculates and returns the expected return date of the movie by adding the
     * rental duration to the initial rental date.
     *
     * @return The computed return date.
     */
    public LocalDate getReturnDate() {
        LocalDate returnDate = rentalInitial.plusDays((long) rentalDays);
        return returnDate;
    }

    // Calculate rental cost
    public void calculateRentalCost() {
        this.totalCost = rentalDays * movie.getRentalPrice();
    }

    /**
     * Retrieves the movie associated with this rental.
     *
     * @return The rented movie.
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * Manually assigns the total rental cost. Typically used when adjusting
     * charges due to late returns or other circumstances.
     *
     * @param totalCost The new total cost.
     */
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * Returns the total cost of the rental.
     *
     * @return The final rental cost.
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * Retrieves the unique identifier assigned to this rental transaction.
     *
     * @return Rental code.
     */
    public String getIdRental() {
        return idRental;
    }

    /**
     * Returns the customer who performed the rental.
     *
     * @return The associated customer.
     */
    public Customer getCustomer() {
        return customer;
    }

public String toString() {
    return  " ID Rental: | '" + idRental  +
            " Movie: | " + movie.getTitle() +
            " Customer: | " + customer.getName() +
            " RentalInitial: |" + rentalInitial +
            " RentalDays: | " + rentalDays +
            " TotalCost: | " + totalCost;
}

}
    


