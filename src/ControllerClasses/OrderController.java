package ControllerClasses;

import DataUserClasses.Catalog;
import DataUserClasses.Category;
import DataUserClasses.Customer;
import OrderClasses.CartItem;
import OrderClasses.Item;
import OrderClasses.OrderManager;
import SystemClasses.DataManager;
import java.util.Scanner;
import java.util.Vector;

/**
The OrderController class handles order-related operations and user interactions.
*/
public class OrderController {
    public DataManager Data;
    private OrderManager orderManager = new OrderManager();
    private Catalog catalog = new Catalog();
    private ApplicationController applicationController;
   
    /**
     * Creates an instance of the OrderController class.
     * @param app The ApplicationController instance.
    */
    public OrderController(ApplicationController app){
        this.applicationController = app;
        Data = new DataManager();
        Data.LoadDATA();
        catalog = Data.getCatalogs();
    }

    /**
     * Adds items to the shopping cart.
     * @param numberofItems The number of items to add.
     * @param catalog The catalog from which to select items.
     * @param customer The customer object representing the current user.
     * @return The updated number of items in the cart.
    */   
    public int AddingItems(int numberofItems , Category catalog , Customer customer){
        int numItems = numberofItems;
        Scanner input = new Scanner(System.in);
        int itemChoice = 0;
        while(true){
            catalog.displayCategory();
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Please enter the number of the item you want to buy or enter 0 to choose a different catalog:");
            itemChoice = input.nextInt();
            while(itemChoice > catalog.getItems().size()){
                System.out.println("Opps! your Choice Is Wrong , Please re-Enter it");
                itemChoice = input.nextInt();
            }
            if (itemChoice == 0) {
                return numItems;
            }
            else {
                Vector<Item> catalogItems = catalog.getItems();
                CartItem cartItem = new CartItem();                                        
                cartItem = cartItem.convertToCartItem(catalogItems.get(itemChoice - 1), 1);
                if(catalogItems.get(itemChoice-1).getQuan() != 0){
                    customer.getShoppingCart().addCartItem(cartItem);
                    catalogItems.get(itemChoice-1).setQuan(catalogItems.get(itemChoice-1).getQuan()-1);
                    numItems+=1;
                    System.out.println("Item added to cart! you have " + numItems + " items in your cart");
                }else{
                    System.out.println("Cannout add item , Out Of Stock item");
                }
            }
        }
    }
    /**
     * Allows the user to choose a category from a list of available catalogs.
     * @param catalogs A vector of Category objects representing the available catalogs.
     * @return The selected Category object, or null if the user chooses to exit.
    */
    public Category ChooseingCategory(Vector<Category> catalogs){
        if(catalogs.isEmpty()) {
            System.out.println("Catalog is Empty !");
            return null;
        }
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("----------------------------------------------------------------------------------- Available Categories -----------------------------------------------------------------------------------");
            for (int i = 0; i < catalogs.size(); i++) {
                System.out.println(i + 1 + " " + catalogs.get(i).getName());
            }            
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            System.out.println("Please enter the number of the category you want to view or enter 0 to exit:");
            int choose = input.nextInt();
            while(choose > catalogs.size()){
                System.out.println("Opps! your Choice Is Wrong , Please re-Enter it");
                choose = input.nextInt();
            }
            if (choose == 0) {
                return null;
            }else{
                choose = choose - 1;
                return catalogs.get(choose);
            }
        }
    }
    /**
     * Performs the checkout process for a customer.
     * Displays the shopping cart and provides options for updating the cart, placing the order, or clearing the cart.
     * @param customer The Customer object representing the current user.
     * @return True if the user wants to create another order, false otherwise.
    */
    public boolean checkoutProcess(Customer customer){
        boolean isValid = true , isOrderProccess = false;
        Scanner input = new Scanner(System.in);
        System.out.println("--------------------------------------------------------------------------------------- Shopping Cart -----------------------------------------------------------------------------------");
        customer.getShoppingCart().displayShoppingCart();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        do{
            System.out.println("Please choose an option:");
            System.out.println(" 1 : Update Cart Items Quantity");
            System.out.println(" 2 : Place The Order");
            System.out.println(" 3 : Clear Cart and back");
            int ch = input.nextInt();
            input.nextLine();
            switch (ch) {
                case 1:
                    customer.getShoppingCart().updateCartItem();
                    System.out.println("Cart updated!");
                    isValid = false;
                    break;
                case 2:
                    if (orderManager.placeOrder(Data , customer)) {
                        isOrderProccess = true;
                        System.out.println("Order Place Successful!");
                    }else{
                        System.out.println("Order didn't place!");
                        isValid = false;
                    }
                    break;
                case 3:
                    customer.getShoppingCart().clearCart();
                    System.out.println("Cart cleared!");
                    return true;
                default:
                    System.out.println("Opps! your Choice Is Wrong , Please re-Enter it");
                    isValid = false;
                    break;
            }
            if(isOrderProccess){
                while (true) {
                    System.out.println("Do You Want To Create Another Order?");
                    System.out.println("1 : Yes");
                    System.out.println("2 : No");
                    int continueOption = input.nextInt();
                    if (continueOption == 1) {
                        return true;
                    } else {
                        Data.updateData();
                        System.out.println("Thank you for choosing Toffee! We can't wait to serve you again!");
                        return false;
                    }
                }
            }
        }while(!isValid);
        return false;
    }
    
    /**
     * Displays the available catalogs and allows the user to choose a category.
     * Depending on the user's choice, displays the corresponding category items.
     * @return True if a category is successfully selected and displayed, false otherwise.
    */
    public boolean displayCatalogs() {
        int choice;
        choice = catalog.displayCatalogs();
        if (choice == 1) {
            catalog.displaySealed();
            return displayCategories(catalog.getSealedVector());
        } else if (choice == 2) {
            catalog.displayNSealed();
            return displayCategories(catalog.getNSealedVector());
        } else {
            return false;
        }
    }

    /**
     * Displays the categories and provides options for user registration or sign-in.
     * @param Categories A vector of Category objects representing the available categories.
     * @return True if the user successfully registers or signs in, false otherwise.
    */
    public boolean displayCategories(Vector<Category> Categories) {
        if (Categories.isEmpty()) return false;
        Scanner input = new Scanner(System.in);
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Please take note of the following information:");
        System.out.println("If you want to buy something, you must sign in or register first.");
        System.out.println(" 1 : Register.");
        System.out.println(" 2 : Sign In.");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Please choose an option : ");
        int SRChoice;
        while (true) {
            SRChoice = input.nextInt();
            input.nextLine();
            if (SRChoice == 1 || SRChoice == 2) {
                break;
            }
            System.out.println("Opps! your Choice Is Wrong , Please re-Enter it");
        }
        if (SRChoice == 1) {
            boolean x = applicationController.getUserController().RegisterPage();
            if(x){
                return applicationController.getUserController().LoginPage();
            }else
                return x;
        } else {
            return applicationController.getUserController().LoginPage();
        }
    }

}