import SystemClasses.DataManager;
import java.util.List;
import java.util.ArrayList;

import java.io.Console;
import java.util.Scanner;
import java.util.Vector;
import DataUserClasses.*;
import OrderClasses.*;
public class ApplicationController{
    private DataManager Data;

    int choice;
    String nameE , passwordD;
    ApplicationController(){
        Data = new DataManager();
    }
    ApplicationController(DataManager Data){
        this.Data = Data;
    }
    public void StartApplication(){
         Data.LoadDATA();
        // Vector<Catalog> ct = Data.getCatalogs();
        // for(Catalog d : ct){
        //     System.out.println(d.getName());
        //     Vector<Item> itt = d.getItems();
        //     for(Item it : itt)
        //     {
        //         System.out.println(it.getName() + it.getDescription() + it.getDiscountPercentage());
        //     }
        // }
        // Data.updateData();

        Scanner input = new Scanner(System.in);
        System.out.println("HEllO TO TOFFEE SHOP !");   
        System.out.println("Do you wanna Login or Register !"); 
        boolean shouldContinue = true;
        while (shouldContinue) {
            String choosing = input.next().toUpperCase();

            if (choosing.equals("LOGIN")) {
                System.out.println("SIGN IN");
                do {
                    System.out.println("Press you are a/an:\n   1) customer\n   2) Admin\n:");
                    choice = input.nextInt();
                    System.out.print("Enter Username: ");
                    nameE = new Scanner(System.in).nextLine();
                    System.out.print("Enter Password: ");
                    passwordD = new Scanner(System.in).nextLine();
                    boolean isLoggedIn = Data.login(choice,nameE,passwordD);
                    if (isLoggedIn) {
                        shouldContinue = false;
                    } else {
                        System.out.println("You have entered an invalid password or username");
                        shouldContinue = true;
                    }
                    } while (shouldContinue);

            } else if (choosing.equals("REGISTER")) {
                System.out.println("SIGN UP");
                Data.register();
                shouldContinue = false;
            } else {
                System.out.println("Wrong choice, enter 'LOGIN' or 'REGISTER' only!");
            }
        }
        Admin admin;
        if(choice == 2){
            admin = Data.getCurrentAdmin(nameE,passwordD);
            while(true) {
                System.out.println("Welcome to the Admin Menu!");
                System.out.println("Please select an option:");
                System.out.println("1. Add Item");
                System.out.println("2. Edit Item");
                System.out.println("3. Delete Item");
                System.out.println("4. View All Orders");
                System.out.println("5. View Statistics");
                System.out.println("6. Set Loyalty Points");
                System.out.println("7. Create Gift Voucher");
                System.out.println("8. Suspend User");
                System.out.println("9. Add Catalog");
                System.out.println("10. Delete Catalog");
                System.out.println("0. Exit");

                int choice = input.nextInt();
                input.nextLine(); // consume newline character

                switch (choice) {
                    case 0:
                        System.out.println("Exiting Admin Menu...");
                        return;
                    case 1:
                        admin.addItem();
                        break;
                    case 2:
                        admin.editItem();
                        break;
                    case 3:
                        admin.deleteItem();
                        break;
                    case 4:
                        admin.viewAllOrders();
                        break;
                    case 5:
                        admin.viewStatistics();
                        break;
                    case 6:
                       admin.setLoyaltyPointsSystem();
                        break;
                    case 7:
                        admin.createAGiftVoucher();
                        break;
                    case 8:
                        admin.suspendUser();
                        break;
                    case 9:
                        admin.addNewCatalog();
                        break;
                    case 10:
                        admin.removeCatalog();
                        break;
                    default:
                        System.out.println("Invalid choice, please select again.");
                }
            }
        }
    }

}
