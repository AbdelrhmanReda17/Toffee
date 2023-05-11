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

public class OrderController {
    public DataManager Data;
    private OrderManager orderManager = new OrderManager();
    private Catalog catalog = new Catalog();
    private ApplicationController applicationController;

    public OrderController(ApplicationController app){
        this.applicationController = app;
        Data = new DataManager();
        Data.LoadDATA();
        catalog = Data.getCatalogs();
    }
    public int AddingItems(int numberofItems , Category catalog , Customer customer){
        int numItems = numberofItems;
        Scanner input = new Scanner(System.in);
        catalog.displayCategory();
        int itemChoice = 0;
        while(true){
            System.out.println("Please enter the number of the item you want to buy or enter 0 to choose a different catalog:");
            itemChoice = input.nextInt();
            while(itemChoice > catalog.getItems().size()){
                System.out.println("Opps! your Choice Is Wrong , Please re-Enter it");
                itemChoice = input.nextInt();
            }
            if (itemChoice == 0) {
                return numItems;
            }else {
                Vector<Item> catalogItems = catalog.getItems();
                CartItem cartItem = new CartItem();
                cartItem = cartItem.convertToCartItem(catalogItems.get(itemChoice - 1), 1);
                customer.getShoppingCart().addCartItem(cartItem);
                System.out.println("Item added to cart!");
                numItems+=1;
                System.out.println("You have " + numItems + " items in your cart.");
            }
        }
    }


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
            return applicationController.getUserController().RegisterPage();
        } else {
            return applicationController.getUserController().LoginPage();
        }
    }

}