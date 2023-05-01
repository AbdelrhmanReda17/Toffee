package OrderClasses;

import DataUserClasses.Customer;
import SystemClasses.*;
import PaymentClasses.PaymentMethod;
import java.util.Date;
import java.util.List;

public class Order {
    private int ORDERCount = 0;
    private int orderId = 0;
    private Customer user;
    private Order_state status;
    private ShoppingCart shopcart;
    private String shippingAddress;
    private Date ordertime;
    private String phoneNo;
    private List<GiftVoucher> giftVouchers;
    private int loyaltyPoints;
    private double totalCost;
    private PaymentMethod payment;
    
    public Order(int orderId, Customer user, Order_state status, ShoppingCart shopcart, String shippingAddress, PaymentMethod payment) {
        this.orderId = orderId;
        this.user = user;
        this.status = status;
        this.shopcart = shopcart;
        this.shippingAddress = shippingAddress;
        this.payment = payment;
        this.ordertime = new Date();
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
        // Logic to place the order
        return true; // Placeholder return statement
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public List<GiftVoucher> getGiftVouchers() {
        return giftVouchers;
    }

    public void setGiftVouchers(List<GiftVoucher> giftVouchers) {
        this.giftVouchers = giftVouchers;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public PaymentMethod getPayment() {
        return payment;
    }
}