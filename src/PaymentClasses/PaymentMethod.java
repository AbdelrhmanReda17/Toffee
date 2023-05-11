package PaymentClasses;
import DataUserClasses.Customer;
import SystemClasses.DataManager;
public abstract class PaymentMethod {
    protected double amount;
    /**
     * Default constructor for PaymentMethod.
     */
    PaymentMethod(){};

    /**
     * This method returns the name of the payment method.
     * @return A String containing the name of the payment method.
     */
    public abstract String getMethod();
    /**
     * This method processes the payment made by the user.
     * @param Data An instance of the DataManager class.
     * @param user An instance of the Customer class.
     * @param Email A String representing the email of the user.
     * @param phone A String representing the phone number of the user.
     * @param total_price A double representing the total price of the order made by the user.
     * @return A float value representing the payment status. If the payment is successful, it returns 0. If the payment fails, it returns -1.
     */
    public abstract float processPayment(DataManager Data,Customer user , String Email , String phone , double total_price );

}