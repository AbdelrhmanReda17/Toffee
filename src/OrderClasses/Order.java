package OrderClasses;

import DataUserClasses.Customer;
import PaymentClasses.*;
import SystemClasses.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import javax.xml.crypto.Data;


public class Order {
    private static int ORDERCount = 0;
    private int orderId = 0;
    private Customer user;
    private Order_state status;
    private ShoppingCart shopcart;
    private String shippingAddress;
    private Date ordertime;
    private PaymentMethod payment;

    public Order() {
    }

    public Order(int OrderID ,Customer user, Order_state status, ShoppingCart shopcart ,Date ordertime, String shippingAddress, PaymentMethod payment) {
        this.orderId = OrderID;
        this.user = user;
        this.status = status;
        this.shopcart = shopcart;
        this.shippingAddress = shippingAddress;
        this.payment = payment;
        this.ordertime = ordertime;
        ORDERCount = OrderID;
    }
    public Order(Customer user, Order_state status, ShoppingCart shopcart,Date ordertime, String shippingAddress, PaymentMethod payment) {
        this.orderId = ++ORDERCount;
        this.user = user;
        this.status = status;
        this.shopcart = shopcart;
        this.shippingAddress = shippingAddress;
        this.payment = payment;
        this.ordertime = ordertime;
    }
    public Date getOrderTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date ordertime = calendar.getTime();
        return ordertime;
    }
    public String formatOrderTime() {
        Date orderTime = getOrderTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");
        String formattedDate = dateFormat.format(orderTime);
        return formattedDate;
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

    public Order_state setStatus(Order_state status) {
        this.status = status;
        return status;
    }

    public ShoppingCart getShopcart() {
        return shopcart;
    }
    public void setShopcart(ShoppingCart shopcart) {
        this.shopcart = shopcart;
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
    public void setOrderTime(Date orderTime){this.ordertime = orderTime;}
    public void setShippingAddress(String shippingAddress){this.shippingAddress = shippingAddress;}
    public void setPayment(PaymentMethod payment){this.payment = payment;}

}