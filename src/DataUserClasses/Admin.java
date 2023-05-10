package DataUserClasses;
import java.util.*;

import OrderClasses.Item;
import SystemClasses.*;
public class Admin extends User {
    private DataManager Data = new DataManager();

    public Admin(String name, String password,String email) {
        super(name, password , email);

    }
    public void un_or_suspendUser(DataManager Data) {
        System.out.println("------------------------------------------------------------------------------------------------");
        Vector<Customer> ct = Data.getCustomers();
        for(int i = 0 ; i < ct.size() ; i++)
        {
            System.out.print(i + ". ");
            ct.get(i).displayCustomer();
        }
        System.out.println("------------------------------------------------------------------------------------------------");

        System.out.print("Enter the name of the customer you want to suspend / unsuspend: ");
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
            //Data.setCurrentCustomer(customer);
            System.out.println("User "+ (customer.getStatus() ? "suspend" : "unsuspend" ) +" Successful");
        } else {
            System.out.println("Operation cancelled.");
        }
    }





}