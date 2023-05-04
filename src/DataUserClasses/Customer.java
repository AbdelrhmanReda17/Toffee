package DataUserClasses;

import OrderClasses.Order;
import OrderClasses.ShoppingCart;
import OrderClasses.Order_state;

import java.util.Vector;
import SystemClasses.*;

public class Customer extends User {
    private String address;
    private String phone;
    private ShoppingCart shoppingCart = new ShoppingCart();
    private int loyaltyPoints;
    private DataManager Data = new DataManager();

    public Customer(String name, String password, String phone, String address) {
        super(name, password);
        this.address = address;
        this.phone=phone;
        this.loyaltyPoints = 0;
    }
    public Customer(String name, String password, String phone,  String address , int loyaltyPoints) {
        super(name, password);
        this.address = address;
        this.phone=phone;
        this.loyaltyPoints = loyaltyPoints;
    }
    // public Order getCurrentOrder() {
    //     Data.loadOrders();
    //     for (Order order : Data.getOrders()) {
    //         if (order.getStatus().equals(Order_state.IN_PROGRESS) && order.getUser().equals(this)) {
    //             return order;
    //         }
    //     }
    //     return null;
    // }

    public boolean reorder(Order order) {
                             System.out.println("Please choose an option:");
                        System.out.println(" 1 : Update Cart Items Quantity");
                        System.out.println(" 2 : Place The Order");
                        System.out.println(" 3 : Clear Cart");
        return true;
    }
    public void DisplayPrevOrderHistory(){
            Vector<Order> prevOrders = this.LoadPrevOrderHistory();
            if(prevOrders.isEmpty()){
                System.out.print("Customer Didn't have any orders");
            }
            for(int i = 0 ; i < prevOrders.size() ; i++){
                System.out.println("--------------------------------------------------------Order - " + (i+1) + "--------------------------------------------------------");
                System.out.print( "OrderID: " + prevOrders.get(i).getOrderId() + "  ||  ");
                prevOrders.get(i).getShopcart().displayShoppingCart();
                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
            }
    }
    public Vector<Order> LoadPrevOrderHistory() {
        Vector<Order> prevOrders = new Vector<Order>();
        Data.loadOrders();
        for(Order order : Data.getOrders()){
            if(order.getUser().getName().equals(this.getName())){
                prevOrders.add(order);
            }
        }
        return prevOrders;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints= loyaltyPoints;
    }
    public ShoppingCart getShoppingCart(){ return shoppingCart;}
}