package PaymentClasses;

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

    public abstract float processPayment(double total_price);
}