package ControllerClasses;
import DataUserClasses.Admin;
import DataUserClasses.Catalog;
import DataUserClasses.Category;
import DataUserClasses.Customer;
import OrderClasses.Item;
import OrderClasses.OrderManager;
import SystemClasses.DataManager;
import SystemClasses.GiftVoucher;
import SystemClasses.LoyaltyPoints;
import java.util.List;
import java.util.Scanner;

/**
 * The UserController class handles user interactions and controls the flow of the Toffee shop application.
 */
public class UserController {
    public DataManager Data;
    private OrderManager orderManager = new OrderManager();
    private Catalog catalog = new Catalog();
    private Category category = new Category();
    private GiftVoucher Gift = new GiftVoucher();
    private LoyaltyPoints loyalityPoints = new LoyaltyPoints(0.0,0);
    private ApplicationController applicationController;
    int CAOption=0;
    String nameE,passwordD;
    /**
     * Constructs a new UserController object with the provided ApplicationController.
     * @param app The ApplicationController object that manages the application flow.
    */
    public UserController(ApplicationController app)
    {   
        this.applicationController = app;
        Data = new DataManager();
        Data.LoadDATA();
        catalog = Data.getCatalogs();
    }

    /**
     * Displays the admin interface and provides options for performing various administrative tasks.
     * @param nameE The username of the admin.
     * @param PasswordD The password of the admin.
    */
    public void adminInterface(String nameE, String PasswordD) {
        Admin admin = Data.getCurrentAdmin(nameE, PasswordD);
        System.out.println("-------------------------------------------------------------------------------------Home Page----------------------------------------------------------------------------------------");
        System.out.println("Greetings, administrator " + admin.getName() + " ! You have arrived at the command center for all things Toffee. Let's get to work!");
        while (true) {
            System.out.println("What Do You Want To do?");
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
            System.out.println("0. Log Out");
            System.out.println("Please select an option:");
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
                    category.addItem(Data.getItems(), Data.getCategories());
                    break;
                case 2:
                    category.editItem(Data.getItems());
                    break;
                case 3:
                    category.deleteItem(Data.getItems());
                    break;
                case 4:
                    orderManager.viewAllOrders(Data.getOrders());
                    break;
                case 5:
                    orderManager.viewStatistics(Data.getOrders());
                    break;
                case 6:
                    LoyaltyPoints loyaltyPoints = new LoyaltyPoints(Data);
                    loyaltyPoints.setLoyaltyPointsSystem(loyaltyPoints);
                    break;
                case 7:
                    Gift.createAGiftVoucher(Data.getVouchers());
                    break;
                case 8:
                    admin.un_or_suspendUser(Data);
                    break;
                case 9:
                    //catalog.addNewCategory(Data.getCategories(),Data.getItems());
                    break;
                case 10:
                    //catalog.removeCategory(Data.getCategories());
                    break;
                default:
                    System.out.println("Opps! your Choice Is Wrong , Please re-Enter it");
            }
        }
    }
    /**
     * Displays the customer interface and provides options for customers to interact with the Toffee shop.
     * @param nameE The username of the customer.
     * @param PasswordD The password of the customer.
    */
    public void customerInterface(String nameE , String PasswordD){
        Category categoryY = null;
        boolean PaymentProccess = false;
        Scanner input = new Scanner(System.in);
        Customer customer = Data.getCurrentCustomer(nameE, PasswordD);
        System.out.println("--------------------------------------------------------------------------------------Home Page------------------------------------------------------------------------------");
        System.out.println("Welcome to Toffee, " + customer.getName() + "! Get ready to satisfy your sweet tooth!");
        System.out.println("You have " + customer.getLoyaltyPoints() + " Loyalty Points");
        while(true){
            System.out.println("What Do You Want To do?");
            System.out.println("1 : View Catalogs");
            System.out.println("2 : View Past Orders");
            System.out.println("3 : Search by item by name");
            System.out.println("4 : Search by item by Brand");
            System.out.println("0. Exit");
            System.out.println("Please choose an option : ");
            int choose = input.nextInt();
            if(choose == 1){
                int numberofItems = 0;                    
                while(true){
                    int choice = catalog.displayCatalogs();
                    if(choice == 1){
                        categoryY = applicationController.getOrderController().ChooseingCategory(catalog.getSealedVector());
                    }else if(choice == 2){
                        categoryY = applicationController.getOrderController().ChooseingCategory(catalog.getNSealedVector());
                    }else{
                        if(PaymentProccess == true){
                            if(applicationController.getOrderController().checkoutProcess(customer)){
                                break;
                            }else{
                                return;
                            }
                        }else{
                            break;
                        }
                    }
                    if (categoryY != null ){
                            numberofItems = applicationController.getOrderController().AddingItems(numberofItems , categoryY, customer);
                            if(numberofItems == 0){
                                PaymentProccess = false;
                            }else{
                                PaymentProccess = true;
                            }
                    }
                }
            }else if (choose == 2){
                while(true){
                    PaymentProccess = orderManager.PastOrders(customer,Data.getOrders());
                    if(PaymentProccess)
                    {
                        if(applicationController.getOrderController().checkoutProcess(customer)){
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
    /**
     * Displays the registration page and handles the registration process for a new user.
     * @return true if the registration is successful, false otherwise.
    */
    public boolean RegisterPage() {
        boolean isReg = false;
        System.out.println("------------------------------------------------------------------------------------------ Registration Page -------------------------------------------------------------------------------");
         isReg = Data.register();
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        return isReg;
    }
    /**
     * Displays the login page and handles the login process for a user.
     * @return true if the login is successful, false otherwise.
    */
    public boolean LoginPage() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Do You Want To sign in As?....");
            System.out.println("1 : Customer.");
            System.out.println("2 : Admin.");
            CAOption = input.nextInt();
            input.nextLine();
            if (CAOption != 1 && CAOption != 2) {
                System.out.println("Opps! your Choice Is Wrong , Please re-Enter it");
            } else {
                System.out.print("Enter Username: ");
                nameE = input.nextLine();
                System.out.print("Enter Password: ");
                passwordD = input.nextLine();

                return Data.login(CAOption, nameE, passwordD);
            }
        }
    }
    /**
     * Returns the chosen option for the user type during login.
     * @return the chosen option (1 for Customer, 2 for Admin).
    */

    int getCAOption(){return CAOption;}
    /**
     * Returns the entered username during login.
     * @return the entered username.
    */
    
    String getNameE(){return nameE;}

    /**
     * Returns the entered password during login.
     * @return the entered password.
    */
    String getPasswordD(){return passwordD;}
}
