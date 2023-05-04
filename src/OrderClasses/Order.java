package OrderClasses;

import DataUserClasses.Customer;
import PaymentClasses.*;
import SystemClasses.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Order {
    private int ORDERCount = 0;
    private int orderId = 0;
    private Customer user;
    private Order_state status;
    private ShoppingCart shopcart;
    private String shippingAddress;
    private Date ordertime;
    private PaymentMethod payment;
    private DataManager Data = new DataManager();

    public Order() {

    }

    public void Order(){};
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

    public boolean placeOrder( Customer user) {
        Data.loadOrders();
        float paymentSuccess;
        System.out.println("The Total Price of you items : " + user.getShoppingCart().getTotalCost());
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the shipping address: ");
        String shippingAddress = input.nextLine();
        System.out.print("Enter the phone number: ");
        String phoneNo = input.nextLine();

        System.out.println("Select a payment method:");
        System.out.println("1. Credit Card");
        System.out.println("2. Cash on Delivery");
        System.out.println("3. Using Loyalty Points");
        System.out.println("4. Using Gift voucher");
        int paymentMethodChoice = input.nextInt();
        if (paymentMethodChoice == 1) {
            payment = new CreditPayment();
        } else if (paymentMethodChoice == 2) {
            payment = new CashPayment();
        } else if(paymentMethodChoice == 3){
            payment = new LoyaltyPayment();
        }else if(paymentMethodChoice == 4){
            payment = new GiftPayment();
        }
        paymentSuccess = payment.processPayment(user.getShoppingCart().getTotalCost());
        if (paymentSuccess == -1 ) {
            System.out.println("Failed to process payment.");
            return false;
        }else if (paymentSuccess == 0){
            ordertime = getOrderTime();
            Order order = new Order(orderId++, user, Order_state.IN_PROGRESS, user.getShoppingCart(),ordertime,shippingAddress, payment);
            Data.setOrders(order);
            Data.updateOrders();
            System.out.println("Delivery Expected Time " + getOrderTime());
            System.out.println("Order placed successfully!");
            return true;
        }else{
            user.getShoppingCart().setTotalCost(paymentSuccess);
            return placeOrder(user);
        }
    }
    public Date getOrderTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 4);
        Date ordertime = calendar.getTime();
        return ordertime;
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