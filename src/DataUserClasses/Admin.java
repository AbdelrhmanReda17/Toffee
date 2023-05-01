package DataUserClasses;

import java.net.PasswordAuthentication;
import java.util.Scanner;

import OrderClasses.CartItem;
import OrderClasses.Item;
import java.util.Vector;

import OrderClasses.Order;
import SystemClasses.*;
public class Admin extends User {
    private DataManager Data = new DataManager();
    private Catalog catalog = new Catalog();
    private String email;
    public Admin(String name, String password, String email) {
        super(name, password);
        this.email = email;
    }

    public boolean addItem() {
        System.out.print("Enter item ID: ");
        int id = new Scanner(System.in).nextInt();

        System.out.print("Enter item name: ");
        String name = new Scanner(System.in).nextLine();

        System.out.print("Enter item category: ");
        String category = new Scanner(System.in).nextLine();

        System.out.print("Enter item description: ");
        String description = new Scanner(System.in).nextLine();

        System.out.print("Enter item brand: ");
        String brand = new Scanner(System.in).nextLine();

        System.out.print("Enter item price: ");
        double price = new Scanner(System.in).nextDouble();

        System.out.print("Enter item discount percentage: ");
        double discountPercentage =  new Scanner(System.in).nextDouble();

        System.out.print("Enter item points: ");
        int points =  new Scanner(System.in).nextInt();

        System.out.print("Enter item image: ");
        String image =  new Scanner(System.in).nextLine();

        System.out.print("Enter item quantity: ");
        int quantity =  new Scanner(System.in).nextInt();

        Item item = new Item(id, name, category, description, brand, price, discountPercentage, points, image, quantity);

        if(Data.addItemToVector(item)){
            Data.updateItems();
            Vector<Catalog> ct = Data.getCatalogs();
            for(Catalog x : ct){
                if(category == x.getName()){
                    catalog.addItem(item);
                    Data.updateCatalogs();
                    break;
                }else{
                    addNewCatalog();
                    catalog.addItem(item);
                    Data.updateCatalogs();
                }
            }
            return true;
        }else{
            return false;
        }
    }
    public void printItemData(Item item){
        System.out.println("Current data:");
        System.out.println("ID: " + item.getID());
        System.out.println("Name: " + item.getName());
        System.out.println("Category: " + item.getCategory());
        System.out.println("Description: " + item.getDescription());
        System.out.println("Brand: " + item.getBrand());
        System.out.println("Price: " + item.getPrice());
        System.out.println("Discount percentage: " + item.getDiscountPercentage());
        System.out.println("Points: " + item.getPoints());
        System.out.println("Image: " + item.getImage());
        System.out.println("Quantity: " + item.getQuantity());
    }

    public boolean editItem() {
        System.out.print("Enter The id of The item you want to Edit: ");
        int id = new Scanner(System.in).nextInt();
        Vector<Item> items = Data.getItems();
        for (Item item : items) {

            if (item.getID() == id) {
               printItemData(item);

                int choice = 0;
                while (choice != 10) {
                    System.out.println("Which field do you want to edit?");
                    System.out.println("   1. Name\n   2. Category\n   3. Description\n   4. Brand\n   5. Price\n   " +
                            "6. Discount percentage\n   7. Points\n   8. Image\n   9. Quantity\n   10. Save");
                    choice = new Scanner(System.in).nextInt();
                    switch (choice) {
                        case 1:
                            System.out.print("Enter the new name: ");
                            item.setName(new Scanner(System.in).nextLine());
                            break;
                        case 2:
                            System.out.print("Enter the new category: ");
                            item.setCategory(new Scanner(System.in).nextLine());
                            break;
                        case 3:
                            System.out.print("Enter the new description: ");
                            item.setDescription(new Scanner(System.in).nextLine());
                            break;
                        case 4:
                            System.out.print("Enter the new brand: ");
                            item.setBrand(new Scanner(System.in).nextLine());
                            break;
                        case 5:
                            System.out.print("Enter the new price: ");
                            item.setPrice(new Scanner(System.in).nextDouble());
                            break;
                        case 6:
                            System.out.print("Enter the new discount percentage: ");
                            item.setDiscountPercentage(new Scanner(System.in).nextDouble());
                            break;
                        case 7:
                            System.out.print("Enter the new points: ");
                            item.setPoints(new Scanner(System.in).nextInt());
                            break;
                        case 8:
                            System.out.print("Enter the new image: ");
                            item.setImage(new Scanner(System.in).nextLine());
                            break;
                        case 9:
                            System.out.print("Enter the new quantity: ");
                            item.setQuantity(new Scanner(System.in).nextInt());
                            break;
                        case 10:
                            break;
                        default:
                            System.out.println("Invalid choice");
                            break;
                    }
                }

                Data.updateItems();
                catalog.updateItem(item);
                Data.updateCatalogs();
                return true;
            }
        }

        System.out.println("Item not found");
        return false;
    }


