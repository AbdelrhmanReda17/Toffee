package ControllerClasses;


import SystemClasses.DataManager;


import java.util.Scanner;

/**

The ApplicationController class handles the overall application logic and user interactions.
*/
public class ApplicationController {
    public DataManager Data = new DataManager();
    private UserController userController;
    private OrderController orderController;
    int CAOption=0;
    String nameE,passwordD;
   /**
 * Gets the usercontroller.
 * 
 * @return the usercontroller
 */
    public UserController getUserController() {
        return userController;
    }

    /**
     * Gets the ordercontroller.
     * @return the ordercontroller
     */
    public OrderController getOrderController() {
        return orderController;
    }
    /**
     * Creates an instance of the ApplicationController class.
     * Initializes a DataManager object.
     * Initializes an OrderController object and passes a reference to this ApplicationController object.
     * Initializes a UserController object and passes a reference to this ApplicationController object.
    */
    public ApplicationController() {
        Data = new DataManager();
        this.orderController = new OrderController(this);
        this.userController = new UserController(this);
    }
    /**
     * Starts the Toffee Shop application.
     * Displays a menu of options and handles user input.
    */
    public void StartApplication() {
        Data.LoadDATA();
        Scanner input = new Scanner(System.in);
        int option;
        boolean isLoggedIn = false;
        while (true) {
            System.out.println("WELCOME TO TOFFEE SHOP!");
            System.out.println(" 1 : View Catalogs.");
            System.out.println(" 2 : Register.");
            System.out.println(" 3 : Sign In.");
            System.out.println(" 4 : Exit.");
            System.out.println("Please choose an option:");
            option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1:
                    isLoggedIn = orderController.displayCatalogs();
                    CAOption = 1;
                    break;
                case 2:
                    userController.RegisterPage();
                    break;
                case 3:
                    isLoggedIn = userController.LoginPage();
                    break;
                case 4:
                    Data.updateData();
                    System.out.println("Thank you for choosing Toffee Shop! We hope to see you again soon!");
                    return;
                default:
                    System.out.println("Opps! your Choice Is Wrong , Please re-Enter it");
            }
            if(isLoggedIn) {
                break;
            }
        }
        if (isLoggedIn) {
             CAOption = userController.getCAOption();
             nameE = userController.getNameE();
             passwordD = userController.getPasswordD();
             Options(CAOption);
        }else {
            System.out.println("Oops! Something went wrong while logging in. Please try again!");

        }
    }
    /**
     * Handles the options for a logged-in user based on the given CAOption.
     * @param CAOption The user's chosen option (1 for customer, 2 for admin).
    */
    private void Options(int CAOption){
        while (true) {
            if (CAOption == 1) {
                userController.customerInterface(nameE , passwordD);
                break;
            }
            else if (CAOption == 2) {
                userController.adminInterface(nameE , passwordD);
                break;
            }
        }
    }
    /**
     * Sets the name for the current user.
     * @param name The name of the user.
    */
    public void setNameE(String name) {this.nameE = name;}
    /**
     * Sets the password for the current user.
     * @param pass The password of the user.
    */
    public void setPasswordD(String pass){this.passwordD = pass;}
    /**
     * Sets the chosen option for the current user.
     * @param n The chosen option (1 for customer, 2 for admin).
    */
    public void setCAOption(int n){this.CAOption=n;}
}