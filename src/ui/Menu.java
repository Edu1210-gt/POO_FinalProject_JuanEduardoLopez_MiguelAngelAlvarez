
package src.ui;

import src.domain.Store;
import src.domain.Movie;
import src.domain.Customer;
import src.domain.Rental;
import src.domain.ReturnMovie;

public class Menu {
    private Store store;

    public Menu(Store store) {
        this.store = store;

    }

    public void startMenu() {
        boolean start = true;
        while (start) {
            Console.writeLine("\n--- Main Menu ---");
            Console.writeLine("1. Register Movie");
            Console.writeLine("2. Register Customer");
            Console.writeLine("3. Register Rental");
            Console.writeLine("4. Exit");
            Console.writeLine("5. Register Return");
            Console.writeLine("6. view lists of available movies");

            int choice = Console.readLineInt("Enter your choice: ");

            ; // Consume newline
            switch (choice) {
                case 1:
                    registerMovie();
                    break;
                case 2:
                    registerCustomer();
                    break;
                case 3:
                    registerRental();
                    break;
                case 4:
                    start = false;
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                case 5:
                    registerReturn();

                case 6:
                    viewListOfAvailableMovies();
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void registerMovie() {
        String tittle;
        String genere;
        Console.writeLine("--- Register Movie ---");
        tittle = Console.readLine("Enter movie title: ");
        genere = Console.readLine("Enter movie genre: ");

        Movie movie = new Movie("M-" + (int) (Math.random() * Math.pow(10, 3)), tittle, genere);
        if (store.addMovie(movie)) {
            Console.writeLine("Movie registered successfully:");
            Console.writeLine(movie);
        } else {
            Console.writeLine("Movie registration failed. Movie may already exist.");
        }

    }

    public void registerCustomer() {
        String phoneNumber;
        String id;
        String name;
        String email;
        Console.writeLine("--- Register Customer ---");
        id = Console.readLine("Enter customer ID: ");
        name = Console.readLine("Enter customer name: ");
        email = Console.readLine("Enter customer email: ");
        phoneNumber = Console.readLine("Enter customer phone number: ");
        Customer customer = new Customer("C-" + id, name, email, phoneNumber);
        if (store.addCustomer(customer)) {
            Console.writeLine("Customer registered successfully:");
            Console.writeLine(customer);
        } else {
            System.out.println("Customer registration failed. Customer may already exist.");
        }
    }

    public void registerRental() {
        int rentalDays;
        String customerid;
        String movieId;
        Console.writeLine("--- Register Rental ---");
        customerid = Console.readLine("Enter customer ID: ");
        rentalDays = Console.readLineInt("Enter number of rental days: ");
        Customer customer = store.findCustomerById(customerid);
        if (customer == null) {
            System.out.println("Customer not found. Rental registration failed.");

        } else {
            movieId = Console.readLine("Enter movie ID: ");
            Movie movie = store.findMovieById(movieId);
            if (movie == null) {
                Console.writeLine("Movie not found. Rental registration failed.");
            } else if (!movie.isAvailable()) {
                Console.writeLine("Movie is not available for rental.");
            } else {
                Rental rental = new Rental(rentalDays, movie, customer);
                rental.calculateRentalCost();
                if (store.rentMovie(rental)) {
                    Console.writeLine("Rental registered successfully:");
                    Console.writeLine("Rental ID: " + rental.getIdRental());
                    Console.writeLine("Customer: " + customer.getName());
                    Console.writeLine("Movie: " + movie.getTitle());
                    Console.writeLine("Rental Days: " + rental.getRentalDays());
                    Console.writeLine("Total Cost: " + rental.getTotalCost());
                    Console.writeLine("Return Date: " + rental.getReturnDate());
                } else {
                    Console.writeLine("Rental registration failed.");
                }
            }
        }

    }

    public void registerReturn() {
        long dayArrears;
        double costArrears;
        double totalCost;
        String rentalId;
        Console.writeLine("--- Register Return ---");
        rentalId = Console.readLine("Enter rental ID: ");
        Rental rental = store.findRentalById(rentalId);
        if (rental == null) {
            Console.writeLine("Rental not found. Return registration failed.");
        } else {
            ReturnMovie returnMovie = new ReturnMovie(rental);
            dayArrears = returnMovie.calculationsDaysofArrears();
            costArrears = returnMovie.calculationsofCostsofArrears();
            totalCost = returnMovie.calculationsTotaltoPay();
            rental.getMovie().setAvailable(true);
            Console.writeLine("Return registered successfully:");
            Console.writeLine("Rental ID: " + rental.getIdRental());
            Console.writeLine("Days of Arrears: " + dayArrears);
            Console.writeLine("Cost of Arrears: " + costArrears);
            Console.writeLine("Total to Pay: " + totalCost);
        }

    }

    public void viewListOfAvailableMovies() {
        Console.writeLine("--- List of Available Movies ---");
        for (Movie movie : store.getAvailableMovies()) {
            Console.writeLine(movie.toString());
        }
    }
}