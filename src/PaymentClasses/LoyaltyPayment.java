package PaymentClasses;

public class LoyaltyPayment extends PaymentMethod {
    private String methodName = "Loyalty Payment";


    public String getMethod() {
        return methodName;
    }
    public boolean processPayment(String phoneNumber,double total_price) {
        return true; // Placeholder return statement
    }

    // Implement other methods and attributes specific to LoyaltyPayment
}