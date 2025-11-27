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
            Console.writeLine("================MOVIE==============");
            Console.writeLine("1. add Movie");
            Console.writeLine("2. lists of available movies");
            Console.writeLine("3. find Movie by Id");
            Console.writeLine("4. search Movies by Title");
            Console.writeLine("5. search Movies by Genre");
            Console.writeLine("6. update Movie");
            Console.writeLine("7. delete Movie");
            Console.writeLine("8. List of movies");
            Console.writeLine("================CUSTOMER=============");
            Console.writeLine("9. add Customer");
            Console.writeLine("10. find Customer by Id");
            Console.writeLine("11. find customer by Name");
            Console.writeLine("12. update Customer");
            Console.writeLine("13. List of customers");
            Console.writeLine("14. delete Customer");
            Console.writeLine("================RENTAL===============");
            Console.writeLine("15. Rent movie");
            Console.writeLine("16. Register Return");
            Console.writeLine("17. find rent by id");
            Console.writeLine("18. rent by customer");
            Console.writeLine("19. list of rentals");
            Console.writeLine("===========RENTAL REPORTS===========");
            Console.writeLine("20 least movie");
            Console.writeLine("21. most rented movie");
            Console.writeLine("22. Show movie with rent count");
            Console.writeLine("==============  EXIT AND SAVE=========");
            Console.writeLine("23. Exit");

            int choice = Console.readLineInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addMovie();
                    break;
                case 2:
                    ListOfAvailableMovies();
                    break;
                case 3:
                    findMovieById();
                    break;
                case 4:
                    searchMoviesByTitle();
                    break;
                case 5:
                    searchMoviesByGenre();
                    break;
                case 6:
                    updateMovie();
                    break;
                case 7:
                    deleteMovie();
                    break;
                case 8:
                    listOfMovies();
                    break;
                case 9:
                    addCustomer();
                    break;
                case 10:
                    findCustomerById();
                    break;
                case 11:
                    findCustomerByName();
                    break;
                case 12:
                    updateCustomer();
                    break;
                case 13:
                    listOfCustomers();
                    break;
                case 14:
                    deleteCustomer();
                    break;
                case 15:
                    RentalMovie();
                    break;
                case 16:
                    registerReturn();
                    break;
                case 17:
                    findRentalById();
                    break;
                case 18:
                    findRentalByCustomer();
                    break;
                case 19:
                    listOfRentals();
                    break;
                case 20:
                    leastMovie();
                    break;
                case 21:
                    mostRentedMovie();
                    break;
                case 22:
                    showMovieWithRentCount();
                    break;
                case 23:
                    Console.writeLine("Exiting the program. Goodbye!");
                    start = false;
                    break;
                default:
                    Console.writeLine("Invalid option. Try again.");
                    break;
            }
        }
    }

    // ================= MOVIE METHODS =================

    public void addMovie() {
        String tittle;
        String genere;
        double rentalPrice;
        Console.writeLine("--- Register Movie ---");
        tittle = Console.readLine("Enter movie title: ");
        genere = Console.readLine("Enter movie genre: ");
        rentalPrice = Double.parseDouble(Console.readLine("Enter rental price: "));

        Movie movie = new Movie("M-" + (int) (Math.random() * Math.pow(10, 3)), tittle, genere, rentalPrice);
        if (store.addMovie(movie)) {
            Console.writeLine("Movie registered successfully:");
            Console.writeLine(movie);
        } else {
            Console.writeLine("Movie registration failed. Movie may already exist.");
        }
    }

    public void ListOfAvailableMovies() {
        Console.writeLine("--- List of Available Movies ---");
        for (Movie movie : store.getAvailableMovies()) {
            Console.writeLine(movie.toString());
        }
    }

    public void findMovieById() {
        Console.writeLine("--- Find Movie by ID ---");
        String movieId = Console.readLine("Enter movie ID: ");
        Movie movie = store.findMovieById(movieId);

        if (movie != null) {
            Console.writeLine("Movie found:");
            Console.writeLine(movie);
        } else {
            Console.writeLine("Movie not found.");
        }
    }

    public void searchMoviesByTitle() {
        Console.writeLine("--- Search Movies by Title ---");
        String title = Console.readLine("Enter movie title: ");
        for (Movie movie : store.searchMoviesByTitle(title)) {
            Console.writeLine(movie.toString());
        }
    }

    public void searchMoviesByGenre() {
        Console.writeLine("--- Search Movies by Genre ---");
        String genre = Console.readLine("Enter movie genre: ");
        for (Movie movie : store.searchMoviesByGenre(genre)) {
            Console.writeLine(movie.toString());
        }
    }

    public void updateMovie() {
        Console.writeLine("--- Update Movie ---");
        String movieId = Console.readLine("Enter movie ID to update: ");
        Movie existingMovie = store.findMovieById(movieId);

        if (existingMovie != null) {
            String newTitle = Console.readLine("Enter new title: ");
            String newGenre = Console.readLine("Enter new genre: ");
            double newRentalPrice = Double.parseDouble(Console.readLine("Enter new rental price: "));
            Movie updatedMovie = new Movie(movieId, newTitle, newGenre, newRentalPrice);
            updatedMovie.setRentalPrice(newRentalPrice);
            updatedMovie.setGenre(newGenre);
            updatedMovie.setTitle(newTitle);

            if (store.updateMovie(movieId, updatedMovie)) {
                Console.writeLine("Movie updated successfully:");
                Console.writeLine(updatedMovie);
            } else {
                Console.writeLine("Movie update failed.");
            }
        } else {
            Console.writeLine("Movie not found.");
        }
    }

    public void deleteMovie() {
        Console.writeLine("--- Delete Movie ---");
        String movieId = Console.readLine("Enter movie ID to delete: ");
        if (store.deleteMovie(movieId)) {
            Console.writeLine("Movie deleted successfully.");
        } else {
            Console.writeLine("Movie deletion failed. Movie may not exist.");
        }
    }

    public void listOfMovies() {
        Console.writeLine("--- List of Movies ---");
        for (Movie movie : store.getAllMovies()) {
            Console.writeLine(movie.toString());
        }
    }

    // ================= CUSTOMER METHODS =================

    public void addCustomer() {
        Console.writeLine("--- Register Customer ---");
        String id = Console.readLine("Enter customer ID: ");
        String name = Console.readLine("Enter customer name: ");
        String email = Console.readLine("Enter customer email: ");
        String phoneNumber = Console.readLine("Enter customer phone number: ");

        Customer customer = new Customer(id, name, email, phoneNumber);

        if (store.addCustomer(customer)) {
            Console.writeLine("Customer registered successfully:");
            Console.writeLine(customer);
        } else {
            System.out.println("Customer registration failed. Customer may already exist.");
        }
    }

    public void findCustomerById() {
        Console.writeLine("--- Find Customer by ID ---");
        String customerId = Console.readLine("Enter customer ID: ");
        Customer customer = store.findCustomerById(customerId);

        if (customer != null) {
            Console.writeLine("Customer found:");
            Console.writeLine(customer);
        } else {
            Console.writeLine("Customer not found.");
        }
    }

    public void findCustomerByName() {
        Console.writeLine("--- Search Customers by Name ---");
        String name = Console.readLine("Enter customer name: ");
        for (Customer customer : store.searchCustomersByName(name)) {
            Console.writeLine(customer.toString());
        }
    }

    public void updateCustomer() {
        Console.writeLine("--- Update Customer ---");
        String customerId = Console.readLine("Enter customer ID to update: ");
        Customer existingCustomer = store.findCustomerById(customerId);

        if (existingCustomer != null) {
            String newName = Console.readLine("Enter new name: ");
            String newEmail = Console.readLine("Enter new email: ");
            String newPhoneNumber = Console.readLine("Enter new phone number: ");
            Customer updatedCustomer = new Customer(customerId, newName, newEmail, newPhoneNumber);

            if (store.updateCustomer(customerId, updatedCustomer)) {
                Console.writeLine("Customer updated successfully:");
                Console.writeLine(updatedCustomer);
            } else {
                Console.writeLine("Customer update failed.");
            }
        } else {
            Console.writeLine("Customer not found.");
        }
    }

    public void deleteCustomer() {
        Console.writeLine("--- Delete Customer ---");
        String customerId = Console.readLine("Enter customer ID: ");
        if (store.deleteCustomer(customerId)) {
            Console.writeLine("Customer deleted successfully.");
        } else {
            Console.writeLine("Customer deletion failed. Customer may not exist.");
        }
    }

    public void listOfCustomers() {
        Console.writeLine("====== List of Customers ======");
        for (Customer c : store.getAllCustomers()) {
            Console.writeLine(c.toString());
        }
    }

    // ================= RENTAL METHODS =================

    public void RentalMovie() {
        Console.writeLine("--- Register Rental ---");

        String customerid = Console.readLine("Enter customer ID: ");
        int rentalDays = Console.readLineInt("Enter number of rental days: ");
        Customer customer = store.findCustomerById(customerid);

        if (customer == null) {
            System.out.println("Customer not found. Rental registration failed.");
            return;
        }

        String movieId = Console.readLine("Enter movie ID: ");
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

    public void registerReturn() {
        Console.writeLine("--- Register Return ---");

        String rentalId = Console.readLine("Enter rental ID: ");
        Rental rental = store.findRentalById(rentalId);

        if (rental == null) {
            Console.writeLine("Rental not found. Return registration failed.");
            return;
        }

        ReturnMovie returnMovie = new ReturnMovie(rental);

        long dayArrears = returnMovie.calculationsDaysofArrears();
        double costArrears = returnMovie.calculationsofCostsofArrears();
        double totalCost = returnMovie.calculationsTotaltoPay();

        rental.getMovie().setAvailable(true);

        Console.writeLine("Return registered successfully:");
        Console.writeLine("Rental ID: " + rental.getIdRental());
        Console.writeLine("Days of Arrears: " + dayArrears);
        Console.writeLine("Cost of Arrears: " + costArrears);
        Console.writeLine("Total to Pay: " + totalCost);
    }

    public void findRentalById() {
        String rentalId = Console.readLine("Enter rental ID: ");
        Rental rental = store.findRentalById(rentalId);

        if (rental != null) {
            Console.writeLine("The rental was found:");
            Console.writeLine(rental.toString());
        } else {
            Console.writeLine("Rental not found");
        }
    }

    public void findRentalByCustomer() {
        String customerId = Console.readLine("Enter Customer ID: ");
        for (Rental rental : store.getRentalsByCustomer(customerId)) {
            Console.writeLine(rental.toString());
        }
    }

    public void listOfRentals() {
        Console.writeLine("====== List of Rentals ======");
        for (Rental r : store.getAllRentals()) {
            Console.writeLine(r.toString());
        }
    }

    public void leastMovie() {
        Movie least = store.leastMovie();
        if (least != null) {
            Console.writeLine("Least rented movie:");
            Console.writeLine(least.toString());
        } else {
            Console.writeLine("No rentals found.");
        }
    }

    public void mostRentedMovie() {
        Movie most = store.mostRenta();
        if (most != null) {
            Console.writeLine("Most rented movie:");
            Console.writeLine(most.toString());
        } else {
            Console.writeLine("No rentals found.");
        }
    }

    public void showMovieWithRentCount() {
        Console.writeLine("--- Movies with Rent Count ---");
        store.showMoviesWithRentCount();
    }

}
