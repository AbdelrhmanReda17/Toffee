package DataUserClasses;

import java.net.PasswordAuthentication;
import java.util.Scanner;
import OrderClasses.Item;
import SystemClasses.*;
public class Admin extends User {
    private DataManager Data;
    private String email;
    public Admin(String name, String password, String email) {
        super(name, password);
        this.email = email;
    }

    public boolean addItem(Item item) {
        // Logic to add an item
        return true; // Return true if successful, false otherwise
    }

    public boolean editItem(Item item) {
        // Logic to edit an item
        return true; // Return true if successful, false otherwise
    }

    public boolean deleteItem(Item item) {
        // Logic to delete an item
        return true; // Return true if successful, false otherwise
    }

    public void setLoyaltyPointsSystem(LoyaltyPoints loyaltySystem) {
        // Logic to set the loyalty points system
    }

    public boolean suspendUser(Customer user) {
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

    public boolean addNewCatalog(Catalog catalog) {
        // Logic to add a new catalog
        return true; // Return true if successful, false otherwise
    }

    public boolean updateCatalog(Catalog catalog) {
        // Logic to update a catalog
        return true; // Return true if successful, false otherwise
    }

    public boolean removeCatalog(Catalog catalog) {
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