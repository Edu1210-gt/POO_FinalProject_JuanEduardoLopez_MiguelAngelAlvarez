package src.domain;

import java.io.Serializable;
/**
 * Represents a customer in the Movie Rental System.
 * Implements Serializable to allow saving and loading Customer objects from files.
 */
public class Customer implements Serializable{
     // Unique identifier for the customer
    private String customerId;
    // Full name of the customer
    private String name;
    //Customer's email address
    private String email;
    //Customer's phone number
    private String phoneNumber;

// Constructs  a new customer with the provided attributes
public Customer(String customerId, String name, String email, String phoneNumber){
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
}
//Returnsthe customer's a unique ID.
public String getCustomerId(){
    return customerId;
}
//Update the customer's unique ID.
public void setCustomerId(String customerId){
    this.customerId = customerId;

}
//Return the customer's name 
public String getName(){
    return name;
}
//Update the customer's name 
public void setName(String name){
    this.name = name;
}
//Returns the customer's email
public String getEmail(){
    return email;
}
//Update the customer's email
public void setEmail(String email){
    this.email = email;
}
//Returns the customer's phone number.
public String getPhoneNumber(){
    return phoneNumber;
}
//Update the customer's phone number
public void setPhoneNumber(String phoneNumber){
    this.phoneNumber = phoneNumber;
}
//Return a readable string representation of  the customer object.
public String toString(){
    return "Customer ID: " + customerId + " | " +
            "Name: " + name + " | " +
            "Email: " + email + " | " + 
            "Phone Number: " + phoneNumber + " | ";
}
}