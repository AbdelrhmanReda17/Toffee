package DataUserClasses;
import java.util.*;

import SystemClasses.*;
public class Admin extends User {
    private DataManager Data = new DataManager();

    public Admin(String name, String password,String email) {
        super(name, password , email);

    }


public void setLoyaltyPointsSystem(LoyaltyPoints loyalityPoints) {
    System.out.print("Enter the points per EGP: ");
    Double pointsEarned = new Scanner(System.in).nextDouble();
    System.out.print("Enter the maximum points Via one Order: ");
    int maximumPoint = new Scanner(System.in).nextInt();

    loyalityPoints.setPointsEarnedperEgp(pointsEarned);
    loyalityPoints.setMaximumpoint(maximumPoint);

    if (loyalityPoints.checkLoyaltyPoints()) {
        System.out.println("Loyalty Schema Added Successfully");
    }
    Data.setLoyaltyScheme(loyalityPoints);
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

        System.out.print("Enter the name of the customer you want to suspend: ");
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


    // public void addNewCatalog(Vector<Category>ct,Vector<Item>itemM) {
    //     System.out.print("Enter The Catalog Name : ");
    //     String name = new Scanner(System.in).nextLine();
    //     for (Category x : ct) {
    //         if (x.getName() == name) {
    //             System.out.println("The Catalog Name is Already Exist !");
    //         }
    //     }
    //     Category NewCatalog = new Category(name);
    //     ct.add(NewCatalog);
    //     System.out.print("Press 1.Add New Item , 2.Add Existing Item, 3.No Need To Add Item : ");
    //     int choice = new Scanner(System.in).nextInt();
    //     if(choice == 1){
    //         System.out.println("You Must Add The Item First ! ");
    //         Item item = new Item();
    //         item.getItem();
    //         itemM.add(item);
    //         Data.setItems(itemM);
    //         NewCatalog.addItem(item);
    //     }else if(choice == 2){
    //         System.out.print("Enter Item ID : ");
    //         int id = new Scanner(System.in).nextInt();
    //         Vector<Item> it = Data.getItems();
    //         for (Item item : it) {
    //             if (id == item.getID()) {
    //                 item.setCategory(name);
    //                 NewCatalog.addItem(item);
    //             }
    //         }
    //     }
    //     System.out.println("Catalog added successfully!!");
    //     Data.setCatalogs(ct);

    // }
    public void removeCatalog(Vector<Category> catalogs) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the Name of the catalog you want to remove: ");
        String catalogName = scanner.nextLine();
        Category ToRemove = null;
        for (Category catalog : catalogs) {
            if (catalog.getName().equals(catalogName)) {
                ToRemove = catalog;
                break;
            }
        }

        if (ToRemove != null) {
            catalogs.remove(ToRemove);
            System.out.println("Catalog Deleted Successfully");
        }

        Data.setCategories(catalogs);
    }

}