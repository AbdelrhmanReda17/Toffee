package SystemClasses;
import java.util.Vector;
import java.util.Scanner;
import DataUserClasses.*;
import OrderClasses.*;
import PaymentClasses.PaymentMethod;
import SystemClasses.*;
import java.io.Console;
public class DataManager {
    private Vector<Customer> customers;
    private Vector<Catalog> catalogs;
    private Vector<GiftVoucher> vouchers;
    private LoyaltyPoints loyaltyScheme;
    private Vector<Order> orders;
    private Vector<Admin> admins;

    public DataManager() {
        customers = new Vector<>();
        catalogs = new Vector<>();
        vouchers = new Vector<>();
        loyaltyScheme = new LoyaltyPoints(0, 0);
        orders = new Vector<>();
        admins = new Vector<>();
    }

    public void loadUsers() {
        // Add logic to load users from a data source
        // Add your specific implementation here
    }

    public void loadCatalog() {
        // Add logic to load catalogs from a data source
        // Add your specific implementation here
    }

    public boolean login() {
        // Add logic to handle user login
        boolean found = false;
        Scanner X = new Scanner(System.in);
        int choice;
        do {
            System.out.print("Please enter your choice:\n   1 for Sign in as customer\n   2 for Sign in as User\nEnter : ");
            choice = X.nextInt();
            if (choice != 1 && choice != 2) {
                System.out.println("Invalid choice! Please enter 1 or 2.");
            }
        } while (choice != 1 && choice != 2);
        X.nextLine(); // Consume the newline character left in the buffer
        System.out.print("Enter username: ");
        String nameE = X.nextLine();
        System.out.print("Enter Password: ");
        String passwordD = X.nextLine();
        if (choice == 1) {
            for (Customer customer : customers) {
                if (customer.getName().equals(nameE) && customer.getPassword().equals(passwordD)) {
                    found = true;
                    break;
                }
            }
        } else {
            for (Admin admin : admins) {
                if (admin.getName().equals(nameE) && admin.getPassword().equals(passwordD)) {
                    found = true;
                    break;
                }
            }
        }

        return found;
    }


    public void register() {
        Scanner X = new Scanner(System.in);

        // Enter username and check if it follows the rules or not + check is it unique or not
        boolean found;
        String usernameRegex = "^[a-zA-Z0-9_-]+$";
        String name;
        do {
            found = false; // reset found to false
            System.out.print("Enter username: ");
            name = X.nextLine();
            if (!name.matches(usernameRegex)) {
                System.out.println("Invalid username!! , Username can consist of [ letters, numbers , _ , - ]");
            }
            for (Customer customer : customers) {
                if (customer.getName().equals(name)) {
                    found = true;
                    System.out.println("Username is already exist !! Enter new One");
                    break;
                }
            }
        } while (!name.matches(usernameRegex) || found);


        // Enter phone number and check if it follows the rules or not
        String phoneRegex = "^[0-9]{11}$";
        String phone;
        do {
            System.out.print("Enter phone number: ");
            phone = X.nextLine();
            if (!phone.matches(phoneRegex)) {
                System.out.println("Invalid phone number !! it must only numbers and 11 digit");
            }
        } while (!phone.matches(phoneRegex));


        // Enter address and check if it follows the rules or not
        String addressRegex = "^[a-zA-Z0-9\\s,-]*$";
        String address;
        do {
            System.out.print("Enter address: ");
            address = X.nextLine();
            if (!address.matches(addressRegex)) {
                System.out.println("Invalid address!! Addresses can consist of letters, numbers, [  ,_ ,- ]");
            }
        } while (!address.matches(addressRegex));

        // Enter password  and check if it follows the rules or not
        String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$#&*%^])[A-Za-z\\d$#&*%^]{8,}$";
        String password;
        do {
            System.out.print("Enter password: ");
            password = X.nextLine();
            if (!password.matches(passwordRegex)) {
                System.out.println("Invalid password!! it must consist of letters, numbers and one of [$ # & * % ^ ] and be at least 8 characters long");
            }
        } while (!password.matches(passwordRegex));

        // add new customer to the vector
        Customer newCustomer = new Customer(name, phone, address, password);
        customers.add(newCustomer);
    }


    public void loadVouchers() {
        // Add logic to load vouchers from a data source
        // Add your specific implementation here
    }

    public void loadLoyaltyScheme() {
        // Add logic to load loyalty scheme from a data source
        // Add your specific implementation here
    }

    public void loadOrders() {
        // Add logic to load orders from a data source
        // Add your specific implementation here
    }

    public boolean updateCatalog() {
        // Add logic to update catalogs in the data source
        // Return true if update is successful, false otherwise
        // Add your specific implementation here
        return true;
    }

    public boolean updateOrders() {
        // Add logic to update orders in the data source
        // Return true if update is successful, false otherwise
        // Add your specific implementation here
        return true;
    }

    public boolean updateVouchers() {
        // Add logic to update vouchers in the data source
        // Return true if update is successful, false otherwise
        // Add your specific implementation here
        return true;
    }

    public boolean updateCustomers() {
        // Add logic to update customers in the data source
        // Return true if update is successful, false otherwise
        // Add your specific implementation here
        return true;
    }

    public boolean updateAdmins() {
        // Add logic to update admins in the data source
        // Return true if update is successful, false otherwise
        // Add your specific implementation here
        return true;
    }

    public Vector<Order> getOrders(){
        return orders;
    }
    public Vector<Customer> getcustomers(){
        return customers;
    }
}
