package src.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Store implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private ArrayList<Movie> movies;
    private ArrayList<Customer> customers;
    private ArrayList<Rental> rentals;
    

    public Store() {
        this.movies = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.rentals = new ArrayList<>();
    }
//________________________CRUD MOVIE____________________________________________________
    public boolean addMovie(Movie movie) {
        if (findMovieById(movie.getMovieId()) == null) {
            return movies.add(movie);
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
        Movie movie = findMovieById(movieId);
        if (movie != null) {
            movie.setTitle(updatedMovie.getTitle());
            movie.setGenre(updatedMovie.getGenre());
            movie.setRentalPrice(updatedMovie.getRentalPrice());
            return true;
        }
        return false;
    }
    public boolean deleteMovie(String movieId) {
        Movie movie = findMovieById(movieId);
        if (movie != null) {
            return movies.remove(movie);
        }
        return false;
    }
    
    public ArrayList<Movie> getAllMovies() {
        return movies;
    }

//_____________________________CRUD CUSTOMER_______________________

public boolean addCustomer(Customer customer){
    if(findCustomerById(customer.getCustomerId())==null){
        
        return customers.add(customer);
    }
    return false;
}

public Customer findCustomerById(String customerId){
    for(Customer c : customers){
        if(c.getCustomerId().equals(customerId)){
            return c;
        }
    }
    return null;
}

public List<Customer> searchCustomersByName(String name){
    return customers.stream()
            .filter(c -> c.getName().toLowerCase().contains(name.toLowerCase()))
            .collect(Collectors.toList());
}

public boolean updateCustomer(String customerId, Customer updateCustomer){
    Customer customer = findCustomerById(customerId);
    if(customer != null){
        customer.setName(updateCustomer.getName());
        customer.setEmail(updateCustomer.getEmail());
        customer.setPhoneNumber(updateCustomer.getPhoneNumber());
        return true;
    }
return false;
}

public boolean deleteCustomer(String customerId){
    Customer customer = findCustomerById(customerId);

    if(customer != null){
        return customers.remove(customer);
    }
    return false;
}

public ArrayList<Customer> getAllCustomers(){
    return customers;
}

//_________________________CRUD RENTAL______________________________________________
public boolean rentMovie(Rental rental){
    Movie movie = rental.getMovie();
    if(movie.isAvailable()){
        movie.setAvailable(false);
        return rentals.add(rental);
    }
    return false;
}
public boolean returnMovie(String rentalId){
    Rental rental = findRentalById(rentalId);
    if(rental != null){
        rental.getMovie().setAvailable(true);
        return rentals.remove(rental);
    }
    return false;
}

public Rental findRentalById(String rentalId){
    for(Rental r : rentals){
        if(r.getIdRental().equals(rentalId)){
            return r;
        }
    }
    return null;
}
public List<Rental> getRentalsByCustomer(String customerId){
    return rentals.stream()
            .filter(r -> r.getCustomer().getCustomerId().equals(customerId))
            .collect(Collectors.toList());
        
}
public ArrayList<Rental> getAllRentals(){
    return rentals;
}

//________________________REGISTER___________________________________
public Movie leastMovie(){
    if(rentals.isEmpty()) 
        return null;
    Movie least = null;
    int min = Integer.MAX_VALUE;
    for(Movie m : movies){
        int count = 0;
        for(Rental r : rentals){
            if(r.getMovie().getMovieId().equals(m.getMovieId()))
                count ++;
            
            if (count < min){
                min = count;
                least = m;
            }

        }
    }
    return least;
}
public void showMoviesWithRentCount(){
    for(Movie movie : movies){
        int count = 0;

        for (Rental r : rentals){
            if(r.getMovie().getMovieId().equals(movie.getMovieId())){
                count++;
            }
        }
        System.out.println("Title: " + movie.getTitle() + " | Rentals: " + count);
    }
}
public Movie mostRenta(){

    if(rentals.isEmpty()){
        return null;
    }

    Movie most = null;
    int max = 0;

    for(Movie m : movies){
        int count = 0;
        for(Rental r : rentals){
            if(r.getMovie().getMovieId().equals(m.getMovieId())){
            count++;
        }
        
    }
    if(count > max){
        max = count;
        most = m;
    }
}
return most;
}







public void setMovies(ArrayList<Movie> movies){
    this.movies = movies;
}

public void setCustomers(ArrayList<Customer> customers){
    this.customers = customers;
}
public void setRentals(ArrayList<Rental> rentals){
    this.rentals = rentals;
}
}