package PaymentClasses;

import DataUserClasses.Customer;
import SystemClasses.DataManager;
import java.util.Scanner;

public class LoyaltyPayment extends PaymentMethod {
    private String methodName;

    public LoyaltyPayment() {
        this.methodName = "Loyalty Payment";
    }

    public String getMethod() {
        return methodName;
    }

    public float processPayment(DataManager Data,Customer user , String Email,String phone, double total_price ) {
        int CustomerLoyalty = user.getLoyaltyPoints();
        int loyaltyPoints = user.getShoppingCart().getLoyaltyPoints();
        if (CustomerLoyalty >= loyaltyPoints) {
            Scanner input = new Scanner(System.in);
            System.out.println("Your Loyalty Points: " + CustomerLoyalty +" Loyalty point" + "and your Order Price is " + loyaltyPoints +" Loyalty Point");
            System.out.println("Do you want to Confirm paid ? (y->1 , n->2 ) ");
            int continueOption = input.nextInt();
            if(continueOption == 1){
                System.out.println("Your new Loyalty Points " + (CustomerLoyalty -loyaltyPoints) +" Loyalty Point");
                user.setLoyaltyPoints((CustomerLoyalty - loyaltyPoints));
                return 0;
            }else{
                return -1;
            }
        }
        return -1;
    }
}