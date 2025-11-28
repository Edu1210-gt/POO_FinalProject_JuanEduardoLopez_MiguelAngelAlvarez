package src.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/*
  Represents the main store of the Movie Rental System.
  Manages collections of movies, customers, and rentals.
  Provides full CRUD functionality and rental/return operations.
 */

public class Store implements Serializable {
    private static final long serialVersionUID = 1L;
//List of all movies registered in the store.
    private ArrayList<Movie> movies;
//List of all customers registeres in the system
    private ArrayList<Customer> customers;
//List of all active rentals
    private ArrayList<Rental> rentals;
//Constucts an empty Store with initilized list.
    public Store() {
        this.movies = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.rentals = new ArrayList<>();
    }
// ======================================================
    // ====================== MOVIE CRUD =====================
    // ======================================================

    //Adds a new movie to the store if it does not already exist.
    public boolean addMovie(Movie movie) {
        if (movie == null)
            return false;

        try {
            if (movie.getMovieId() == null)
                return false;
            //Movie must not already exist
            if (findMovieById(movie.getMovieId()) == null) {
                return movies.add(movie);
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }
    //Finds a movie by its ID
    public Movie findMovieById(String movieId) {
        for (Movie movie : movies) {
            if (movie.getMovieId().equals(movieId)) {
                return movie;
            }
        }
        return null;
    }
    //searches movies by title using case-insensitive matching.
    public List<Movie> searchMoviesByTitle(String title) {
        return movies.stream()
                .filter(m -> m.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }
    //Searches Movies by Genre usig case-insensitive matching.
    public List<Movie> searchMoviesByGenre(String genre) {
        return movies.stream()
                .filter(m -> m.getGenre().toLowerCase().contains(genre.toLowerCase()))
                .collect(Collectors.toList());
    }
    //Return a list of all movies currently available for rent
    public List<Movie> getAvailableMovies() {
        return movies.stream()
                .filter(m -> m.isAvailable())
                .collect(Collectors.toList());
    }
    //Updates an existing movie's information
    public boolean updateMovie(String movieId, Movie updatedMovie) {
        if (movieId == null || updatedMovie == null)
            return false;

        Movie movie = findMovieById(movieId);

        if (movie != null) {
            try {
                movie.setTitle(updatedMovie.getTitle());
                movie.setGenre(updatedMovie.getGenre());
                movie.setRentalPrice(updatedMovie.getRentalPrice());
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
    //Removes a movie from the store

    public boolean deleteMovie(String movieId) {
        if (movieId == null)
            return false;

        Movie movie = findMovieById(movieId);

        try {
            if (movie != null) {
                return movies.remove(movie);
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }
    //Returns all movies in the systmem.
    public ArrayList<Movie> getAllMovies() {
        return movies;
    }

      // ======================================================
    // ==================== CUSTOMER CRUD ====================
    // ======================================================

    //Adds a new customer to the store.
   public boolean addCustomer(Customer customer) {
    if (customer == null)
        return false;

    try {
        
        if (customer.getCustomerId() == null)
            return false;

        
        if (!validarEmail(customer.getEmail())) {
            System.out.println("Correo inválido: " + customer.getEmail());
            return false;
        }

    
        if (findCustomerById(customer.getCustomerId()) == null) {
            return customers.add(customer);
        }
    } catch (Exception e) {
        return false;
    }

    return false;
}


public boolean validarEmail(String email) {
    if (email == null || email.isEmpty()) {
        return false;
    }

    boolean tieneArroba = false;
    boolean tienePuntoDespuesArroba = false;
    int contadorArroba = 0;

    for (int i = 0; i < email.length(); i++) {
        char c = email.charAt(i);

        if (c == '@') {
            contadorArroba++;
            tieneArroba = true;
        }

        
        if (c == '.' && tieneArroba) {
            tienePuntoDespuesArroba = true;
        }
    }

    
    if (!tieneArroba || contadorArroba != 1) {
        return false; 
    }

    if (!tienePuntoDespuesArroba) {
        return false; 
    }

    if (email.charAt(email.length() - 1) == '.') {
        return false; 
    }

    return true; 
}
    
    //Finds a csutomer by ID

    public Customer findCustomerById(String customerId) {
        for (Customer c : customers) {
            if (c.getCustomerId().equals(customerId)) {
                return c;
            }
        }
        return null;
    }
    //searches customers by their name (case-insensitive)
    public List<Customer> searchCustomersByName(String name) {
        return customers.stream()
                .filter(c -> c.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
    //Updates a customer's information

    public boolean updateCustomer(String customerId, Customer updateCustomer) {
        if (customerId == null || updateCustomer == null)
            return false;

        Customer customer = findCustomerById(customerId);

        if (customer != null) {
            try {
                customer.setName(updateCustomer.getName());
                customer.setEmail(updateCustomer.getEmail());
                customer.setPhoneNumber(updateCustomer.getPhoneNumber());
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
    //deletes a customer from the system.

    public boolean deleteCustomer(String customerId) {
        if (customerId == null)
            return false;

        Customer customer = findCustomerById(customerId);

        try {
            if (customer != null) {
                return customers.remove(customer);
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }
    //Returns all customers

    public ArrayList<Customer> getAllCustomers() {
        return customers;
    }
 // ======================================================
    // ====================== RENTAL CRUD ====================
    // ======================================================
    
    //Processes a movie rental 
    //Markes the movie as unavailable and add the rental
    public boolean rentMovie(Rental rental) {
        if (rental == null)
            return false;

        try {
            Movie movie = rental.getMovie();
            if (movie == null)
                return false;

            if (movie.isAvailable()) {
                movie.setAvailable(false);
                return rentals.add(rental);
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }
    //Returns a rented movie by making ir available again
    public boolean returnMovie(String rentalId) {
        if (rentalId == null)
            return false;

        Rental rental = findRentalById(rentalId);

        if (rental != null) {
            try {
                Movie movie = rental.getMovie();
                if (movie != null && !movie.isAvailable()) {
                    movie.setAvailable(true);
                    rentals.remove(rental);
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }

        return false;
    }
//Finds a rental by ID
    public Rental findRentalById(String rentalId) {
        for (Rental r : rentals) {
            if (r.getIdRental().equals(rentalId)) {
                return r;
            }
        }
        return null;
    }
    //returns all rentals made by a specific customer.
    public List<Rental> getRentalsByCustomer(String customerId) {
        return rentals.stream()
                .filter(r -> r.getCustomer().getCustomerId().equals(customerId))
                .collect(Collectors.toList());

    }
//returns all rentals
    public ArrayList<Rental> getAllRentals() {
        return rentals;
    }

      // ======================================================
    // ===================== STATISTICS ======================
    // ======================================================
    
    //returns the movie with the fewesr rentals
    //if no rentals existn, returns null.
    public Movie leastMovie() {
        if (rentals.isEmpty())
            return null;
        Movie least = null;
        int min = Integer.MAX_VALUE;
        for (Movie m : movies) {
            int count = 0;
            for (Rental r : rentals) {
                if (r.getMovie().getMovieId().equals(m.getMovieId()))
                    count++;

                
                }
                if (count < min) {
                    min = count;
                    least = m;

            }
        }
        return least;
    }
    //Prints each movie along with its rental count.

    public void showMoviesWithRentCount() {
        for (Movie movie : movies) {
            int count = 0;

            for (Rental r : rentals) {
                if (r.getMovie().getMovieId().equals(movie.getMovieId())) {
                    count++;
                }
            }
            System.out.println("Title: " + movie.getTitle() + " | Rentals: " + count);
        }
    }
//RETURNS THE MOST RENTED MOVIE
    public Movie mostRenta() {

        if (rentals.isEmpty()) {
            return null;
        }

        Movie most = null;
        int max = 0;

        for (Movie m : movies) {
            int count = 0;
            for (Rental r : rentals) {
                if (r.getMovie().getMovieId().equals(m.getMovieId())) {
                    count++;
                }

            }
            if (count > max) {
                max = count;
                most = m;
            }
        }
        return most;
    }

  // ======================================================
    // ====================== SETTERS ========================
    // ======================================================
    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public void setRentals(ArrayList<Rental> rentals) {
        this.rentals = rentals;
    }
}