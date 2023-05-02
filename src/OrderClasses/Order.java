package OrderClasses;

import DataUserClasses.Customer;
import SystemClasses.*;
import PaymentClasses.PaymentMethod;
import java.util.Date;

public class Order {
    private int ORDERCount = 0;
    private int orderId = 0;
    private Customer user;
    private Order_state status;
    private ShoppingCart shopcart;
    private String shippingAddress;
    private Date ordertime;
    private PaymentMethod payment;
    
    public Order(int orderId, Customer user, Order_state status, ShoppingCart shopcart ,Date ordertime, String shippingAddress, PaymentMethod payment) {
        this.orderId = orderId;
        this.user = user;
        this.status = status;
        this.shopcart = shopcart;
        this.shippingAddress = shippingAddress;
        this.payment = payment;
        this.ordertime = ordertime;
        ORDERCount = orderId;
    }
    public Order(Customer user, Order_state status, ShoppingCart shopcart, String shippingAddress, PaymentMethod payment) {
        this.orderId = ORDERCount;
        this.user = user;
        this.status = status;
        this.shopcart = shopcart;
        this.shippingAddress = shippingAddress;
        this.payment = payment;
        this.ordertime = new Date();
        ORDERCount++;
    }

    public boolean placeOrder() {
        return true;
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getUser() {
        return user;
    }

    public Order_state getStatus() {
        return status;
    }

    public void setStatus(Order_state status) {
        this.status = status;
    }

    public ShoppingCart getShopcart() {
        return shopcart;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public PaymentMethod getPayment() {
        return payment;
    }
}