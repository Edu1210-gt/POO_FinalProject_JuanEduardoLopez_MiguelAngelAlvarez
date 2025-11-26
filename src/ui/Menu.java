
package src.ui;
import src.data.StoreData;
import src.domain.*;
public class Menu{
    private Store store;


public Menu(){
    store = StoreData.loadStore("src/data/store.dat");
}

public void star(){
    boolean option = true;

    while(option){
        showMenu();

        String input = Console.readLine("Select an option: ");


        switch(input){
            case "1" -> addMovie();
            case "2" -> addCustomer();
            case "3" -> rentMovie();
            case "4" -> returnMovie();
            case "5" -> showMoviesWithRentCount();
            case "6" -> showMostRentedMovie();
            case "7" -> removeMovie();
            case "8" -> removeCustomer();
            case "0" -> {
                exitMenu();
                option = false;
            
            
            }
             default -> Console.writeLine("Invalid option");
        }
    }
}
public void showMenu(){
    Console.writeLine("=========Menu=========");
    Console.writeLine("1. Add Movie");
    Console.writeLine("2. Add Customer");
    Console.writeLine("3. Make an rent");
    Console.writeLine("4. Return rent");
    Console.writeLine("5. Show movies with rental numbers");
    Console.writeLine("6. Show most rented movie");
    Console.writeLine("7. Remove Movie");
    Console.writeLine("8. Remove Customer");
    Console.writeLine("0. Salir");
}
private void addMovie(){
    String id = Console.readLine("Movie ID: ");
    String title = Console.readLine("Title: ");
    String genre = Console.readLine("Genre: ");

    Movie movie = new Movie(id, title, genre);
    if(store.addMovie(movie)){
        Console.writeLine("Movie added successfully");
    }
    else{
        Console.writeLine("There is already a movie with this ID");
    }

}

private void exitMenu(){
    StoreData.saveStore(store, "src/data/store.dat");
    Console.writeLine("Data saved and exiting the system ");
}

}

