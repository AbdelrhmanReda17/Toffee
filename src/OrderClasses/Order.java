package OrderClasses;

import DataUserClasses.Customer;
import PaymentClasses.*;
import SystemClasses.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner14;
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
    public boolean placeOrder(DataManager Data, Customer user) {
        float paymentSuccess;
        System.out.println("The Total Price of you items : " + user.getShoppingCart().getTotalCost() + "L.E");
        Scanner input = new Scanner(System.in);
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        String email;
        do {
            System.out.print("Enter Email : ");
            email =  new Scanner(System.in).nextLine();
            if (!email.matches(emailRegex)) {
                System.out.println("Invalid Email! It must be in the correct format.");
            }
        } while (!email.matches(emailRegex));

        String addressRegex = "^[a-zA-Z0-9\\s,-]*$";
        do {
            int choice=0;
            System.out.println("Select the Address:");
            System.out.println("1. Same Address");
            System.out.println("2. Another Address");
            choice = input.nextInt();
            if (choice==1){
                //Customer C = new Customer();
                shippingAddress = user.getAddress();
                System.out.println("Your order will be delivered to " + shippingAddress);
            }
            if(choice==2){
                System.out.println("Enter the address");
                shippingAddress =  new Scanner(System.in).nextLine();
            }
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
            boolean isValid = false;
            int paymentMethodChoice = 0;
            do{
                System.out.println("Select a payment method:");
                System.out.println("1. Credit Card");
                System.out.println("2. Cash on Delivery");
                System.out.println("3. Using Loyalty Points");
                System.out.println("4. Using Gift voucher");
                System.out.println("0. Back");
                paymentMethodChoice = input.nextInt();
                if (paymentMethodChoice == 1) {
                    isValid = true;
                    payment = new CreditPayment();
                } else if (paymentMethodChoice == 2) {
                    isValid = true;
                    payment = new CashPayment();
                } else if (paymentMethodChoice == 3) {
                    isValid = true;
                    payment = new LoyaltyPayment();
                } else if (paymentMethodChoice == 4) {
                    isValid = true;
                    payment = new GiftPayment();
                }else if (paymentMethodChoice ==0){
                    return false;
                }
                else{
                    System.out.println("Opps! your Choice Is Wrong , Please re-Enter it");
                    isValid = false;
                }
            }while (!isValid);
            paymentSuccess = payment.processPayment(Data,user , email,phone,user.getShoppingCart().getTotalCost());
            
            if (paymentSuccess == -1 ) {
                System.out.println("Failed to process payment , please Select another payment");
            }else{
                if(paymentSuccess == 0){
                    if(!(payment instanceof LoyaltyPayment)){
                        user.setLoyaltyPoints(user.getLoyaltyPoints() + user.getShoppingCart().getPointsEarned());
                        System.out.println("You Gained " + user.getShoppingCart().getPointsEarned() + " Loyalty Points Your Loyalty Points Balance updated to be " + (user.getLoyaltyPoints()));
                    }
                    Data.setCurrentCustomer(user);
                    CreateOrder(Data , user, Order_state.IN_PROGRESS, user.getShoppingCart(),shippingAddress, payment);
                    return true;
                }else
                {
                    user.getShoppingCart().setTotalCost(paymentSuccess);
                    return placeOrder(Data , user);
                }
            }
        }
    }
    private void CreateOrder(DataManager Data , Customer user , Order_state status , ShoppingCart shoppingCart , String address , PaymentMethod payment){
        ordertime = getOrderTime();
        Order order = new Order(user, status, shoppingCart,ordertime,address, payment);
        Data.setOrders(order);
        System.out.println("Delivery expected time: " + formatOrderTime() );
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

}