package PaymentClasses;

public abstract class PaymentMethod {
    protected double amount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public abstract boolean processPayment();
}