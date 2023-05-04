package PaymentClasses;

import DataUserClasses.Customer;

import java.util.Objects;
import java.util.Scanner;

public class LoyaltyPayment extends PaymentMethod {
    private String methodName;

    public LoyaltyPayment() {
        this.methodName = "Loyalty Payment";
    }

    public String getMethod() {
        return methodName;
    }

    public float processPayment(int CustomerLoyalty ,String phone, double total_price  , int loyaltyPoints) {
        if (CustomerLoyalty >= loyaltyPoints) {
            Scanner input = new Scanner(System.in);
            System.out.println("Your Loyalty Points: " + CustomerLoyalty +" Loyalty point" + "and your Order Price is " + loyaltyPoints +" Loyalty Point");
            System.out.println("Do you want to Confirm paid ? (y->1 , n->2 )");
            int continueOption = input.nextInt();
            if(continueOption == 1){
                System.out.println("Your new Loyalty Points " + (CustomerLoyalty -loyaltyPoints) +" Loyalty Point");
                return (CustomerLoyalty-loyaltyPoints);
            }else{
                return -1;
            }
        }
        return -1;
    }
}