    public boolean deleteItem() {
        System.out.print("Enter the ID of the item you want to delete: ");
        int id = new Scanner(System.in).nextInt();
        Vector<Item> items = Data.getItems();
        for (Item item : items) {
            if (item.getID() == id) {
                printItemData(item);
                System.out.println("Do you want to delete this item? Press 1 to confirm, 2 to cancel.");
                int choice = new Scanner(System.in).nextInt();
                if (choice == 1) {
                    boolean isRemoved = Data.removeItemFromVector(item);
                    if (isRemoved) {
                        Data.updateItems();
                        catalog.removeItem(item);
                        Data.updateCatalogs();
                        return true;
                    }
                } else {
                    System.out.println("Operation Cancelled.");
                }
            }
        }
        System.out.println("Item not found.");
        return false;
    }



    public void setLoyaltyPointsSystem() {
        // Logic to set the loyalty points system
    }

    public boolean suspendUser() {
        System.out.print("Enter the username of the customer you want to suspend: ");
        String username = new Scanner(System.in).nextLine();
        Vector<Customer>ct = Data.getCustomers();
        for (Customer c : ct) {
            if (c.getName() == username) {
                System.out.println("Name: " + c.getName());
                System.out.println("Phone: " + c.getPhone());
                System.out.println("Address: " + c.getAddress());
                System.out.println("Do you want to suspend this customer? Press 1 to confirm, 2 to cancel.");
                int choice = new Scanner(System.in).nextInt();
                if (choice == 1) {
                    boolean isRemoved = Data.removeCustomerFromVector(c);
                    if (isRemoved) {
                        Data.updateCustomers();
                        return true;
                    }
                } else {
                    System.out.println("Operation cancelled.");
                }
            }
        }
        System.out.println("customer not found.");
        return false;
    }


    public boolean createAGiftVoucher() {
        Scanner X = new Scanner(System.in);
        //voucher code
        String codeRegex = "^[A-Za-z0-9]{16}$";
        String vouchercode;
        do {
            System.out.print("Enter Voucher code: ");
            vouchercode = X.nextLine();
            if (!vouchercode.matches(codeRegex)) {
                System.out.println("Invalid code! The code must be  16 characters  and consist of letters or digits.");
            }
        } while (!vouchercode.matches(codeRegex));


        String valueRegex = "^[0-9]+(\\.[0-9]+)?$";
        String value;
        do {
            System.out.print("Enter Voucher Value : ");
            value = X.nextLine();
            if (!value.matches(valueRegex)) {
                System.out.println("Invalid voucher value, please enter a valid int or float values.");
            }
        } while (!value.matches(valueRegex));

        float VoucherValue = Float.parseFloat(value);
        GiftVoucher newVoucher = new GiftVoucher(vouchercode,VoucherValue);
        if (newVoucher != null) {
            Data.getVouchers().add(newVoucher);
            Data.updateVouchers();
            return true;
        } else {
            return false;
        }
    }

    public void viewStatistics() {
        // Logic to view statistics
    }

    public boolean addNewCatalog() {
        // Logic to add a new catalog
        return true; // Return true if successful, false otherwise
    }

    public boolean updateCatalog(Catalog catalog) {
        // Logic to update a catalog
        return true; // Return true if successful, false otherwise
    }

    public boolean removeCatalog() {
        // Logic to remove a catalog
        return true; // Return true if successful, false otherwise
    }

    public void viewAllOrders() {
        Vector<Order> orders = Data.getOrders();
        int i=0;
        for(Order order : orders){
            System.out.println("Order id : " + order.getOrderId());
            System.out.println("User: " + order.getUser().getName());
            System.out.println("Order status: " + order.getStatus().toString());
            System.out.println("Shopping cart: ");
            for (CartItem item : order.getShopcart().getCartItems()) {
                System.out.println(" - " + item.getName() + " (" + item.getID() + ")");
            }
            System.out.println("Shipping address: " + order.getShippingAddress());
            System.out.println("Order time: " + order.getOrdertime().toString());
            System.out.println("Phone number: " + order.getPhoneNo());
            System.out.println("Gift vouchers: ");
            for (GiftVoucher voucher : order.getGiftVouchers()) {
                System.out.println(" - " + voucher.getCode());
            }
            System.out.println("Loyalty points: " + order.getLoyaltyPoints());
            System.out.println("Total cost: " + order.getTotalCost());
            System.out.println("Payment method: " + order.getPayment().toString());

            i++;
        }
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}