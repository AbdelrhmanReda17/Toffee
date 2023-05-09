package SystemClasses;
import java.util.Vector;
import java.util.function.BiConsumer;
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
public class DataManager {
    private Vector<Customer> customers;
    private Vector<Category> categories;
    private Vector<GiftVoucher> vouchers;
    private LoyaltyPoints loyaltyScheme;
    private Vector<Admin> admins;
    private Vector<Order> orders;
    private Vector<Item> items;
    private Catalog catalogs = new Catalog();

    public DataManager() {
        customers = new Vector<>();
        categories = new Vector<>();
        vouchers = new Vector<>();
        loyaltyScheme = new LoyaltyPoints(0, 0);
        admins = new Vector<>();
        orders = new Vector<>();
        items = new Vector<>();
    }
    public void LoadDATA(){
        loadItems();
        loadCategories();
        loadLoyaltyScheme();
        loadCustomers();
        loadVouchers();
        loadOrders();
        loadAdmins();
    }
    public void updateData(){
        updateAdmins();
        updateCategories();
        updateCustomers();
        updateOrders();
        UpdateVouchers_Loyalty();
        updateItems();
    }
    // -------------------------------------------------------
    //                 CUSTOMER / USER CLASSES
    // -------------------------------------------------------
    //  LOAD USERS VECTOR 
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
     * @param customerData
     * @return Customer
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
//    /**
//     * @param loadAdmins()
//     * @return Vector<Customer>
//     */
    // GET / SET ALL USERS
    public Vector<Customer> getCustomers(){return customers;}
    public void setCustomers(Vector<Customer> customers) {this.customers = customers;}

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
        
        /** 
         * @param adminData
         * @return Admin
         */
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
        // GET ADMINS
        public Vector<Admin> getAdmins() {return admins;} 


    // -------------------------------------------------------
    //                 ITEM CLASSES
    // -------------------------------------------------------
    //  LOAD ITEMS VECTOR 
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
    // GET / SET ITEMS
    public Vector<Item> getItems(){return items;}
    public void setItems(Vector<Item> items) {this.items = items;}
    // -------------------------------------------------------
    //                 Categories CLASSES
    // -------------------------------------------------------
    // LOAD Categories VECTOR 
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
    
