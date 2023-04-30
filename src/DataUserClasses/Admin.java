package DataUserClasses;

import java.net.PasswordAuthentication;
import java.util.Scanner;
import OrderClasses.Item;
import java.util.Vector;
import SystemClasses.*;
public class Admin extends User {
    private DataManager Data = new DataManager();
    private String email;
    public Admin(String name, String password, String email) {
        super(name, password);
        this.email = email;
    }

    public boolean addItem() {
        /*n2as part check if there is no catalog create new one lama a3ml addcatalog b2a hattzbt*/
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
                    Data.updateCatalogs();
                    break;
                }else{
                    addNewCatalog();
                    Data.updateCatalogs();
                }
            }
            return true;
        }else{
            return false;
        }
    }

    public boolean editItem() {
        System.out.print("Enter The id of The item you want to Edit: ");
        int id = new Scanner(System.in).nextInt();
        Vector<Item> items = Data.getItems();
        for (Item item : items) {

            if (item.getID() == id) {
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
                Data.updateCatalogs();
                return true;
            }
        }

        System.out.println("Item not found");
        return false;
    }


    public boolean deleteItem() {
        // Logic to delete an item
        return true; // Return true if successful, false otherwise
    }

    public void setLoyaltyPointsSystem() {
        // Logic to set the loyalty points system
    }

    public boolean suspendUser() {
        // Logic to suspend a customer
        return true; // Return true if successful, false otherwise
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
        // Logic to view all orders
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}