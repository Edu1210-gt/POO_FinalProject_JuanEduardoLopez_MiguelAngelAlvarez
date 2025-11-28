package src.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Represents the process of returning a rented movie within the system.
 * This class encapsulates the logic required to handle a movie return,
 * including the recording of the return date, calculation of days in arrears,
 * computation of late fees, and determination of the final total amount owed.
 *
 * As a domain-level entity, this class provides:
 * <ul>
 * <li>The date on which the movie was actually returned.</li>
 * <li>A reference to the original {@code Rental} transaction.</li>
 * <li>Computation of late days using the rental's expected return date.</li>
 * <li>Calculation of arrears costs based on a percentage of the movie's rental
 * price.</li>
 * <li>Calculation and assignment of the updated total rental cost including
 * penalties.</li>
 * </ul>
 *
 * The class implements {@link java.io.Serializable} to allow persistence and
 * storage
 * of return-related data.
 */
public class ReturnMovie implements Serializable {
    // atributes
    private LocalDate returnMovie;
    private Rental renta;

    // constructor
    /**
     * Creates a new movie return instance. The return date is automatically
     * assigned as the current system date, and the object is linked to the
     * corresponding {@code Rental} transaction.
     *
     * @param renta The rental transaction being returned.
     */
    public ReturnMovie(Rental renta) {
        this.returnMovie = LocalDate.now();
        this.renta = renta;
    }

    // methods
    /**
     * Returns the actual date on which the movie was returned.
     *
     * @return The return date.
     */
    public LocalDate getReturnMovie() {
        return returnMovie;
    }

    // Calculate days of arrears
    /**
     * Calculates the number of late days by comparing the expected return date
     * from the rental with the actual return date. A positive value indicates
     * that the movie was returned late.
     *
     * @return Number of days in arrears.
     */
    public long calculationsDaysofArrears() {
        return ChronoUnit.DAYS.between(this.renta.getReturnDate(), returnMovie);

    }

    // Calculate costs of arrears

    /**
     * Calculates the cost of arrears (late fees). If the number of late days is
     * greater than zero, a penalty equal to 30% of the movie's rental price per day
     * is applied.
     *
     * @return The total arrears cost, or {@code 0} if returned on time.
     */
    public double calculationsofCostsofArrears() {
        long daysOfArrears = calculationsDaysofArrears();
        double costOfArrears;
        if (daysOfArrears > 0) {
            costOfArrears = daysOfArrears * (this.renta.getMovie().getRentalPrice() * 0.3);
            return costOfArrears;
        } else {
            return 0;
        }

    }

    // Calculate total to pay
    /**
     * Calculates the final total amount to be paid, combining the rental's base
     * cost with any arrears penalties. The rental's total cost is updated
     * accordingly.
     *
     * @return The updated total amount to pay.
     */
    public double calculationsTotaltoPay() {
        double totalToPay = this.renta.getTotalCost() + calculationsofCostsofArrears();
        this.renta.setTotalCost(totalToPay);
        return totalToPay;
    }

}