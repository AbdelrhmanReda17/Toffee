package DataUserClasses;
import java.util.*;

import OrderClasses.Item;
import SystemClasses.*;
public class Admin extends User {
    private DataManager Data = new DataManager();

    /**
     This is the constructor of the Admin class. It creates a new Admin object with the provided name, password, and email.
     @param name the name of the Admin
     @param password the password of the Admin
     @param email the email address of the Admin
     */
    public Admin(String name, String password,String email) {
        super(name, password , email);

    }


    /**
     * This method suspends or unsuspends a customer's account.
     * It displays a list of customers and prompts the user to enter the name of the customer they want to suspend / unsuspend.
     * Then, it searches for the customer by name in the provided DataManager object and displays the customer's details.
     * The user is then prompted to confirm if they want to suspend or unsuspend the customer.
     * If the user confirms, the customer's status is updated and a success message is printed.
     * If the user cancels, the operation is cancelled and a message is printed.
     * @param Data the DataManager object containing the list of customers to search through
     */

    public void un_or_suspendUser(DataManager Data) {
        System.out.println("------------------------------------------------------------------------------------------------");
        Vector<Customer> ct = Data.getCustomers();
        for(int i = 0 ; i < ct.size() ; i++)
        {
            System.out.print(i + ". ");
            ct.get(i).displayCustomer();
        }
        System.out.println("------------------------------------------------------------------------------------------------");

        System.out.print("Enter the name of the customer you want to suspend / unSuspend: ");
        String username = new Scanner(System.in).nextLine();
        Customer customer= null;

        for (Customer c : ct) {
            if (Objects.equals(c.getName(), username)){
                customer = c;
                break;
            }
        }

        if (customer == null) {
            System.out.println("customer not found.");
            return;
        }
        System.out.println("------------------------------------------------------------------------------------------------");
        customer.displayCustomer();
        System.out.println("------------------------------------------------------------------------------------------------");

        System.out.println("Do you want to " + (customer.getStatus() ? "unsuspend" : "suspend" ) +" this customer? Press 1 to confirm, 2 to cancel.");
        int choice = new Scanner(System.in).nextInt();
        if (choice == 1) {
            customer.setStatus( !customer.getStatus());
            System.out.println("User "+ (customer.getStatus() ? "suspend" : "unsuspend" ) +" Successful");
        } else {
            System.out.println("Operation cancelled.");
        }
    }
}