import DataUserClasses.Admin;
import DataUserClasses.Catalog;
import DataUserClasses.Customer;
import OrderClasses.CartItem;
import OrderClasses.Item;
import OrderClasses.Order;
import PaymentClasses.GiftPayment;
import PaymentClasses.PaymentMethod;
import SystemClasses.DataManager;
import java.util.Scanner;
import java.util.Vector;
public class ApplicationController {
    public DataManager Data;
    private Order order = new Order();
    String nameE, passwordD;

    ApplicationController() {
        Data = new DataManager();
    }

    ApplicationController(DataManager Data) {
        this.Data = Data;
    }

    public void StartApplication() {
        Data.LoadDATA();
        Vector<Catalog> catalogs = Data.getCatalogs();
        Scanner input = new Scanner(System.in);
        int option, CAOption = 0;
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
                    for (Catalog ct : catalogs) {
                        ct.displayCatalog();
                    }
                    System.out.println("If you want to buy something, you must sign in or register first.");
                    System.out.println("Please choose an option : ");
                    System.out.println(" 1 : Register.");
                    System.out.println(" 2 : Sign In.");
                    int SRChoice = input.nextInt();
                    input.nextLine();
                    if (SRChoice == 1) {
                        System.out.println("REGISTER");
                        Data.register();
                        break;
                    } else if (SRChoice == 2) {
                        System.out.println("SIGN IN");

                        System.out.print("Enter Username: ");
                        nameE = input.nextLine();

                        System.out.print("Enter Password: ");
                        passwordD = input.nextLine();
                        CAOption = 1;
                        isLoggedIn = Data.login(CAOption, nameE, passwordD);
                        break;
                    } else {
                        System.out.println("Invalid choice");
                    }
                    break;
                case 2:
                    System.out.println("REGISTRATION PAGE");
                    Data.register();
                    break;
                case 3:
                    System.out.println("SIGN IN AS : ");
                    System.out.println(" 1 : Customer.");
                    System.out.println(" 2 : Admin.");
                    CAOption = input.nextInt();
                    input.nextLine();

                    System.out.print("Enter Username: ");
                    nameE = input.nextLine();

                    System.out.print("Enter Password: ");
                    passwordD = input.nextLine();

                    isLoggedIn = Data.login(CAOption, nameE, passwordD);
                    break;
                case 4:
                    System.out.println("Thank you for using Toffee Shop!");
                    return;
                default:
                    System.out.println("Invalid choice");
            }

            if (isLoggedIn) {
                Boolean isOrderProccess = false;
                while (true) {
                    Admin admin;
                    Customer customer;
                    if (CAOption == 2) {
                        admin = Data.getCurrentAdmin(nameE, passwordD);
                        while (true) {
                            System.out.println("Greetings, administrator "+admin.getName()+" ! You have arrived at the command center for all things Toffee. Let's get to work!");
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
                            System.out.println("0. Log Out");
                            int cho = input.nextInt();
                            input.nextLine();
                            switch (cho) {
                                case 0:
                                    System.out.println("Logging Out");
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
                    } else if (CAOption == 1) {
                        customer = Data.getCurrentCustomer(nameE, passwordD);
                        System.out.println("Welcome to Toffee, " + customer.getName() + "! Get ready to satisfy your sweet tooth!");
                        System.out.println("You have " + customer.getLoyaltyPoints()+ " Loyalty Points");
                        while (true) {
                            System.out.println("Please select an option:");
                            System.out.println("1. View Catalogs");
                            System.out.println("2. View Past Orders");
                            System.out.println("0. Exit");

                            int choose = input.nextInt();
                            if(choose == 1)
                            {
                                System.out.println("Here Are The available Catalogs : ");
                                for (int i = 0; i < catalogs.size(); i++) {
                                    System.out.println(i + 1 + " " + catalogs.get(i).getName());
                                }
                                System.out.println("Please enter the number of the catalog you want to view or enter 0 to exit:");
                                int cho = input.nextInt();
                                if (cho == 0) {
                                    break;
                                }
                                cho = cho - 1;
                                catalogs.get(cho).displayCatalog();

                                while (true) {
                                    System.out.println("Please enter the number of the item you want to buy or enter 0 to choose a different catalog:");
                                    int itemChoice = input.nextInt();
                                    input.nextLine();
                                    if (itemChoice == 0) {
                                        break;
                                    }
                                    Vector<Item> catalogItems = catalogs.get(cho).getItems();
                                    CartItem cartItem = new CartItem();
                                    cartItem = cartItem.convertToCartItem(catalogItems.get(itemChoice - 1), 1);
                                    customer.getShoppingCart().addCartItem(cartItem);
                                    System.out.println("Item added to cart!");
                                    isOrderProccess = true;
                                }
                                if(isOrderProccess)
                                    break;
                            }else if (choose == 2){
                                System.out.println("Here is your the Past Ordered");
                                customer.DisplayPrevOrderHistory();
                                System.out.println("do you want to reorder ? (y->1,n->2)");
                                int cho = input.nextInt();
                                if(cho == 1){
                                    order = customer.reorder();
                                    customer.setShoppingCart(order.getShopcart());
                                    isOrderProccess = true;
                                    break;
                                }
                                if(cho == 2){
                                    break;
                                }
                            }else if (choose == 0){
                                break;
                            }
                        }
                        if(isOrderProccess){
                            customer.getShoppingCart().displayShoppingCart();
                            System.out.println("Please choose an option:");
                            System.out.println(" 1 : Update Cart Items Quantity");
                            System.out.println(" 2 : Place The Order");
                            System.out.println(" 3 : Clear Cart");
                            int cho = input.nextInt();
                            input.nextLine();

                            switch (cho) {
                                case 1:
                                    customer.getShoppingCart().updateCartItem();
                                    System.out.println("Cart updated!");
                                    order.placeOrder(customer);
                                    break;
                                case 2:
                                    if(order.placeOrder(customer)){
                                        System.out.println("Ordered Place Successful ! ");
                                        if(order.getPayment().getMethod() != "Loyalty Payment" ){
                                            customer.setLoyaltyPoints(customer.getLoyaltyPoints() + customer.getShoppingCart().getPointsEarned());
                                            Data.setCurrentCustomer(customer);
                                            Data.updateCustomers();
                                            System.out.println("You Gained " + customer.getShoppingCart().getPointsEarned()+ " Loyalty Points Your Loyalty Points Balance updated to be " + (customer.getLoyaltyPoints()));
                                        }
                                    }else
                                        System.out.println("Ordered Didn't Placed ! ");
                                    break;
                                case 3:
                                    customer.getShoppingCart().clearCart();
                                    System.out.println("Cart cleared!");
                                    break;
                                default:
                                    System.out.println("Invalid input! Please try again.");
                                    break;
                            }

                            while (true) {
                                System.out.println("Do You Want To Create Another Order or To Exit ?");
                                System.out.println("1 : Yes");
                                System.out.println("2 : No");
                                int continueOption = input.nextInt();
                                if (continueOption == 1) {
                                    break;
                                } else {
                                    return;
                                }
                            }

                        }
                    }

                }
            }else{
                System.out.println("Sorry !! There is an Error While logging in");
            }
        }
    }
}