package PaymentClasses;
import DataUserClasses.Customer;
import SystemClasses.DataManager;
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

    public abstract float processPayment(DataManager Data,Customer user , String Email , String phone , double total_price );
}