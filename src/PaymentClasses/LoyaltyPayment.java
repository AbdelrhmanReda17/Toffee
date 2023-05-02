package PaymentClasses;

public class LoyaltyPayment extends PaymentMethod {
    private String methodName;
    public LoyaltyPayment(){
        this.methodName = "Loyalty Payment";
    }
    public String getMethod() {
        return methodName;
    }
    public boolean processPayment() {
        // Add logic to process loyalty payment
        // Return true if the payment is successful, false otherwise
        return true; // Placeholder return statement
    }

    // Implement other methods and attributes specific to LoyaltyPayment
}