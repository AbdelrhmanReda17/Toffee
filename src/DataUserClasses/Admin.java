package DataUserClasses;
import java.util.*;

import OrderClasses.*;
import SystemClasses.*;
public class Admin extends User {
    private DataManager Data = new DataManager();
    private Category catalog = new Category();
    public Admin(String name, String password,String email) {
        super(name, password , email);

    }

    public void addItem(Vector<Item> items,Vector<Category> ct) {
        Item item = new Item();
        item.getItem();
        items.add(item);
        boolean foundCategory = false;
        for (Category x : ct) {
            if (item.getCategory().equals(x.getName())) {
                x.addItem(item);
                foundCategory = true;
                break;
            }
        }

        if (!foundCategory) {
            Boolean isSealed = false;
            System.out.println("Due to no category for this item we will create one for you");
            System.out.println("Please let us now the catalog is it Sealed or no (1 for Sealed , 2 for not Sealed)");
            int type = new Scanner(System.in).nextInt();
            while(type != 1 || type != 2){
                System.out.println("You entered a wrong value please one 1 or 2 [ 1 -> Sealed , 2->Not sealed ]");
                type = new Scanner(System.in).nextInt();
            }
            if(type == 1)   isSealed = true;
            Category newCatalog = new Category(item.getCategory() , isSealed);
            newCatalog.addItem(item);
            ct.add(newCatalog);
            Data.setCategories(ct);
        }
        Data.setCategories(ct);
        System.out.println("Item added successfully");
        Data.setItems(items);

    }


    public void editItem(Vector<Item> items) {
        System.out.print("Enter The id of The item you want to Edit: ");
        int id = new Scanner(System.in).nextInt();
        for (Item item : items) {
            if (item.getID() == id) {
               item.printItem(false , false);
                int choice = 0;
                while (choice != 10) {
                    System.out.println("\nWhich field do you want to edit? Choose from the following options:\n" +
                            "1. Name   2. Category   3. Description   4. Brand   5. Price\n" +
                            "6. Discount percentage  7. Points        8. Image   9. Quantity\n"
                            +"10. Save");
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
                catalog.updateIteminCatalog(item , Data);
                System.out.println("Item Edited Successful");
                Data.setItems(items);
                return;
            }
        }
        System.out.println("Item not found");
    }

    public void deleteItem(Vector<Item> items) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID of the item you want to delete: ");
        int id = scanner.nextInt();
        Item itemToRemove = null;
        boolean FoundItem = false;
        for (Item item : items) {
            if (item.getID() == id) {
                item.printItem(false , false);
                System.out.println();
                System.out.println("Do you want to delete this item? Press 1 to confirm, 2 to cancel.");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    itemToRemove = item;
                    FoundItem = true;
                } else {
                    System.out.println("Operation Cancelled.");
                    return;
                }
            }
        }
        if (itemToRemove != null) {
            if (FoundItem) {
                items.remove(itemToRemove);
                catalog.removeItem(itemToRemove , Data);
                System.out.println("Item Delete Successfully");
            }
        }else{
            System.out.println("Item not found.");
        }
        Data.setItems(items);
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