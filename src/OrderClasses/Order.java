package OrderClasses;

import DataUserClasses.Customer;
import PaymentClasses.*;
import SystemClasses.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Order {
    private static int ORDERCount = 0;
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
    public boolean placeOrder( Customer user) {
        Data.loadOrders();
        float paymentSuccess;
        System.out.println("The Total Price of you items : " + user.getShoppingCart().getTotalCost());
        Scanner input = new Scanner(System.in);
        String addressRegex = "^[a-zA-Z0-9\\s,-]*$";
        do {
            System.out.print("Enter address: ");
            shippingAddress =  new Scanner(System.in).nextLine();
            if (!shippingAddress.matches(addressRegex)) {
                System.out.println("Invalid address!! Addresses can consist of letters, numbers, [  ,_ ,- ]");
            }
        } while (!shippingAddress.matches(addressRegex));

        String phoneRegex = "^[0-9]{11}$";
        String phone;
        do {
            System.out.print("Enter phone number: ");
            phone =  new Scanner(System.in).nextLine();
            if (!phone.matches(phoneRegex)) {
                System.out.println("Invalid phone number !! it must only numbers and 11 digit");
            }
        } while (!phone.matches(phoneRegex));

        while(true){
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
            paymentSuccess = payment.processPayment(user.getLoyaltyPoints(),phone,user.getShoppingCart().getTotalCost() , user.getShoppingCart().getLoyaltyPoints());
            if (paymentSuccess == -1 ) {
                System.out.println("Failed to process payment , please Select anthor payment");
            }else{
                if(payment.getMethod() == "Loyalty Payment"){
                    int loyaltyPoints = (int) Math.round(paymentSuccess);
                    user.setLoyaltyPoints(loyaltyPoints);
                    Data.setCurrentCustomer(user);
                    Data.updateCustomers();
                    CreateOrder(user, Order_state.IN_PROGRESS, user.getShoppingCart(),shippingAddress, payment);
                    return true;
                }
                else{
                    if(paymentSuccess == 0){
                        CreateOrder(user, Order_state.IN_PROGRESS, user.getShoppingCart(),shippingAddress, payment);
                        return true;
                    }else
                    {
                        user.getShoppingCart().setTotalCost(paymentSuccess);
                        return placeOrder(user);
                    }
                }
            }
        }
        
    }
    private void CreateOrder(Customer user , Order_state status , ShoppingCart shoppingCart , String address , PaymentMethod payment){
        ordertime = getOrderTime();
        Order order = new Order(user, status, shoppingCart,ordertime,address, payment);
        Data.setOrders(order);
        Data.updateOrders();
        System.out.println("Delivery Expected Time " + formatOrderTime());
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

    public void setStatus(Order_state status) {
        this.status = status;
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

}