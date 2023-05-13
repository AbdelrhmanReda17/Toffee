package OrderClasses;

import DataUserClasses.Customer;
import PaymentClasses.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Represents an order placed by a customer.
 */
public class Order {
    private static int ORDERCount = 0;
    private int orderId = 0;
    private Customer user;
    private Order_state status;
    private ShoppingCart shopcart;
    private String shippingAddress;
    private Date ordertime;
    private PaymentMethod payment;

    /**
     * Default constructor for the Order class.
     */
    public Order() {
    }
    /**
     * Parameterized constructor for the Order class.
     * @param OrderID         The ID of the order.
     * @param user            The customer who placed the order.
     * @param status          The status of the order.
     * @param shopcart        The shopping cart containing the items in the order.
     * @param ordertime       The time the order was placed.
     * @param shippingAddress The shipping address for the order.
     * @param payment         The payment method used for the order.
     */
    public Order(int OrderID, Customer user, Order_state status, ShoppingCart shopcart, Date ordertime,
        String shippingAddress, PaymentMethod payment) {
        this.orderId = OrderID;
        this.user = user;
        this.status = status;
        this.shopcart = shopcart;
        this.shippingAddress = shippingAddress;
        this.payment = payment;
        this.ordertime = ordertime;
        ORDERCount = OrderID;
    }
    public Order(Order order) {
        this.orderId = ++ORDERCount;
        this.user = order.user;
        this.status = order.status;
        this.shopcart = order.shopcart;
        this.ordertime = order.ordertime;
        this.shippingAddress = order.shippingAddress;
        this.payment = order.payment;
    }
    
    /**
     * Parameterized constructor for the Order class.
     * Auto-generates the order ID.
     * @param user            The customer who placed the order.
     * @param status          The status of the order.
     * @param shopcart        The shopping cart containing the items in the order.
     * @param ordertime       The time the order was placed.
     * @param shippingAddress The shipping address for the order.
     * @param payment         The payment method used for the order.
     */
    public Order(Customer user, Order_state status, ShoppingCart shopcart, Date ordertime, String shippingAddress,PaymentMethod payment) {
        this.orderId = ++ORDERCount;
        this.user = user;
        this.status = status;
        this.shopcart = shopcart;
        this.shippingAddress = shippingAddress;
        this.payment = payment;
        this.ordertime = ordertime;
    }

    /**
     * Gets the order time.
     * If it is an order, adds one day to the current time.
     * @param isOrder Determines if it is an order or not.
     * @return The order time.
     */
    public Date getOrderTime(boolean isOrder) {
        Calendar calendar = Calendar.getInstance();
        if (isOrder) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        Date ordertime = calendar.getTime();
        return ordertime;
    }

    /**
     * Formats the order time as a string.
     * @return The formatted order time string.
     */
    public String formatOrderTime() {
        Date orderTime = getOrderTime(true);
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");
        String formattedDate = dateFormat.format(orderTime);
        return formattedDate;
    }

    /**
     * Gets the ID of the order.
     * @return The order ID.
     */
    public int getOrderId() {
        return orderId;
    }

   /**
     * Gets the customer who placed the order.
     * @return The customer who placed the order.
     */
    public Customer getUser() {
        return user;
    }

    /**
     * Gets the status of the order.
     * @return The status of the order.
     */
    public Order_state getStatus() {
        return status;
    }

    /**
     * Sets the status of the order.
     * @param status The new status of the order.
     */
    public void setStatus(Order_state status) {
        this.status = status;
    }

    /**
     * Gets the shopping cart containing the items in the order.
     * Updates the shopping cart for the associated user.
     * @return The shopping cart.
     */
    public ShoppingCart getShopcart() {
        user.setShoppingCart(shopcart);
        return shopcart;
    }

    /**
     * Sets the shopping cart for the order.
     * @param shopcart The shopping cart to set.
     */
    public void setShopcart(ShoppingCart shopcart) {
        this.shopcart = shopcart;
    }

    /**
     * Gets the shipping address for the order.
     * @return The shipping address.
     */
    public String getShippingAddress() {
        return shippingAddress;
    }

    /**
     * Gets the order time.
     * @return The order time.
     */
    public Date getOrdertime() {
        return ordertime;
    }

    /**
     * Gets the payment method used for the order.
     * @return The payment method.
     */
    public PaymentMethod getPayment() {
        return payment;
    }

    /**
     * Sets the order time.
     * @param orderTime The order time to set.
     */
    public void setOrderTime(Date orderTime) {
        this.ordertime = orderTime;
    }

    /**
     * Sets the shipping address for the order.
     * @param shippingAddress The shipping address to set.
     */
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    /**
     * Sets the payment method for the order.
     * @param payment The payment method to set.
     */
    public void setPayment(PaymentMethod payment) {
        this.payment = payment;
    }

}