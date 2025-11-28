package src.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Store implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<Movie> movies;
    private ArrayList<Customer> customers;
    private ArrayList<Rental> rentals;

    public Store() {
        this.movies = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.rentals = new ArrayList<>();
    }

    // ________________________CRUD
    // MOVIE____________________________________________________
    public boolean addMovie(Movie movie) {
        if (movie == null)
            return false;

        try {
            if (movie.getMovieId() == null)
                return false;
            if (findMovieById(movie.getMovieId()) == null) {
                return movies.add(movie);
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    public Movie findMovieById(String movieId) {
        for (Movie movie : movies) {
            if (movie.getMovieId().equals(movieId)) {
                return movie;
            }
        }
        return null;
    }

    public List<Movie> searchMoviesByTitle(String title) {
        return movies.stream()
                .filter(m -> m.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Movie> searchMoviesByGenre(String genre) {
        return movies.stream()
                .filter(m -> m.getGenre().toLowerCase().contains(genre.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Movie> getAvailableMovies() {
        return movies.stream()
                .filter(m -> m.isAvailable())
                .collect(Collectors.toList());
    }

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

    public ArrayList<Movie> getAllMovies() {
        return movies;
    }

    // _____________________________CRUD CUSTOMER_______________________

    public boolean addCustomer(Customer customer) {
        if (customer == null)
            return false;

        try {
            if (customer.getCustomerId() == null)
                return false;
            if (findCustomerById(customer.getCustomerId()) == null) {
                return customers.add(customer);
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    public Customer findCustomerById(String customerId) {
        for (Customer c : customers) {
            if (c.getCustomerId().equals(customerId)) {
                return c;
            }
        }
        return null;
    }

    public List<Customer> searchCustomersByName(String name) {
        return customers.stream()
                .filter(c -> c.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

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

    public ArrayList<Customer> getAllCustomers() {
        return customers;
    }

    // _________________________CRUD
    // RENTAL______________________________________________
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

    public Rental findRentalById(String rentalId) {
        for (Rental r : rentals) {
            if (r.getIdRental().equals(rentalId)) {
                return r;
            }
        }
        return null;
    }

    public List<Rental> getRentalsByCustomer(String customerId) {
        return rentals.stream()
                .filter(r -> r.getCustomer().getCustomerId().equals(customerId))
                .collect(Collectors.toList());

    }

    public ArrayList<Rental> getAllRentals() {
        return rentals;
    }

    // ________________________REGISTER___________________________________
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

                if (count < min) {
                    min = count;
                    least = m;
                }

            }
        }
        return least;
    }

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