package SystemClasses;
import java.util.Vector;
import java.util.Date;
import java.util.Scanner;
import DataUserClasses.*;
import OrderClasses.*;
import PaymentClasses.CashPayment;
import PaymentClasses.CreditPayment;
import PaymentClasses.GiftPayment;
import PaymentClasses.LoyaltyPayment;
import PaymentClasses.PaymentMethod;
import PaymentClasses.sendOtp;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 * DataManager is responsible for managing the data for the application.
 * It is responsible for loading, saving, and modifying the data.
 * DataManager is a singleton class, meaning that only one instance of it can exist.
 *
 * DataManager can load data from a file specified by a path using the loadData() method.
 * The data is then stored in the instance variable "data". The data can be modified
 * using the various setter methods provided by DataManager. The modified data can
 * then be saved to the same file using the saveData() method.
 *
 * DataManager also provides methods for querying the data. The getNumEntries() method
 * returns the number of entries in the data. The getEntry() method returns the entry
 * at a specified index.
 *
 * DataManager is thread-safe. Multiple threads can safely access the instance of
 * DataManager at the same time.
 */
public class DataManager {
    private Vector<Customer> customers;
    private Vector<Category> categories;
    private Vector<GiftVoucher> vouchers;
    private LoyaltyPoints loyaltyScheme;
    private Vector<Admin> admins;
    private Vector<Order> orders;
    private Vector<Item> items;
    private Catalog catalogs ;
    /**
     * Constructor for a DataManager object that takes in a Catalog.
     * @param catalogs the catalog to be used
     */
    public DataManager(Catalog catalogs) {
        this.catalogs=catalogs;
    }
    /**
     * Constructor for a DataManager object that takes in a LoyaltyPoints object.
     * @param loyaltyScheme the loyalty scheme to be used
     */
    public DataManager(LoyaltyPoints loyaltyScheme) {
        this.loyaltyScheme=loyaltyScheme;
    }
    /**
     * Default constructor for a DataManager object.
     */
    public DataManager() {
        customers = new Vector<>();
        categories = new Vector<>();
        vouchers = new Vector<>();
        loyaltyScheme = new LoyaltyPoints(0.0,0);
        admins = new Vector<>();
        orders = new Vector<>();
        items = new Vector<>();
        this.catalogs=new Catalog();
    }
    /**
     * Loads all the data for the store.
     */
    public void LoadDATA(){
        loadItems();
        loadCategories();
        loadLoyaltyScheme();
        loadCustomers();
        loadVouchers();
        loadOrders();
        loadAdmins();
    }
    /**
     * Updates all the data for the store.
     */
    public void updateData(){
        updateAdmins();
        updateCategories();
        updateCustomers();
        updateOrders();
        UpdateVouchers_Loyalty();
        updateItems();
    }

    /**
     * Loads customer data from a CSV file and stores it in the customers vector.
     * @throws FileNotFoundException if the CSV file is not found at the specified path.
     */
    public void loadCustomers() {    
        customers = new Vector<>();
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
    
    
    /** 
     * Parses a string containing customer data separated by commas into a Customer object.
     * @param customerData the string containing the customer data
     * @return the Customer object created from the customer data
     */
    private Customer parseCustomerData(String customerData) {
        String[] data = customerData.split(",");
        String name = data[0];
        String password = data[1];
        String email = data[2];
        String phone = data[3];
        String address = data[4];
        int LoyaltyPoints = Integer.parseInt(data[5]);
        Boolean Status = Boolean.parseBoolean(data[6]);

        return new Customer( name, password , email, phone, address ,LoyaltyPoints , Status);
    }
    /**
     * Writes the customer data from the customers vector to a CSV file.
     * @throws FileNotFoundException if the CSV file is not found at the specified path.

     */
    public void updateCustomers() {
        String filePath = "src/ApplicationData/CustomersData.csv";
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Customer customer : customers) {
                writer.write(customer.getName() + "," + customer.getPassword() + "," + customer.getEmail() + "," + customer.getPhone() + "," + customer.getAddress() + "," + customer.getLoyaltyPoints() + "," + customer.getStatus() + "\n");
            }
            writer.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    } 
    /**
     * Returns the customers vector containing all the customer data.
     * 
     * @return the customers vector
     */
    public Vector<Customer> getCustomers(){return customers;}
    /**
     * Sets the customers vector to a new vector containing the specified customer data.
     * 
     * @param customers the new customers vector to be set
     */
    public void setCustomers(Vector<Customer> customers) {this.customers = customers;}


