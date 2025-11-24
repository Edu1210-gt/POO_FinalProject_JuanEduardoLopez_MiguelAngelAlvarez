package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReturnMovie implements Serializable {
    // atributes
    private LocalDate returnMovie;
    private Rental renta;

    // constructor
    public ReturnMovie(Rental renta) {
        this.returnMovie = LocalDate.now();
        this.renta = renta;
    }

    // methods
    public LocalDate getReturnMovie() {
        return returnMovie;
    }

    // Calculate days of arrears
    public long calculationsDaysofArrears() {
        return ChronoUnit.DAYS.between(this.renta.getReturnDate(), returnMovie);

    }

    // Calculate costs of arrears
    public double calculationsofCostsofArrears() {
        long daysOfArrears = calculationsDaysofArrears();
        double costOfArrears;
        if (daysOfArrears > 0) {
            costOfArrears = daysOfArrears * (this.renta.getMovie().getcostToday() * 0.3);
            return costOfArrears;
        } else {
            return 0;
        }

    }

    // Calculate total to pay
    public double calculationsTotaltoPay() {
        double totalToPay = this.renta.getTotalCost() + calculationsofCostsofArrears();
        this.renta.setTotalCost(totalToPay);
        return totalToPay;
    }

}