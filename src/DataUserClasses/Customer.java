package DataUserClasses;

import OrderClasses.Order;
import OrderClasses.ShoppingCart;
import OrderClasses.Order_state;

import java.util.Vector;
import SystemClasses.*;

public class Customer extends User {
    private String address;
    private String phone;
    private ShoppingCart shoppingCart;
    private int loyaltyPoints=0;

    public Customer(String name, String password, String phone, String address) {
        super(name, password);
        this.address = address;
        this.phone=phone;
    }
    // public Order getCurrentOrder() {
    //     for (Order order : dataManager.getOrders()) {
    //         if (order.getStatus().equals(Order_state.IN_PROGRESS) && order.getUser().equals(this)) {
    //             return order;
    //         }
    //     }
    //     return null;
    // }

    // public void placeOrder(Order order, String address) {
    //     // Logic to place an order with the given address
    // }

    // public Vector<Item> viewCatalog() {
    //     // Logic to view the catalog and return a vector of items
    //     return new Vector<Item>(); // Placeholder return statement
    // }

    // public boolean reorder(Order order) {
    //     // Logic to reorder a previous order
    //     return true; // Return true if successful, false otherwise
    // }

    // public Vector<Order> viewOrderHistory() {
    //     // Logic to view the order history and return a vector of orders
    //     return new Vector<>(); // Placeholder return statement
    // }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    // public Vector<Order> getPrevOrders() {
    //     return prevOrders;
    // }

    // public ShoppingCart getShoppingCart() {
    //     return shoppingCart;
    // }
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
}