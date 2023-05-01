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
        String filePath = "src/ApplicationData/CustomersData.csv";
        File file = new File(filePath);
    
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String customerData = scanner.nextLine();
                Customer customer = parseCustomerData(customerData);
                customers.add(customer);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private Customer parseCustomerData(String customerData) {
        String[] data = customerData.split(",");
        String name = data[0];
        String password = data[1];
        String phone = data[2];
        String address = data[3];
        return new Customer(name, password, phone, address);
    }
    
    public void updateCustomers() {
        String filePath = "src/ApplicationData/CustomersData.csv";
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Customer customer : customers) {
                writer.write(customer.getName() + "," + customer.getPassword() + "," + customer.getPhone() + "," + customer.getAddress() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 

    // GET ALL USERS
    public Vector<Customer> getCustomers(){return customers;}

    // -------------------------------------------------------
    //                 ADMIN CLASSES
    // -------------------------------------------------------
    //  LOAD ADMINS VECTOR 
        
        public void loadAdmins() {
            String filePath = "src/ApplicationData/AdminsData.csv";
            File file = new File(filePath);
    
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String adminData = scanner.nextLine();
                    Admin admin = parseAdminData(adminData);
                    admins.add(admin);
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        
    
        private Admin parseAdminData(String adminData) {
            String[] data = adminData.split(",");
            String name = data[0];
            String password = data[1];
            String email = data[2];
            return new Admin(name, password, email);
        }
    
        public void updateAdmins() {
            String filePath = "src/ApplicationData/AdminsData.csv";
            try {
                FileWriter writer = new FileWriter(filePath);
                for (Admin admin : admins) {
                    writer.write(admin.getName() + "," + admin.getPassword() + "," + admin.getEmail() + "\n");
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
        String filePath = "src/ApplicationData/ItemsData.csv";
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
        String[] data = itemData.split(",");
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
        return new Item(ID, name, category, description, brand, price, discountPercentage, points, image, quantity);
    }
    
    public void updateItems() {
        String filePath = "src/ApplicationData/ItemsData.csv";
    
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Item item : items) {
                writer.write(item.getID() + "," + item.getName() + "," + item.getCategory() + "," + item.getDescription() + "," +
                        item.getBrand() + "," + item.getPrice() + "," + item.getDiscountPercentage() + "," +
                        item.getPoints() + "," + item.getImage() + "," + item.getQuantity() + "\n");
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
        String filePath = "src/ApplicationData/CatalogsData.csv";
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
        String[] data = catalogData.split(",");
        if (data.length < 2) {
            return null; // Invalid catalog data
        }
        String catalogName = data[0];
        Catalog catalog = new Catalog(catalogName);
        catalog.getItems().clear();
    
        for (int i = 1; i < data.length; i++) {
            String itemData = data[i].replaceAll("\"", "").trim();
            Item item = parseItemsData(itemData);
            if (item != null) {
                catalog.addItem(item);
            } else {
                System.out.println("Invalid item data: " + itemData);
            }
        }
    
        return catalog;
    }
    
    private Item parseItemsData(String itemData) {
        String[] data = itemData.split("\\|");
        if (data.length != 10) {
            return null;
        }
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

    
        return new Item(ID, name, category, description, brand, price, discountPercentage, points, image, quantity);
    }
    
    public void updateCatalogs() {
        String filePath = "src/ApplicationData/CatalogsData.csv";
    
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Catalog catalog : catalogs) {
                writer.write(catalog.getName() + ",");
                Vector<Item> items = catalog.getItems();
                for (int i = 0; i < items.size(); i++) {
                    Item item = items.get(i);
                    writer.write("\"" + item.getID() + "|" + item.getName() + "|" + item.getCategory() + "|" + item.getDescription() + "|" +
                            item.getBrand() + "|" + item.getPrice() + "|" + item.getDiscountPercentage() + "|" +
                            item.getPoints() + "|" + item.getImage() + "|" + item.getQuantity() + "\"");
                    if (i < items.size() - 1) {
                        writer.write(",");
                    }
                }
                writer.write("\n");
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

    public Admin getCurrentAdmin(String name,String password) {
        for (Admin admin : admins) {
            if (admin.getName().equals(name) && admin.getPassword().equals(password)) {
                return admin;
            }
        }
        return null;
    }

    public boolean login(int choice,String nameE , String passwordD) {
        boolean found = false;
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

        // Enter username and check if it follows the rules or not + check is it unique or not
        boolean found;
        String usernameRegex = "^[a-zA-Z0-9_-]+$";
        String name;
        do {
            found = false; // reset found to false
            System.out.print("Enter username: ");
            name =  new Scanner(System.in).nextLine();
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
            phone =  new Scanner(System.in).nextLine();
            if (!phone.matches(phoneRegex)) {
                System.out.println("Invalid phone number !! it must only numbers and 11 digit");
            }
        } while (!phone.matches(phoneRegex));


        // Enter address and check if it follows the rules or not
        String addressRegex = "^[a-zA-Z0-9\\s,-]*$";
        String address;
        do {
            System.out.print("Enter address: ");
            address =  new Scanner(System.in).nextLine();
            if (!address.matches(addressRegex)) {
                System.out.println("Invalid address!! Addresses can consist of letters, numbers, [  ,_ ,- ]");
            }
        } while (!address.matches(addressRegex));

        // Enter password  and check if it follows the rules or not
        String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$#&*%^])[A-Za-z\\d$#&*%^]{8,}$";
        String password;
        do {
            System.out.print("Enter password: ");
            password =  new Scanner(System.in).nextLine();
            if (!password.matches(passwordRegex)) {
                System.out.println("Invalid password!! it must consist of letters, numbers and one of [$ # & * % ^ ] and be at least 8 characters long");
            }
        } while (!password.matches(passwordRegex));

        // add new customer to the vector
        Customer newCustomer = new Customer(name, password, address, address);
        customers.add(newCustomer);
        updateCustomers();
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

    public boolean addItemToVector(Item newItem) {
        boolean isAdded = items.add(newItem);
        return isAdded;
    }
    public boolean removeItemFromVector(Item toDelete) {
        boolean isAdded = items.remove(toDelete);
        return isAdded;
    }
    public boolean removeCustomerFromVector(Customer toDelete) {
        boolean isremoved = customers.remove(toDelete);
        return isremoved;
    }
}
