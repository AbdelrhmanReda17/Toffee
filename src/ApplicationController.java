import DataUserClasses.Admin;
import DataUserClasses.Catalog;
import DataUserClasses.Category;
import DataUserClasses.Customer;
import OrderClasses.CartItem;
import OrderClasses.Item;
import OrderClasses.Order;
import OrderClasses.OrderManager;
import SystemClasses.DataManager;

import java.util.List;
import java.util.Scanner;
import java.util.Vector;



public class ApplicationController {
    public DataManager Data;
    private Order order = new Order();
    private OrderManager orderManager = new OrderManager();
    private Catalog catalog = new Catalog();
    private Category category = new Category();
    String nameE, passwordD;
    int CAOption = 0;
    ApplicationController() {
        Data = new DataManager();
    }

    public void StartApplication() {
        Data.LoadDATA();
        Vector<Category> Categories = Data.getCategories();
        Scanner input = new Scanner(System.in);
        int option;
        boolean isLoggedIn = false;
        while (true) {
            System.out.println("WELCOME TO TOFFEE SHOP!");
            System.out.println("Please choose an option:");
            System.out.println(" 1 : View Catalogs.");
            System.out.println(" 2 : Register.");
            System.out.println(" 3 : Sign In.");
            System.out.println(" 4 : Exit.");
            option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1:
                   isLoggedIn = displayCatalogs(Data.getCategories());
                    CAOption = 1;
                    break;
                case 2:
                    RegisterPage();
                    break;
                case 3:
                    isLoggedIn = LoginPage();
                    break;
                case 4:
                    Data.updateData();
                    System.out.println("Thank you for choosing Toffee Shop! We hope to see you again soon!");

                    return;
                default:
                    System.out.println("Opps! your Choice Is Wrong , Please re-Enter it");
            }
            if(isLoggedIn)
                break;
        }
        if (isLoggedIn) {
            Options(CAOption);
        }else {
            System.out.println("Oops! Something went wrong while logging in. Please try again!");

        }
    }

    private boolean displayCatalogs(Vector<Category> categories) {
        int choice;
        choice = catalog.displayCatalogs();
        if (choice == 1) {
            catalog.displaySealed();
            return displayCategories(categories);
        }
        catalog.displayNSealed();
        return displayCategories(categories);


    }
    private boolean displayCategories(Vector<Category> Categories){
        Scanner input = new Scanner(System.in);
        for (Category ct : Categories) {
            ct.displayCatalog();
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        System.out.println("Please take note of the following information:");
        System.out.println("If you want to buy something, you must sign in or register first.");
        System.out.println("Please choose an option : ");
        System.out.println(" 1 : Register.");
        System.out.println(" 2 : Sign In.");
        int SRChoice;
        while (true) {
            SRChoice = input.nextInt();
            input.nextLine();
            if (SRChoice == 1 || SRChoice == 2 || SRChoice == 3 || SRChoice == 4) {
                break;
            }
            System.out.println("Opps! your Choice Is Wrong , Please re-Enter it");
        }
        if (SRChoice == 1) {
            System.out.println("REGISTER");
            Data.register();
            System.out.println("Login Page !!");
            System.out.print("Enter Username: ");
            this.nameE = input.nextLine();
            System.out.print("Enter Password: ");
            this.passwordD = input.nextLine();
            return Data.login(1, nameE, passwordD);
        }else  {
            System.out.println("SIGN IN");
            System.out.print("Enter Username: ");
            this.nameE = input.nextLine();
            System.out.print("Enter Password: ");
            this.passwordD = input.nextLine();
            return Data.login(1, nameE, passwordD);
        }

    }


    private void RegisterPage(){
        System.out.println("---------------------------Registration Page--------------------------");
        Data.register();
        System.out.println("-----------------------------------------------------------------------");
    }

    private boolean LoginPage(){
        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println("SIGN IN AS: ");
            System.out.println(" 1 : Customer.");
            System.out.println(" 2 : Admin.");
            CAOption = input.nextInt();
            input.nextLine();
            if(CAOption!=1&&CAOption!=2){
                System.out.println("Opps! your Choice Is Wrong , Please re-Enter it");
            }
            else {
                System.out.print("Enter Username: ");
                nameE = input.nextLine();
                System.out.print("Enter Password: ");
                passwordD = input.nextLine();
                return Data.login(CAOption, nameE, passwordD);
            }
        }
    }
    private void Options(int CAOption){
        while (true) {
            if (CAOption == 1) {
                customerInterface(nameE , passwordD);
                break;
            }
            else if (CAOption == 2) {
                adminInterface(nameE , passwordD);
                break;
            }
        }
    }



    private void adminInterface(String nameE , String PasswordD){
        Admin admin = Data.getCurrentAdmin(nameE, passwordD);
        while (true) {
            System.out.println("Greetings, administrator " + admin.getName() + " ! You have arrived at the command center for all things Toffee. Let's get to work!");
            System.out.println("Please select an option:");
            System.out.println("1. Add Item");
            System.out.println("2. Edit Item");
            System.out.println("3. Delete Item");
            System.out.println("4. View All Orders");
            System.out.println("5. View Statistics");
            System.out.println("6. Set Loyalty Points");
            System.out.println("7. Create Gift Voucher");
            System.out.println("8. Suspend/Unsuspend a User");
            System.out.println("9. Add Catalog");
            System.out.println("10. Delete Catalog");
            System.out.println("11. Change Order Status");
            System.out.println("0. Log Out");
            Scanner scanner = new Scanner(System.in);
            int cho;
            cho = scanner.nextInt();
            scanner.nextLine();
            switch (cho) {
                case 0:
                    Data.updateData();
                    System.out.println("Logging Out");
                    return;
                case 1:
                    admin.addItem(Data.getItems(),Data.getCategories());
                    break;
                case 2:
                    admin.editItem(Data.getItems());
                    break;
                case 3:
                    admin.deleteItem(Data.getItems());
                    break;
                case 4:
                    orderManager.viewAllOrders(Data.getOrders());
                    break;
                case 5:
                    admin.viewStatistics(Data.getOrders());
                    break;
                case 6:
                    admin.setLoyaltyPointsSystem(Data.getLoyaltyScheme());
                    break;
                case 7:
                    admin.createAGiftVoucher(Data.getVouchers());
                    break;
                case 8:
                    admin.un_or_suspendUser(Data);
                    break;
                case 9:
                    //admin.addNewCatalog(Data.getCategories(),Data.getItems());
                    break;
                case 10:
                    admin.removeCatalog(Data.getCategories());
                    break;
                case 11:
                    admin.ChangeOrderStatus(Data.getOrders());
                    break;
                default:
                    System.out.println("Opps! your Choice Is Wrong , Please re-Enter it");
            }
        }
    }




    private void customerInterface(String nameE , String PasswordD){
        boolean PaymentProccess = false;
        Scanner input = new Scanner(System.in);
        Customer customer = Data.getCurrentCustomer(nameE, passwordD);
        System.out.println("Welcome to Toffee, " + customer.getName() + "! Get ready to satisfy your sweet tooth!");
        System.out.println("You have " + customer.getLoyaltyPoints() + " Loyalty Points");
        int choice;
        while(true){
            System.out.println("Please select an option:");
            System.out.println("1 : View Catalogs");
            System.out.println("2 : View Past Orders");
            System.out.println("3 : Search by item by name");
            System.out.println("4 : Search by item by Brand");
            System.out.println("0. Exit");
            int choose = input.nextInt();
            if(choose == 1){
                 int numberofItems = 0;
                Category categoryY = null;
                 while(true){
                     choice = catalog.displayCatalogs();
                     if(choice == 1){
                         categoryY = choosingCatalog(catalog.getSealedVector());
                     }else if(choice == 2){
                         categoryY = choosingCatalog(catalog.getNSealedVector());
                     }

                     if (categoryY != null ){
                         numberofItems = AddingItems(numberofItems , categoryY, customer);
                         if(numberofItems == 0){
                             PaymentProccess = false;
                         }else{
                             PaymentProccess = true;
                         }
                     }else{
                         if(PaymentProccess == true){
                             if(checkoutProcess(customer)){
                                 break;
                             }else{
                                 return;
                             }
                         }else{
                             break;
                         }
                     }
                 }
            }else if (choose == 2){
                while(true){
                    PaymentProccess = PastOrders(customer);
                    if(PaymentProccess)
                    {
                        if(checkoutProcess(customer)){
                            break;
                        }else{
                            return;
                        }
                    }else{
                        break;
                    }
                }
            }
            else if(choose == 0){
                Data.updateData();
                System.out.println("Thank you for choosing Toffee Shop! We hope to see you again soon!");
                break;
            }

            else if (choose == 3){
                Category c = new Category();

                System.out.print("Enter item name: ");
                String name = new Scanner(System.in).nextLine();
                List<Item> foundItems = c.searchItemsByName(name);
                if (foundItems.isEmpty()) {
                    System.out.println("No items found.");
                } else {
                    System.out.println("Found items: ");
                    for (Item item : foundItems) {
                        System.out.println(item.getName() + " - " + item.getDescription());
                    }
                }
            }
            else if (choose==4) {
                Category c = new Category();

                System.out.print("Enter item Brand: ");
                String name = new Scanner(System.in).nextLine();
                List<Item> foundItems = c.searchItemsByBrand(name);
                if (foundItems.isEmpty()) {
                    System.out.println("No items found.");
                } else {
                    System.out.println("Found items: ");
                    for (Item item : foundItems) {
                        System.out.println(item.getName() + " - " + item.getDescription());
                    }
                }
            }

        }
    }


    private int AddingItems(int numberofItems , Category catalog , Customer customer){
        int numItems = numberofItems;
        Scanner input = new Scanner(System.in);
        catalog.displayCatalog();
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

    private Category choosingCatalog(Vector<Category> catalogs){
        Category category = ChooseingCategory(catalogs);
        return category;
    }


    private Category ChooseingCategory(Vector<Category> catalogs){
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Here Are The available Catalogs : ");
            for (int i = 0; i < catalogs.size(); i++) {
                System.out.println(i + 1 + " " + catalogs.get(i).getName());
            }
            System.out.println("Please enter the number of the catalog you want to view or enter 0 to exit:");
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


    private boolean checkoutProcess(Customer customer){
        boolean isValid = true , isOrderProccess = false;
        Scanner input = new Scanner(System.in);
        System.out.println("---------------------------------------------------------------------- Shopping Cart -----------------------------------------------------------------------------------");
        customer.getShoppingCart().displayShoppingCart();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
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



    private boolean PastOrders(Customer customer){
        boolean isValid = true;
        do{
            Scanner input = new Scanner(System.in);
            System.out.println("Here is your the Past Ordered");
            customer.DisplayPrevOrderHistory(Data);
            System.out.println("Would you like to reorder? (Please enter 1 for yes, 2 for no)");
            int ch2 = input.nextInt();
            if (ch2 == 1) {
                order = customer.reorder(Data);
                customer.setShoppingCart(order.getShopcart());
                return true;
            }
            else if (ch2 == 2) {
                return false;
            }else{
                isValid = false;
            }

        }while(!isValid);

        return false;
    }


}