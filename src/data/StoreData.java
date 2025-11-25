package src.data;
import src.domain.Store;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
public class StoreData {
    
    public static void save(Store store, String filename){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))){
            out.writeObject(store);
            System.out.println("Store saved successfully. ");

        }catch(Exception e){
            System.out.println("Error saving store: " + e.getMessage());

        }
    }

    public static Store load(String filename){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))){
            return (Store) in.readObject();
        }catch (Exception e){
            System.out.println("Error loading store: " + e.getMessage());
        }
        return new Store();
    }
}