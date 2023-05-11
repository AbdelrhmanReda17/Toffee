package PaymentClasses;

import DataUserClasses.Customer;
import SystemClasses.DataManager;
import java.util.Scanner;

public class LoyaltyPayment extends PaymentMethod {
    private String methodName;

    /**
     * Constructs a new LoyaltyPayment object with the payment method name set to "Loyalty Payment".
     */
    public LoyaltyPayment() {
        this.methodName = "Loyalty Payment";
    }

    /**
     @return the name of the payment method
     */
    public String getMethod() {
        return methodName;
    }

    /**
     * Processes a payment where the customer uses their loyalty points to pay for an order.
     * @param Data a DataManager object used to access and modify data in the system
     * @param user the customer making the payment
     * @param Email the email address of the customer
     * @param phone the phone number of the customer
     * @param total_price the total price of the order
     * @return 0 if the payment is successful, -1 if the payment is not successful
     */
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