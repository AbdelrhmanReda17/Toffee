import SystemClasses.DataManager;

import java.io.Console;
import java.lang.foreign.Addressable;
import java.util.Scanner;
import java.util.Vector;
import DataUserClasses.*;
import OrderClasses.*;
public class ApplicationController{
    private DataManager Data;
    ApplicationController(){
        Data = new DataManager();
    }
    ApplicationController(DataManager Data){
        this.Data = Data;
    }
    public void StartApplication(){
        Data.LoadDATA();
        Scanner input = new Scanner(System.in);
        System.out.println("HEllO TO TOFFEE SHOP !");   
        System.out.println("Do you wanna Login or Register !"); 
        boolean shouldContinue = true;
        while (shouldContinue) {
            String choosing = input.next().toUpperCase();

            if (choosing.equals("LOGIN")) {
                System.out.println("SIGN IN");
                boolean isLoggedIn = Data.login();
                if (isLoggedIn) {
                    shouldContinue = false;
                } else {
                    System.out.println("You have entered an invalid password or username");
                }
            } else if (choosing.equals("REGISTER")) {
                System.out.println("SIGN UP");
                Data.register();
                shouldContinue = false;
            } else {
                System.out.println("Wrong choice, enter 'LOGIN' or 'REGISTER' only!");
            }
        }
    }
}