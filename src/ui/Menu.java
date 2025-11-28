package src.ui;

import src.domain.Store;
import src.domain.Movie;
import src.domain.Customer;
import src.domain.Rental;
import src.domain.ReturnMovie;

/**
 * User interface controller that exposes a console-based main menu for the
 * video-rental application. This class is responsible for presenting a
 * text-based menu (table-view layout), reading user choices, delegating
 * actions to the underlying {@link Store} domain service, and printing
 * results and receipts to the console.
 *
 * <p>
 * Responsibilities include:
 * </p>
 * <ul>
 * <li>Rendering the main menu and sub-options for Movies, Customers,
 * Rentals, Returns and Reports.</li>
 * <li>Validating user input where appropriate (e.g., numeric values).</li>
 * <li>Building and forwarding domain objects (Movie, Customer, Rental)
 * to the {@code Store} for persistence or processing.</li>
 * <li>Printing receipts and summaries for successful rental/return
 * operations.</li>
 * </ul>
 *
 * <p>
 * This class acts as a thin presentation layer and intentionally keeps
 * no persistent state beyond a reference to the {@link Store} instance.
 * </p>
 */

public class Menu {
    /**
     * Domain service / repository that stores and manages movies, customers and
     * rentals. All business operations are delegated to this object.
     */
    private Store store;

    /**
     * Constructs a menu controller bound to the given store instance.
     *
     * @param store the domain store used to perform operations invoked from the
     *              menu
     */
    public Menu(Store store) {
        this.store = store;
    }