    /**
     * Loads administrator data from a CSV file and adds it to the admins Vector.
     * @throws FileNotFoundException if the CSV file is not found at the specified path.
     */
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
    /**
     * Parses a line of administrator data from a CSV file and returns an Admin object.
     * @param adminData the line of administrator data to parse
     * @return an Admin object representing the parsed data
     */
    private Admin parseAdminData(String adminData) {
        String[] data = adminData.split(",");
        String name = data[0];
            String password = data[1];
            String email = data[2];
            return new Admin(name, password, email);
    }
    /**
     * Updates the CSV file containing administrator data with the current data in the admins Vector.
     * @throws FileNotFoundException if the CSV file is not found at the specified path.
     */
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
    /**
     * Returns the Vector containing all Admin objects.
     * @return the admins Vector
     */
    public Vector<Admin> getAdmins() {
        return admins;
    }
    /**
     * Sets the Vector containing all Admin objects.
     * @param admins the Vector of Admin objects to set
     */
    public void setAdmins(Vector<Admin> admins) {
        this.admins = admins;
    }

    /**
     * Loads the items data from the "ItemsData.csv" file into the items vector.
     * @throws FileNotFoundException if the CSV file is not found at the specified path.
     */
    public void loadItems() {
        items = new Vector<>();
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
    /**
     * Parses an item data string and returns a new Item object.
     * 
     * @param itemData a string containing the item data to be parsed
     * @return a new Item object with the parsed data
     */
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
    /**
     * Updates the "ItemsData.csv" file with the current items data in the items vector.
     * @throws FileNotFoundException if the CSV file is not found at the specified path.
     */
    public void updateItems() {
        String filePath = "src/ApplicationData/ItemsData.csv";
    
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Item item : items) {
                writer.write(item.getID() + "," + item.getName() + "," + item.getCategory() + "," + item.getDescription() + "," +
                        item.getBrand() + "," + item.getPrice() + "," + item.getDiscountPercentage() + "," +
                        item.getPoints() + "," + item.getImage() + "," + item.getQuan() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     /**
     * Returns the items vector.
     * @return the items vector
     */
    public Vector<Item> getItems(){return items;}
    /**
     * Sets the items vector to the specified vector of items.
     * @param items the new vector of items
     */
    public void setItems(Vector<Item> items) {this.items = items;}


     /**
     * Loads the categories data from the file and populates the categories list.
     * The data is read from the file "CategoriesData.csv" in the "ApplicationData" directory.
     * Each line in the file represents a category, its items, and whether it is sealed or not.
     * @throws FileNotFoundException if the CSV file is not found at the specified path.
     */
    public void loadCategories() {
        categories = new Vector<>();

        String filePath = "src/ApplicationData/CategoriesData.csv";
        File file = new File(filePath);
    
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String CategoriesData = scanner.nextLine().trim();
                if (!CategoriesData.isEmpty()) {
                    Category Categories = parseCategoryData(CategoriesData);
                    if (Categories != null) {
                        categories.add(Categories);
                    } else {
                        System.out.println("Invalid Categories data: " + CategoriesData);
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Parses the CategoriesData string and creates a Category object from it.
     * It also adds the category to the appropriate catalogs in the Catalogs object.
     * @param CategoriesData the string containing the category data
     * @return the Category object created from the data, or null if the data is invalid
     */
    private Category parseCategoryData(String CategoriesData) {
        String[] data = CategoriesData.split(",");
        if (data.length < 2) {
            return null; // Invalid Categories data
        }
        String CategoryName = data[0];
        Boolean CategoryType = Boolean.parseBoolean(data[1]);
        Category category = new Category(CategoryName , CategoryType);
        category.getItems().clear();
        for (int i = 2; i < data.length; i++) {
            String itemData = data[i].replaceAll("\"", "").trim();
            Item item = parseItemsData(itemData);
            if (item != null) {
                category.addItem(item);
            } else {
                System.out.println("Invalid item data: " + itemData);
            }
        }
        if(CategoryType == true){
            catalogs.addSealedCategory(category);
        }else{
            catalogs.addNSealedCategory(category);
        }
        return category;
    }
    /**
     * Parses the itemData string and creates an Item object from it.
     * @param itemData the string containing the item data
     * @return the Item object created from the data, or null if the data is invalid
     */
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
    /**
     * Updates the CSV file that stores the categories and their associated items.
     * Uses the current state of the categories vector to write to the file.
     * @throws FileNotFoundException if the CSV file is not found at the specified path.
     */
    public void updateCategories() {
        String filePath = "src/ApplicationData/CategoriesData.csv";
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Category Categories : categories) {
                writer.write(Categories.getName() + "," + Categories.getSealed()+",");
                Vector<Item> items = Categories.getItems();
                for (int i = 0; i < items.size(); i++) {
                    Item item = items.get(i);
                    writer.write("\"" + item.getID() + "|" + item.getName() + "|" + item.getCategory() + "|" + item.getDescription() + "|" +
                            item.getBrand() + "|" + item.getPrice() + "|" + item.getDiscountPercentage() + "|" +
                            item.getPoints() + "|" + item.getImage() + "|" + item.getQuan() + "\"");
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

    /**
     * Returns the vector of categories.
     * @return The vector of categories.
     */
    public Vector<Category> getCategories() {return categories;}
    /**
     * Sets the vector of categories.
     * @param categories The new vector of categories.
     */
    public void setCategories(Vector<Category> categories) {this.categories = categories;}

    /**
     * Loads voucher data from a CSV file and stores it in a vector of GiftVoucher objects.
     * It calls the 'parseVoucherData' method to parse each line of data in the file and create a GiftVoucher object.
     * The data is read from the file "SystemData.csv" in the "ApplicationData" directory.
     * @throws FileNotFoundException if the CSV file is not found at the specified path.
    */
    public void loadVouchers() {
        vouchers = new Vector<>();
        String filePath = "src/ApplicationData/SystemData.csv";
        File file = new File(filePath);
    
        try {
            Scanner scanner = new Scanner(file);
            int x = 0;
            while (scanner.hasNextLine()) {
                String VoucherData = scanner.nextLine();
                if(x == 0) x++;
                else {
                    GiftVoucher GiftPayment = parseVoucherData(VoucherData);
                    vouchers.add(GiftPayment);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * This is a private method that takes a line of data from the 'SystemData.csv' file and parses it to create a GiftVoucher object.
     * It returns the created GiftVoucher object.
     * 
     * @param VoucherData a line of data from the 'SystemData.csv' file
     * @return GiftVoucher object created by parsing the line of data
     */
    private GiftVoucher parseVoucherData(String VoucherData){
        String[] data = VoucherData.split(",");
        String code = data[0];
        float val = Float.parseFloat(data[1]);
        return new GiftVoucher(code,val);
    }

    /**
     * This method updates the GiftVoucher and LoyaltyScheme data in the 'SystemData.csv' file.
     * It overwrites the existing data in the file with the updated data provided in the objects.
     */
    public void UpdateVouchers_Loyalty() {
        String filePath = "src/ApplicationData/SystemData.csv";
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(loyaltyScheme.getPointsEarnedperEgp()+ "," + loyaltyScheme.getMaximumpoint() + "\n");
            for (GiftVoucher voucher : vouchers) {
                writer.write(voucher.getCode() + "," + voucher.getValue() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
    
    /**
     * Sets the vouchers in the system.
     * @param vouchers a vector of GiftVoucher objects to set in the system.
    */
    public void setVouchers(Vector<GiftVoucher> vouchers) {this.vouchers = vouchers;}
    /**
     * Gets the vouchers from the system.
     * @return a vector of GiftVoucher objects stored in the system.
    */
    public Vector<GiftVoucher> getVouchers() {return vouchers;}
    
    /**
     * This method loads the LoyaltyScheme data from the 'SystemData.csv' file and stores it in the 'loyaltyScheme' object.
     * It parses the first line of data in the file to set the points earned per EGP and maximum points in the LoyaltyScheme object.
     */
    public void loadLoyaltyScheme() {
        String filePath = "src/ApplicationData/SystemData.csv";
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            if(scanner.hasNextLine()) {
                String LoyaltyData = scanner.nextLine();
                String[] data = LoyaltyData.split(",");
                Double getPoints = Double.parseDouble(data[0]);
                int getMaximumpoint = Integer.parseInt(data[1]);
                loyaltyScheme.setPointsEarnedperEgp(getPoints);
                loyaltyScheme.setMaximumpoint(getMaximumpoint);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Gets the current loyalty scheme.
     * @return the current loyalty scheme.
     */
    public LoyaltyPoints getLoyaltyScheme() {return loyaltyScheme;}
    /**
     * Sets the loyalty scheme to the given loyalty scheme.
     * @param loyaltyScheme the loyalty scheme to set.
     */
    public void setLoyaltyScheme(LoyaltyPoints loyaltyScheme) {this.loyaltyScheme = loyaltyScheme;}
    // -------------------------------------------------------
    //                 Order CLASSES
    // -------------------------------------------------------
    /**
     * Loads the orders data from the "OrderData.csv" file, creates Order objects for each row of data, 
     * and adds them to the "orders" vector. It also calls the loadCustomers function to ensure the 
     * customers data is loaded before parsing the orders.
     * @throws FileNotFoundException if the CSV file is not found at the specified path.
     */
    public void loadOrders() {
        loadCustomers();      
        orders = new Vector<>();
        String filePath = "src/ApplicationData/OrderData.csv";
        File file = new File(filePath);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String orderData = scanner.nextLine();
                Order order = parseOrderData(orderData);
                orders.add(order);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Parses a single row of data from the "OrderData.csv" file and creates an Order object from it.
     * @param orderData A string containing the data for a single order in the format "id,customerName,status,shoppingCartData,shippingAddress,orderTime,paymentMethod".
     * @return An Order object representing the parsed data.
     */
    private Order parseOrderData(String orderData) {
        String[] data = orderData.split(",");
        int orderId = Integer.parseInt(data[0]);
        Customer customer = getCustomerByName(data[1]);
        Order_state status = Order_state.valueOf(data[2]);
        ShoppingCart shopcart = parseShoppingCartData(data[3]);
        String shippingAddress = data[4];
        Date ordertime = parseOrderTime(data[5]);
        String PaymentName = data[6];
        PaymentMethod payment = null;
        if(PaymentName.equals("Cash On Payment")){
            payment = new CashPayment();
        }
        else if (PaymentName.equals("Credit Payment")){
            payment = new CreditPayment();
        }
        else if (PaymentName.equals("Gift Voucher Payment")){
            payment = new GiftPayment();
        }
        else if (PaymentName.equals("Loyalty Payment")){
            payment = new LoyaltyPayment();
        }
        return new Order( orderId,customer, status, shopcart,ordertime, shippingAddress, payment);
    }
    /**
     * Searches the "customers" vector for a Customer object with the specified name.
     * @param name The name of the Customer to search for.
     * @return The Customer object with the specified name, or null if no such Customer exists.
     */
    private Customer getCustomerByName(String name) {  
        for (Customer customer : customers) {
            if (customer.getName().equals(name)) {
                return customer;
            }
        }
        return null;
    }
    /**
     * Parses a single row of data from the "OrderData.csv" file and creates a ShoppingCart object from it.
     * @param cartData A string containing the data for a single ShoppingCart in the format "quantity1|itemId1/quantity2|itemId2/...".
     * @return A ShoppingCart object representing the parsed data.
     */
    private ShoppingCart parseShoppingCartData(String cartData) {
        ShoppingCart shoppingCart = new ShoppingCart();
        String[] data = cartData.split("/");
        for (int i = 0; i < data.length; i++) {
            CartItem item = parseCartItem(data[i]);
            if (item != null) {
                shoppingCart.addCartItem(item);
            }
        }
        return shoppingCart;
    }
    /**
     * Parses the item data and creates a CartItem object.
     * @param itemData A String containing the item data in the format "quantity|ID".
     * @return The created CartItem object.
    */
    private CartItem parseCartItem(String itemData) {
        CartItem cartItem = new CartItem();
        String[] data = itemData.split("\\|");
        int Qun = Integer.parseInt(data[0]);
        int ID = Integer.parseInt(data[1]);
        loadItems();
        Item item = null;
        for(Item it : items){
            if(it.getID()== ID)
            {
                item = it;
            }
        }
        if(item == null){
            return null;
        }
        cartItem.setItem(item);
        cartItem.setQuantity(Qun);
        return cartItem;
    }
    /**
     * Parses the order time string and creates a Date object.
     * @param timeString A String containing the order time in the format "EEE MMM dd HH:mm:ss zzz yyyy".
     * @return The created Date object, or null if the input string cannot be parsed.
    */
    private Date parseOrderTime(String timeString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        try {
            return dateFormat.parse(timeString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; 
        }
    }
    /**
     * Updates the status of each order in the orders list, based on the current time and the order time.
     * Also updates the order data file.
     * Updates the orders data from the "OrderData.csv" file, creates Order objects for each row of data, 
     * @throws FileNotFoundException if the CSV file is not found at the specified path.
    */
    public void updateOrders(){
        String filePath = "src/ApplicationData/OrderData.csv";
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Order order : orders) {
                Date orderDate = order.getOrdertime();
                Date currentDate = order.getOrderTime(false);
                if(currentDate.after(orderDate)) {
                    long timeDifference = currentDate.getTime() - orderDate.getTime();
                    long oneDay = 24 * 60 * 60 * 1000;
                    if (timeDifference >= oneDay) {
                        order.setStatus(Order_state.DELIVERED);
                    }else{
                        order.setStatus(Order_state.IN_DELIVERY);
                    }
                }
                writer.write(order.getOrderId() + "," + order.getUser().getName() + "," +order.getStatus() + ",");
                for (int i = 0; i < order.getShopcart().getCartItems().size() ; i++) {
                    CartItem item = order.getShopcart().getCartItems().get(i);
                    writer.write(item.getQuantity() + "|" + item.getID()) ;
                    if (i < order.getShopcart().getCartItems().size() - 1) {
                        writer.write("/");
                    }
                }
                writer.write("," + order.getShippingAddress() + "," + order.getOrdertime().toString() + "," + order.getPayment().getMethod());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
    /**
    * Returns the vector of orders.
    * @return The vector of orders.
    */
   public Vector<Order> getOrders() {
       return orders;
   }
    /**
     * Logs in a user with the specified credentials.
     *
     * @param choice an integer representing the type of user logging in (1 for customer, 2 for admin)
     * @param nameE a string representing the username of the user attempting to log in
     * @param passwordD a string representing the password of the user attempting to log in
     * @return true if the login attempt is successful, false otherwise
     */
    public boolean login(int choice,String nameE , String passwordD) {
        boolean found = false , isnotValidpass = false;
        if (choice == 1) {
            for (Customer customer : customers) {
                if (customer.getName().equals(nameE)) {
                    if(customer.getPassword().equals(passwordD))
                    {
                        if(customer.getStatus() == true){
                            System.out.println("Sorry , User Suspended");
                            return false;
                        }
                        found = true;
                        break;
                    }else{
                        isnotValidpass = true;
                    }
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
        if(isnotValidpass && found == true){
            System.out.println("Sorry , Wrong password ");
        }else if(!found){
            System.out.println("Sorry , Account not founded");
        }
        return found;
    }

    /**
     * Registers a new customer with the system.
    * @return true if the registration attempt is successful, false otherwise
    */
    public boolean register() {
        boolean found , isCanceled = false;
        String usernameRegex = "^[a-zA-Z0-9_-]+$";
        String name = "", email ="", address ="", phone ="", password ="";
        if(!isCanceled){
            do {        
                found = false; // reset found to false
                System.out.print("Enter username - 0 to cancel: ");
                name = new Scanner(System.in).nextLine();
                if(name.equals("0")){
                    isCanceled = true;
                    break;
                }
                if (!name.matches(usernameRegex)) {
                    System.out.println("Invalid username! Username can consist of [letters, numbers, _, -]");
                }
                for (Customer customer : customers) {
                    if (customer.getName().equals(name)) {
                        found = true;
                        System.out.println("Username already exists! Enter a new one.");
                        break;
                    }
                }
            } while (!name.matches(usernameRegex) || found);
         }

        if(!isCanceled){
            String phoneRegex = "^[0-9]{11}$";
            do {
                System.out.print("Enter phone number - 0 to cancel:  ");
                phone = new Scanner(System.in).nextLine();
                if(phone.equals("0")){
                    isCanceled = true;
                    break;
                }
                if (!phone.matches(phoneRegex)) {
                    System.out.println("Invalid phone number! It must only contain numbers and be 11 digits long.");
                }
            } while (!phone.matches(phoneRegex));
        }
        
        if(!isCanceled){
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            do {
                System.out.print("Enter Email - 0 to cancel: ");
                email = new Scanner(System.in).nextLine();
                if(email.equals("0")){
                    isCanceled = true;
                    break;
                }
                if (!email.matches(emailRegex)) {
                    System.out.println("Invalid Email! It must be in the correct format.");
                }
            } while (!email.matches(emailRegex));
        }

        String addressRegex = "^[a-zA-Z0-9\\s,-]*$";
        if(!isCanceled){
            do {
                System.out.print("Enter address - 0 to cancel: ");        
                address = new Scanner(System.in).nextLine();
                if(address.equals("0")){
                    isCanceled = true;
                    break;
                }
                if (!address.matches(addressRegex)) {
                    System.out.println("Invalid address! Addresses can consist of letters, numbers, [, _, -]");
                }

            } while (!address.matches(addressRegex));
        }

        if(!isCanceled){
            String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$#&*%^])[A-Za-z\\d$#&*%^]{8,}$";
            do {
                System.out.print("Enter password - 0 to cancel: ");
                password = new Scanner(System.in).nextLine();
                if(password.equals("0")){
                    isCanceled = true;
                    break;
                }
                if (!password.matches(passwordRegex)) {
                    System.out.println("Invalid password! It must consist of letters, numbers, and one of [$ # & * % ^ ] and be at least 8 characters long.");
                }

            } while (!password.matches(passwordRegex));
        }
        if(!isCanceled ){
            System.out.println("Please wait While Sending The verification code to your email.... ");
            int otpCode = (int) (Math.random() * 900000) + 100000;
            if (sendOtp.SendOTP(name,email, otpCode)) {
                System.out.println("OTP sent to your email. Please enter the verification code - 0 to cancel:");
                Scanner scanner = new Scanner(System.in);
                int inputCode = scanner.nextInt();
                while(true){
                    if (inputCode == otpCode) {
                        System.out.println("Email verification successful. Registration completed!");
                         customers.add(new Customer(name,password,email, phone, address));
                         return true;
                    }else if(inputCode == 0){
                        System.out.println("Registration rejected.");
                        return false;
                    }
                    else {
                        System.out.println("Invalid verification code.");                
                        inputCode = scanner.nextInt();
                    }
                }
            } else {
                System.out.println("Failed to send OTP to your email. Registration rejected.");
                    return false;
            }
        }else{
            System.out.println("Registration rejected.");
            return false;
        }
    }

    /**
     * Retrieves the current admin based on the given name and password.
     * @param name the name of the admin to retrieve
     * @param password the password of the admin to retrieve
     * @return the Admin object with the matching name and password, or null if not found
    */
    public Admin getCurrentAdmin(String name,String password) {
        for (Admin admin : admins) {
            if (admin.getName().equals(name) && admin.getPassword().equals(password)) {
                return admin;
            }
        }
        return null;
    }
    /**
     * Retrieves the current customer based on the given name and password.
     * @param name the name of the customer to retrieve
     * @param password the password of the customer to retrieve
     * @return the Customer object with the matching name and password, or null if not found
    */
    public Customer getCurrentCustomer(String name,String password) {
        for (Customer customerR : customers) {
            if (customerR.getName().equals(name) && customerR.getPassword().equals(password)) {
                return customerR;
            }
        }
        return null;
    }
    /**
     * Sets the current customer's loyalty points to match the given customer's loyalty points.
     * @param customer the customer whose loyalty points will be set
    */
    public void setCurrentCustomer(Customer customer) {
        for (Customer customerR : customers) {
            if (customerR.getName().equals(customer.getName())) {
                customerR.setLoyaltyPoints(customer.getLoyaltyPoints());
                break; 
            }
        }
    }
    /**
     * Adds the given order to the list of orders.
     * @param order the order to add to the list
    */
    public void setOrders(Order order) {
            orders.add(order);
    }
    /**
     * Sets the list of orders to the given vector of orders.
     * @param or the vector of orders to set
    */
    public void setOrderVector(Vector<Order> or){this.orders = or;};
    /**
     * Retrieves the catalog.
     * @return the Catalog object
    */
    public Catalog getCatalogs() {
        return catalogs;
    }
}
