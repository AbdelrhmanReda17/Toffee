package PaymentClasses;

public class CreditPayment extends PaymentMethod {
    private String cardNumber;
    private String expDate;
    private int cvv;

    public boolean processPayment() {
        // Add logic to process credit card payment
        // Return true if the payment is successful, false otherwise
        return true; // Placeholder return statement
    }

    // Implement other methods and attributes specific to CreditPayment
}