    /**
     * Starts the interactive console menu loop. This method prints the main
     * table-style menu, reads the user's choice, and dispatches to the
     * corresponding handler method. The loop continues until the user
     * selects the exit option.
     *
     * Note: input validation is performed at the method-level for specific
     * operations (e.g., numeric parsing for prices and rental days).
     */
    public void startMenu() {
        boolean start = true;
        while (start) {
            Console.writeLine("\n=============== MAIN MENU (TABLE VIEW) ===============");

            Console.writeLine("  MOVIE                         |   CUSTOMER");
            Console.writeLine("-------------------------------+-------------------------------");
            Console.writeLine(" 1. add Movie                  |  9. add Customer");
            Console.writeLine(" 2. lists of available movies  | 10. find Customer by Id");
            Console.writeLine(" 3. find Movie by Id           | 11. find customer by Name");
            Console.writeLine(" 4. search Movies by Title     | 12. update Customer");
            Console.writeLine(" 5. search Movies by Genre     | 13. List of customers");
            Console.writeLine(" 6. update Movie               | 14. delete Customer");
            Console.writeLine(" 7. delete Movie               |");
            Console.writeLine(" 8. List of movies             |\n");

            Console.writeLine("  RENTAL                       |   RETURN / RENT INFO");
            Console.writeLine("-------------------------------+-------------------------------");
            Console.writeLine("15. Rent movie                 | 16. Register Return");
            Console.writeLine("17. find rent by id            | 18. rent by customer");
            Console.writeLine("19. list of rentals            |\n");

            Console.writeLine("  REPORTS                      |   EXIT");
            Console.writeLine("-------------------------------+-------------------------------");
            Console.writeLine("20. least movie                | 23. Exit");
            Console.writeLine("21. most rented movie          |");
            Console.writeLine("22. Show movie with rent count |");

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

    /**
     * Interactive flow that registers a new movie. It requests title, genre
     * and rental price from the user, validates the price input, constructs
     * a {@link Movie} instance and delegates persistence to {@link Store#addMovie}.
     * The method prints a success or failure message depending on the
     * result returned by the store.
     */
    public void addMovie() {
        Console.writeLine("--- Register Movie ---");
        String tittle = Console.readLine("Enter movie title: ");
        String genere = Console.readLine("Enter movie genre: ");

        double rentalPrice = 0;

        while (true) {
            try {
                rentalPrice = Console.readLineDouble("Enter rental price: ");
                break;
            } catch (NumberFormatException e) {
                Console.writeLine("Invalid price. Enter a numeric value.");
            }
        }

        Movie movie = new Movie("M-" + (int) (Math.random() * Math.pow(10, 3)), tittle, genere, rentalPrice);

        if (store.addMovie(movie)) {
            Console.writeLine("Movie registered successfully:");
            Console.writeLine(movie);
        } else {
            Console.writeLine("Movie registration failed. Movie may already exist.");
        }
    }

    /**
     * Prints the list of currently available movies retrieved from the store.
     * Each movie is printed using its {@code toString} representation.
     */
    public void ListOfAvailableMovies() {
        Console.writeLine("--- List of Available Movies ---");
        for (Movie movie : store.getAvailableMovies()) {
            Console.writeLine(movie.toString());
        }
    }

    /**
     * Prompts for a movie id, queries the store and prints the movie details if
     * found. Otherwise prints a not-found message.
     */
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

    /**
     * Prompts for a title (or partial title) and prints matching movies returned
     * by the store search method.
     */
    public void searchMoviesByTitle() {
        Console.writeLine("--- Search Movies by Title ---");
        String title = Console.readLine("Enter movie title: ");
        for (Movie movie : store.searchMoviesByTitle(title)) {
            Console.writeLine(movie.toString());
        }
    }

    /**
     * Prompts for a genre and prints matched movies returned by the store.
     */
    public void searchMoviesByGenre() {
        Console.writeLine("--- Search Movies by Genre ---");
        String genre = Console.readLine("Enter movie genre: ");
        for (Movie movie : store.searchMoviesByGenre(genre)) {
            Console.writeLine(movie.toString());
        }
    }

    /**
     * Interactive flow to update a movie's metadata. The method validates the
     * existence of the movie, requests new values from the user and delegates
     * the update to {@link Store#updateMovie}.
     */
    public void updateMovie() {
        Console.writeLine("--- Update Movie ---");
        String movieId = Console.readLine("Enter movie ID to update: ");
        Movie existingMovie = store.findMovieById(movieId);

        if (existingMovie != null) {
            String newTitle = Console.readLine("Enter new title: ");
            String newGenre = Console.readLine("Enter new genre: ");

            double newRentalPrice = 0;
            while (true) {
                try {
                    newRentalPrice = Console.readLineDouble("Enter new rental price: ");
                    break;
                } catch (NumberFormatException e) {
                    Console.writeLine(" Invalid price. Enter a numeric value.");
                }
            }

            Movie updatedMovie = new Movie(movieId, newTitle, newGenre, newRentalPrice);

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

    /**
     * Deletes a movie identified by id. Prints a success or failure message
     * depending on the store operation result.
     */
    public void deleteMovie() {
        Console.writeLine("--- Delete Movie ---");
        String movieId = Console.readLine("Enter movie ID to delete: ");
        if (store.deleteMovie(movieId)) {
            Console.writeLine("Movie deleted successfully.");
        } else {
            Console.writeLine("Movie deletion failed. Movie may not exist.");
        }
    }

    /**
     * Prints every movie stored in the system regardless of availability.
     */
    public void listOfMovies() {
        Console.writeLine("--- List of Movies ---");
        for (Movie movie : store.getAllMovies()) {
            Console.writeLine(movie.toString());
        }
    }

    // ================= CUSTOMER METHODS =================
    /**
     * Interactive flow that registers a new customer. It collects id, name,
     * email and phone number, constructs a {@link Customer} object and delegates
     * the insertion to {@link Store#addCustomer}.
     */
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

    /**
     * Prompts for a customer id, queries the store and prints the customer
     * information if found; otherwise shows a not-found message.
     */
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

    /**
     * Prompts for a (partial) name and prints matching customers returned by
     * the store search method.
     */
    public void findCustomerByName() {
        Console.writeLine("--- Search Customers by Name ---");
        String name = Console.readLine("Enter customer name: ");
        for (Customer customer : store.searchCustomersByName(name)) {
            Console.writeLine(customer.toString());
        }
    }

    /**
     * Interactive flow to update a customer's information. Validates the
     * existence of the customer, reads new attributes and delegates the update
     * to {@link Store#updateCustomer}.
     */
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

    /**
     * Deletes a customer identified by id. Prints feedback based on the store's
     * deletion result.
     */
    public void deleteCustomer() {
        Console.writeLine("--- Delete Customer ---");
        String customerId = Console.readLine("Enter customer ID: ");
        if (store.deleteCustomer(customerId)) {
            Console.writeLine("Customer deleted successfully.");
        } else {
            Console.writeLine("Customer deletion failed. Customer may not exist.");
        }
    }

    /**
     * Prints a list of all customers stored in the system.
     */
    public void listOfCustomers() {
        Console.writeLine("====== List of Customers ======");
        for (Customer c : store.getAllCustomers()) {
            Console.writeLine(c.toString());
        }
    }

    // ================= RENTAL METHODS =================
    /**
     * Interactive flow that registers a new rental. It validates that the
     * customer and movie exist, validates rental day count, constructs a
     * {@link Rental}, computes its cost and delegates the rent operation to
     * {@link Store#rentMovie}. On success it prints a formatted rental receipt.
     */
    public void RentalMovie() {
        Console.writeLine("--- Register Rental ---");

        String customerid = Console.readLine("Enter customer ID: ");
        Customer customer = store.findCustomerById(customerid);

        if (customer == null) {
            System.out.println("Customer not found. Rental registration failed.");
            return;
        }

        int rentalDays = 0;
        while (true) {
            try {
                rentalDays = Console.readLineInt("Enter number of rental days: ");
                if (rentalDays <= 0) {
                    Console.writeLine("Rental days must be greater than zero.");
                    continue;
                }
                break;
            } catch (Exception e) {
                Console.writeLine(" Invalid number of days.");
            }
        }

        String movieId = Console.readLine("Enter movie ID: ");
        Movie movie = store.findMovieById(movieId);

        if (movie == null) {
            Console.writeLine("Movie not found. Rental registration failed.");
            return;
        }

        if (!movie.isAvailable()) {
            Console.writeLine("Movie is not available for rental.");
            return;
        }

        try {
            Rental rental = new Rental(rentalDays, movie, customer);
            rental.calculateRentalCost();

            if (store.rentMovie(rental)) {
                Console.writeLine("=========================================");
                Console.writeLine("              RENTAL RECEIPT             ");
                Console.writeLine("=========================================");

                Console.writeLine("Rental ID      : " + rental.getIdRental());
                Console.writeLine("Customer       : " + customer.getName());
                Console.writeLine("Movie          : " + movie.getTitle());
                Console.writeLine("Rental Days    : " + rental.getRentalDays());
                Console.writeLine("Total Costmoment : $" + rental.getTotalCost());
                Console.writeLine("Return Date    : " + rental.getReturnDate());

                Console.writeLine("=========================================");
                Console.writeLine("         Rental registered successfully!");
                Console.writeLine("=========================================");

            } else {
                Console.writeLine("Rental registration failed.");
            }
        } catch (Exception e) {
            Console.writeLine("Error during rental process: " + e.getMessage());
        }
    }

    /**
     * Interactive flow to register the return of a rented movie. It retrieves
     * the rental by id, computes days of arrears and associated costs using
     * {@link ReturnMovie}, marks the movie as available again and prints a
     * return receipt with the calculated values.
     */
    public void registerReturn() {
        Console.writeLine("--- Register Return ---");

        String rentalId = Console.readLine("Enter rental ID: ");
        Rental rental = store.findRentalById(rentalId);

        if (rental == null) {
            Console.writeLine("Rental not found. Return registration failed.");
            return;
        }

        try {
            ReturnMovie returnMovie = new ReturnMovie(rental);

            long dayArrears = returnMovie.calculationsDaysofArrears();
            double costArrears = returnMovie.calculationsofCostsofArrears();
            double totalCost = returnMovie.calculationsTotaltoPay();

            rental.getMovie().setAvailable(true);

            Console.writeLine("=========================================");
            Console.writeLine("           RETURN RECEIPT                ");
            Console.writeLine("=========================================");

            Console.writeLine("Rental ID       : " + rental.getIdRental());
            Console.writeLine("Days of Arrears : " + dayArrears);
            Console.writeLine("Cost of Arrears : $" + costArrears);
            Console.writeLine("Total to Pay    : $" + totalCost);

            Console.writeLine("=========================================");
            Console.writeLine("        Return registered successfully!  ");
            Console.writeLine("=========================================");
        } catch (Exception e) {
            Console.writeLine(" Error calculating return: " + e.getMessage());
        }
    }

    /**
     * Finds and prints a rental by its identifier. The method handles exceptions
     * and prints a user friendly error message if something goes wrong.
     */
    public void findRentalById() {
        try {
            String rentalId = Console.readLine("Enter rental ID: ");
            Rental rental = store.findRentalById(rentalId);

            if (rental != null) {
                Console.writeLine("The rental was found:");
                Console.writeLine(rental.toString());
            } else {
                Console.writeLine("Rental not found");
            }
        } catch (Exception e) {
            Console.writeLine(" Error searching rental: " + e.getMessage());
        }
    }

    /**
     * Prints all rentals associated with a given customer id.
     *
     * @see Store#getRentalsByCustomer(String)
     */
    public void findRentalByCustomer() {
        String customerId = Console.readLine("Enter Customer ID: ");
        for (Rental rental : store.getRentalsByCustomer(customerId)) {
            Console.writeLine(rental.toString());
        }
    }

    /**
     * Prints a list of all rentals in the system.
     */
    public void listOfRentals() {
        Console.writeLine("====== List of Rentals ======");
        for (Rental r : store.getAllRentals()) {
            Console.writeLine(r.toString());
        }
    }

    /**
     * Retrieves and prints the movie with the least rental count as returned
     * by the store. If no rentals exist, a corresponding message is printed.
     */
    public void leastMovie() {
        Movie least = store.leastMovie();
        if (least != null) {
            Console.writeLine("Least rented movie:");
            Console.writeLine(least.toString());
        } else {
            Console.writeLine("No rentals found.");
        }
    }

    /**
     * Retrieves and prints the most rented movie. If no rentals exist, a
     * corresponding message is printed.
     */
    public void mostRentedMovie() {
        Movie most = store.mostRenta();
        if (most != null) {
            Console.writeLine("Most rented movie:");
            Console.writeLine(most.toString());
        } else {
            Console.writeLine("No rentals found.");
        }
    }

    /**
     * Delegates to the store to print a list of movies with their respective
     * rent counts. The store implementation is responsible for formatting
     * the output or returning a structured result.
     */
    public void showMovieWithRentCount() {
        Console.writeLine("--- Movies with Rent Count ---");
        store.showMoviesWithRentCount();
    }

}
