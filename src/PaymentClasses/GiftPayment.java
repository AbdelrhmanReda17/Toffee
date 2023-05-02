package PaymentClasses;

public class GiftPayment extends PaymentMethod {
    private String methodName;
    private String voucherCode;
    public GiftPayment(){
        this.methodName = "Gift Voucher Payment";
    }
    public String getMethod() {
        return methodName;
    }
    public boolean processPayment() {
        // Add logic to process gift payment
        // Return true if the payment is successful, false otherwise
        return true; // Placeholder return statement
    }

    // Implement other methods and attributes specific to GiftPayment
}