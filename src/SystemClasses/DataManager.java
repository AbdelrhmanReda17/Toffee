package SystemClasses;
import java.util.Vector;
import java.util.Scanner;
import DataUserClasses.*;
import OrderClasses.*;

import java.io.*;
import java.util.Scanner;

public class DataManager {
    private Vector<Customer> customers;
    private Vector<Catalog> catalogs;
    private Vector<GiftVoucher> vouchers;
    private LoyaltyPoints loyaltyScheme;
    private Vector<Order> orders;
    private Vector<Admin> admins;
    private Vector<Item> items;

    public DataManager() {
        customers = new Vector<>();
        catalogs = new Vector<>();
        vouchers = new Vector<>();
        loyaltyScheme = new LoyaltyPoints(0, 0);
        orders = new Vector<>();
        admins = new Vector<>();
        items = new Vector<>();
    }
    public void LoadDATA(){
        loadItems();
        loadCatalogs();
        loadLoyaltyScheme();
        loadOrders();
        loadCustomers();
        loadVouchers();
        loadAdmins();
    }
    public void updateData(){
        updateAdmins();
        updateCatalogs();
        updateCustomers();
        updateVouchers();
        updateOrders();
        updateItems();
        //updateloyaltyScheme();
    }
    // -------------------------------------------------------
    //                 CUSTOMER / USER CLASSES
    // -------------------------------------------------------
    //  LOAD USERS VECTOR 
    public void loadCustomers() {
        String filePath = "D:/Projects/Toffee/src/ApplicationData/users.txt";
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String userData = scanner.nextLine();
                // Parse the user data and create a User object
                Customer user = parseCustomerData(userData);
                customers.add(user);  // Assuming User extends Customer class
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private Customer parseCustomerData(String userData) {
        String[] data = userData.split("\\|");
        String name = data[0];
        String password = data[1];
        String phone = data[2];
        String address = data[3];
        return new Customer(name, password, phone, address);
    }

    // UPDATE USERS TO FILE 
    public void updateCustomers() {
        try {
            String filePath = "D:/Projects/Toffee/src/ApplicationData/users.txt";
            File file = new File(filePath);
            FileWriter writer = new FileWriter(file, false);

            for (Customer user : customers) {
                writer.write(  user.getName() + "|" + user.getPassword() + "|" + user.getPhone() + "|" + user.getAddress() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    

    // GET ALL USERS
    public Vector<Customer> getCustomers(){
        return customers;
    }

    // -------------------------------------------------------
    //                 ADMIN CLASSES
    // -------------------------------------------------------
    //  LOAD ADMINS VECTOR 
    public void loadAdmins() {
        String filePath = "D:/Projects/Toffee/src/ApplicationData/admins.txt";
        File file = new File(filePath);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String adminData = scanner.nextLine();
                // Parse the admin data and create an Admin object
                Admin admin = parseAdminData(adminData);
                admins.add(admin);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private Admin parseAdminData(String adminData) {
        String[] data = adminData.split("\\|");
        String name = data[0];
        String password = data[1];
        String email = data[2];
        return new Admin(name, password, email);
    }
    //  UPDATE ADMINS INTO FILE 

    public void updateAdmins() {
        String filePath = "D:/Projects/Toffee/src/ApplicationData/admins.txt";

        try {
            FileWriter writer = new FileWriter(filePath);
            for (Admin admin : admins) {
                writer.write(admin.getName() + "|" + admin.getPassword() + "|" + admin.getEmail() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Vector<Admin> getAdmins() {
        return admins;
    }



    // -------------------------------------------------------
    //                 ITEM CLASSES
    // -------------------------------------------------------
    //  LOAD ITEMS VECTOR 
    public void loadItems() {
        String filePath = "D:/Projects/Toffee/src/ApplicationData/items.txt";
        File file = new File(filePath);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String itemData = scanner.nextLine();
                Item item = parseItemData(itemData);
                items.add(item);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private Item parseItemData(String itemData) {
        String[] data = itemData.split("\\|");
        int ID = Integer.parseInt(data[0]);
        String name = data[1];
        String category = data[2];
        String description = data[3];
        String brand = data[4];
        double price = Double.parseDouble(data[5]);
        double discountPercentage = Double.parseDouble(data[6]);
        int points = Integer.parseInt(data[7]);
        String image = data[8];
        int quantity = Integer.parseInt(data[9]);
        return new Item(ID,name, category, description, brand, price, discountPercentage, points, image, quantity);
    }
    // UPDATE ITEMS INTO FILE 
    public void updateItems() {
        String filePath = "D:/Projects/Toffee/src/ApplicationData/items.txt";

        try {
            FileWriter writer = new FileWriter(filePath);
            for (Item item : items) {
                writer.write(item.getID() + "|" + item.getName() + "|" + item.getCategory() + "|" + item.getDescription() + "|" +
                        item.getBrand() + "|" + item.getPrice() + "|" + item.getDiscountPercentage() + "|" +
                        item.getPoints() + "|" + item.getImage() + "|" + item.getQuantity() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Vector<Item> getItems(){
        return items;
    }

    
    // -------------------------------------------------------
    //                 Catalog CLASSES
    // -------------------------------------------------------
    // LOAD CATALOG VECTOR 
    public void loadCatalogs() {
        String filePath = "D:/Projects/Toffee/src/ApplicationData/catalogs.txt";
        File file = new File(filePath);
    
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String catalogData = scanner.nextLine().trim();
                if (!catalogData.isEmpty()) {
                    Catalog catalog = parseCatalogData(catalogData);
                    if (catalog != null) {
                        catalogs.add(catalog);
                    } else {
                        System.out.println("Invalid catalog data: " + catalogData);
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private Catalog parseCatalogData(String catalogData) {
        int catalogNameStartIndex = catalogData.indexOf("(");
        int catalogNameEndIndex = catalogData.indexOf("[");
        String catalogName = catalogData.substring(catalogNameStartIndex + 1, catalogNameEndIndex).trim();
        int itemsStartIndex = catalogData.indexOf("[");
        int itemsEndIndex = catalogData.indexOf("]");
        String itemsString = catalogData.substring(itemsStartIndex + 1, itemsEndIndex).trim();
        String[] itemStrings = itemsString.split(",");
        
        Catalog catalog = new Catalog(catalogName);
        catalog.getItems().clear();
        
        for (String it : itemStrings) {
            Item item = parseItemData(it);
            catalog.addItem(item);
        }
        
        return catalog;
    }
    
    
    
    public void updateCatalogs() {
        String filePath = "D:/Projects/Toffee/src/ApplicationData/catalogs.txt";
        File file = new File(filePath);
    
        try {
            FileWriter writer = new FileWriter(file);
            for (Catalog catalog : catalogs) {
                writer.write("(" + catalog.getName() + "[");
                Vector<Item> items = catalog.getItems();
                for (int i = 0; i < items.size(); i++) {
                    Item item = items.get(i);
                    writer.write(item.getID() + "|" + item.getName() + "|" + item.getCategory() + "|" + item.getDescription() + "|" +
                            item.getBrand() + "|" + item.getPrice() + "|" + item.getDiscountPercentage() + "|" +
                            item.getPoints() + "|" + item.getImage() + "|" + item.getQuantity());
                    if (i < items.size() - 1) {
                        writer.write(",");
                    }
                }
                writer.write("])\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
     }


     public Vector<Catalog> getCatalogs() {
        return catalogs;
    }






    //---------------------------------------------------------------------------------------------
    public boolean login() {
        boolean found = false;
        Scanner X = new Scanner(System.in);
        int choice;
        do {
            System.out.print("Please enter your choice:\n   1. for Sign in as customer\n   2. for Sign in as User\n: ");
            choice = X.nextInt();
            if (choice != 1 && choice != 2) {
                System.out.println("Invalid choice! Please enter 1 or 2.");
            }
        } while (choice != 1 && choice != 2);
        X.nextLine(); // Consume the newline character left in the buffer
        System.out.print("Enter Username: ");
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


    public Vector<Order> getOrders(){
        return orders;
    }
    public Vector<GiftVoucher> getVouchers() {
        return vouchers;
    }
}
