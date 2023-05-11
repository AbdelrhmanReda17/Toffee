package ControllerClasses;


import SystemClasses.DataManager;


import java.util.Scanner;



public class ApplicationController {
    public DataManager Data = new DataManager();
    private UserController userController;
    private OrderController orderController;
    int CAOption=0;
    String nameE,passwordD;
    public UserController getUserController() {
        return userController;
    }
    public OrderController getOrderController() {
        return orderController;
    }
    public ApplicationController() {
        Data = new DataManager();
        this.orderController = new OrderController(this);
        this.userController = new UserController(this);
    }
    public void StartApplication() {
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

    public void setNameE(String name) {this.nameE = name;}
    public void setPasswordD(String pass){this.passwordD = pass;}
    public void setCAOption(int n){this.CAOption=n;}

}