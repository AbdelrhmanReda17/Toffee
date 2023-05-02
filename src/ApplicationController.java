import SystemClasses.DataManager;
import SystemClasses.GiftVoucher;
import SystemClasses.LoyaltyPoints;

import java.util.List;
import java.util.ArrayList;

import java.io.Console;
import java.util.Scanner;
import java.util.Vector;
import DataUserClasses.*;
import OrderClasses.*;
import PaymentClasses.GiftPayment;
public class ApplicationController{
    public DataManager Data = new DataManager();
    private Order order;
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
        Vector<Catalog> catalogs = Data.getCatalogs();
        boolean shouldContinue = true;
        Scanner input = new Scanner(System.in);
        int CAOption = 0;
        System.out.println("HELLO TO TOFFEE SHOP!");
        System.out.println("Press 1. To View Catalogs, 2. To Register, or 3. To Sign In: ");
        int choice = input.nextInt();
        input.nextLine();
        if (choice == 1) {
            for (Catalog ct : catalogs) {
                ct.displayCatalog();
            }
            System.out.println("If you want to buy something, you must sign in or register first.");

            System.out.println("Press 1. to Register, or 2. to Sign In: ");
            int signInOrRegisterChoice = input.nextInt();
            input.nextLine();

            if (signInOrRegisterChoice == 1) {
                System.out.println("SIGN UP");
                Data.register();
                shouldContinue = false;
            } else if (signInOrRegisterChoice == 2) {
                System.out.println("SIGN IN");
                CAOption =1;
                System.out.print("Enter Username: ");
                nameE = new Scanner(System.in).nextLine();
                System.out.print("Enter Password: ");
                passwordD = new Scanner(System.in).nextLine();
                boolean isLoggedIn = Data.login(CAOption, nameE, passwordD);
                if (isLoggedIn) {
                    shouldContinue = false;
                } else {
                    System.out.println("You have entered an invalid password or username");
                    shouldContinue = true;
                }
            }
        } else if (choice == 2) {
            System.out.println("SIGN UP");
            Data.register();
            shouldContinue = false;

        } else if (choice == 3) {
            System.out.println("SIGN IN");
            System.out.println("Press you are a/an:\n   1) Customer\n   2) Admin: ");
            CAOption = input.nextInt();
            System.out.print("Enter Username: ");
            nameE = new Scanner(System.in).nextLine();
            System.out.print("Enter Password: ");
            passwordD = new Scanner(System.in).nextLine();
            boolean isLoggedIn = Data.login(CAOption, nameE, passwordD);
            if (isLoggedIn) {
                shouldContinue = false;
            } else {
                System.out.println("You have entered an invalid password or username");
                shouldContinue = true;
            }
        }

        while (!shouldContinue) {
            Admin admin;
            Customer customer;
            if(CAOption == 2){
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
                    int cho = input.nextInt();
                    input.nextLine();
                    switch (cho) {
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
//                            admin.viewAllOrders();
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

            }else if(CAOption == 1){
                customer = Data.getCurrentCustomer(nameE,passwordD);
                System.out.println("| Welcome To Toffee |");
                while (true) {
                    System.out.println("Here are the available catalogs:");
                    int i = 0;
                    for (Catalog ct : catalogs) {
                        System.out.println((i+1) + " " + ct.getName());
                        i++;
                    }
                    System.out.println("Please enter the number of the catalog you want to view or enter 0 to exit:");
                    int cho = input.nextInt();
                    input.nextLine();

                    if (cho == 0) {
                        break;
                    }

                    catalogs.get(cho - 1).printItems();

                    while (true) {
                        System.out.println("Please enter the number of the item you want to buy or enter 0 to choose a different catalog:");
                        int itemChoice = input.nextInt();
                        input.nextLine();
                        if (itemChoice == 0) {
                            break;
                        }
                        Vector<Item> catalogItems = catalogs.get(cho - 1).getItems();
                        CartItem cart = new CartItem();
                        cart = cart.convertToCartItem(catalogItems.get(itemChoice-1),1);
                        customer.getShoppingCart().addCartItem(cart);
                        System.out.println("Item added to cart!");
                    }
                }
                customer.getShoppingCart().printCartItems();
                System.out.print("Press 1 to Update Cart Items Quantity, 2 to Place Order, or 3 to Clear Cart: ");
                int cho = input.nextInt();
                input.nextLine();
                if (cho == 1) {
                    customer.getShoppingCart().updateCartItem();
                    System.out.println("Cart updated!");
                    order.placeOrder(customer);
                } else if (cho == 2) {
                    order.placeOrder(customer);
                } else if (cho == 3) {
                    customer.getShoppingCart().clearCart();
                    System.out.println("Cart cleared!");
                } else {
                    System.out.println("Invalid input! Please try again.");
                }

            }

    }

}
}
