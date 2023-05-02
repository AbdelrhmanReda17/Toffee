package PaymentClasses;

public class LoyaltyPayment extends PaymentMethod {
    private String methodName;
    public LoyaltyPayment(){
        this.methodName = "Loyalty Payment";
    }
    public String getMethod() {
        return methodName;
    }
    public boolean processPayment(String phoneNumber,double total_price) {

        return false;
    }

    // Implement other methods and attributes specific to LoyaltyPayment
}