    public void updateCategories() {
        String filePath = "src/ApplicationData/CategoriesData.csv";
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Category Categories : categories) {
                writer.write(Categories.getName() + ",");
                Vector<Item> items = Categories.getItems();
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
    public Vector<Order> getOrders() {
        return orders;
    }
    // GET / SET categories
    public Vector<Category> getCategories() {return categories;}
    public void setCategories(Vector<Category> categories) {this.categories = categories;}

    // -------------------------------------------------------
    //                 VOUCHER/LOYALTY-SCHEME CLASSES
    // -------------------------------------------------------
    // LOAD VOUCHER VECTOR 
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
    private GiftVoucher parseVoucherData(String VoucherData){
        String[] data = VoucherData.split(",");
        String code = data[0];
        float val = Float.parseFloat(data[1]);
        return new GiftVoucher(code,val);
    }

    // UPDATE VOUCHER/LOYALTY IN FILE 
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
    // GET / SET VOUCHERS
    public void setVouchers(Vector<GiftVoucher> vouchers) {this.vouchers = vouchers;}
    public Vector<GiftVoucher> getVouchers() {return vouchers;}
    
    // LOAD Loyalty Scheme
    public void loadLoyaltyScheme() {
        String filePath = "src/ApplicationData/SystemData.csv";
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            if(scanner.hasNextLine()) {
                String LoyaltyData = scanner.nextLine();
                String[] data = LoyaltyData.split(",");
                int getPoints = Integer.parseInt(data[0]);
                int getMaximumpoint = Integer.parseInt(data[1]);
                loyaltyScheme.setPointsEarnedperEgp(getPoints);
                loyaltyScheme.setMaximumpoint(getMaximumpoint);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    // GET / SET Loyalty Scheme
    public LoyaltyPoints getLoyaltyScheme() {return loyaltyScheme;}
    public void setLoyaltyScheme(LoyaltyPoints loyaltyScheme) {this.loyaltyScheme = loyaltyScheme;}
    // -------------------------------------------------------
    //                 Order CLASSES
    // -------------------------------------------------------
    //  LOAD Order VECTOR 
        
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
    private Customer getCustomerByName(String name) {  
        for (Customer customer : customers) {
            if (customer.getName().equals(name)) {
                return customer;
            }
        }
        return null;
    }
    private ShoppingCart parseShoppingCartData(String cartData) {
        ShoppingCart shoppingCart = new ShoppingCart();
        String[] data = cartData.split("/");
        double totalCost = Double.parseDouble(data[0].replaceAll("\"", "").trim());
        int loyaltyPoints = Integer.parseInt(data[1].replaceAll("\"", "").trim());
        for (int i = 2; i < data.length; i++) {
            CartItem item = parseCartItem(data[i]);
            if (item != null) {
                // CartItem cartItem = new CartItem();
                // cartItem.setItem(item);
                // cartItem.setQuantity(item.getQuantity());
                shoppingCart.addCartItem(item);
            }
        }
        shoppingCart.setTotalCost(totalCost);
        shoppingCart.setLoyaltyPoints(loyaltyPoints);
        return shoppingCart;
    }
    private CartItem parseCartItem(String itemData) {
        CartItem cartItem = new CartItem();
        String[] data = itemData.split("\\|");
        int Qun = Integer.parseInt(data[0]);
        int ID = Integer.parseInt(data[1]);
        loadItems();
        Item item = new Item();
        for(Item it : items){
            if(it.getID()== ID)
            {
                item = it;
            }
        }
        cartItem.setItem(item);
        cartItem.setQuantity(Qun);
        return cartItem;
    }
    
    private Date parseOrderTime(String timeString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        try {
            return dateFormat.parse(timeString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; 
        }
    }
    public void updateOrders(){
        String filePath = "src/ApplicationData/OrderData.csv";
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Order order : orders) {
                writer.write(order.getOrderId() + "," + order.getUser().getName() + "," +order.getStatus() + "," + order.getShopcart().getTotalCost() +"/"+ order.getShopcart().getLoyaltyPoints() + "/");
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
    //---------------------------------------------------------------------------------------------

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


    public void register() {
        // Enter username and check if it follows the rules or not + check if it is unique or not
        boolean found;
        String usernameRegex = "^[a-zA-Z0-9_-]+$";
        String name;
        do {
            found = false; // reset found to false
            System.out.print("Enter username: ");
            name = new Scanner(System.in).nextLine();
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


        // Enter phone number and check if it follows the rules or not
        String phoneRegex = "^[0-9]{11}$";
        String phone;
        do {
            System.out.print("Enter phone number: ");
            phone = new Scanner(System.in).nextLine();
            if (!phone.matches(phoneRegex)) {
                System.out.println("Invalid phone number! It must only contain numbers and be 11 digits long.");
            }
        } while (!phone.matches(phoneRegex));

        // Enter Email and check if it follows the rules or not
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        String email;
        do {
            System.out.print("Enter Email: ");
            email = new Scanner(System.in).nextLine();
            if (!email.matches(emailRegex)) {
                System.out.println("Invalid Email! It must be in the correct format.");
            }
        } while (!email.matches(emailRegex));

        // Enter address and check if it follows the rules or not
        String addressRegex = "^[a-zA-Z0-9\\s,-]*$";
        String address;
        do {
            System.out.print("Enter address: ");
            address = new Scanner(System.in).nextLine();
            if (!address.matches(addressRegex)) {
                System.out.println("Invalid address! Addresses can consist of letters, numbers, [, _, -]");
            }
        } while (!address.matches(addressRegex));

        // Enter password and check if it follows the rules or not
        String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$#&*%^])[A-Za-z\\d$#&*%^]{8,}$";
        String password;
        do {
            System.out.print("Enter password: ");
            password = new Scanner(System.in).nextLine();
            if (!password.matches(passwordRegex)) {
                System.out.println("Invalid password! It must consist of letters, numbers, and one of [$ # & * % ^ ] and be at least 8 characters long.");
            }
        } while (!password.matches(passwordRegex));
        System.out.println("Please wait While Sending The verification code to your email.... ");
        int otpCode = (int) (Math.random() * 900000) + 100000;
        if (sendOtp.SendOTP(email, otpCode)) {
            System.out.println("OTP sent to your email. Please enter the verification code:");

            Scanner scanner = new Scanner(System.in);
            int inputCode = scanner.nextInt();
            if (inputCode == otpCode) {
                System.out.println("Email verification successful. Registration completed!");
                customers.add(new Customer(name, password, email, phone, address));
                updateCustomers();
            } else {
                System.out.println("Invalid verification code. Registration rejected.");
            }
        } else {
            System.out.println("Failed to send OTP to your email. Registration rejected.");
        }
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
    public boolean addCategoriesToVector(Category newItem) {
        boolean isAdded = categories.add(newItem);
        return isAdded;
    }

    public Admin getCurrentAdmin(String name,String password) {
        for (Admin admin : admins) {
            if (admin.getName().equals(name) && admin.getPassword().equals(password)) {
                return admin;
            }
        }
        return null;
    }

    public Customer getCurrentCustomer(String name,String password) {
        for (Customer customerR : customers) {
            if (customerR.getName().equals(name) && customerR.getPassword().equals(password)) {
                return customerR;
            }
        }
        return null;
    }
    public void setCurrentCustomer(Customer customer) {
        for (Customer customerR : customers) {
            if (customerR.getName().equals(customer.getName())) {
                customerR.setLoyaltyPoints(customer.getLoyaltyPoints());
                break; 
            }
        }
    }
    public void setOrders(Order order) {
            orders.add(order);
    }
    public void setOrderVector(Vector<Order> or){this.orders = or;};
}
