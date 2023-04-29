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
    ApplicationController(){
        Data = new DataManager();
    }
    ApplicationController(DataManager Data){
        this.Data = Data;
    }
    public void StartApplication(){
        Data.LoadDATA();
        Vector<Catalog> ct = Data.getCatalogs();
        ct.forEach(catalog -> {
            System.out.println(catalog.getName() + " ");
            catalog.getItems().forEach(item -> {
                System.out.println(item.getName() + " " + item.getID() + " " + item.getCategory());
            });
            catalog.getItems().removeIf(item -> item.getName().equals("TOFd"));
        });
        
       // Data.updateData();
        Scanner input = new Scanner(System.in);
        System.out.println("HEllO TO TOFFEE SHOP !");   
        System.out.println("Do you wanna Login or Register !"); 
        while(true){
            String Choosing = input.next();
            if(Choosing.toUpperCase().equals("LOGIN")) {
                System.out.println("SIGN IN");
                boolean shouldContinue = true;
                while (shouldContinue) {
                    boolean isLoggedIn = Data.login();
                    if (isLoggedIn) {
                        shouldContinue = false;
                        break;
                    } else {
                        System.out.println("You have entered an invalid password or username");
                    }
                }
            }
            else if(Choosing.toUpperCase().equals("REGISTER")) {
                System.out.println("SIGN UP");
                Data.register();
                break;
            }
            else
                System.out.println("Wrong Choose , Enter [Login or Register] Only !");
        }
    System.out.println("TYYYY");
    }
}