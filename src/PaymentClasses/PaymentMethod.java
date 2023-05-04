package PaymentClasses;
import DataUserClasses.Customer;
public abstract class PaymentMethod {
    protected double amount;

    PaymentMethod(){};
    public double getAmount() {
        return amount;
    }

    public abstract String getMethod();
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public abstract float processPayment(int CustomerLoyalty ,String phone,double total_price , int loyaltyPoints);
}