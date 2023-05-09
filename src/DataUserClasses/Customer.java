package DataUserClasses;

import OrderClasses.Order;
import OrderClasses.ShoppingCart;
import java.util.Scanner;
import java.util.Vector;
import java.util.jar.Attributes.Name;

import SystemClasses.*;

public class Customer extends User {
    private String address;
    private String phone;
    private ShoppingCart shoppingCart = new ShoppingCart();
    private int loyaltyPoints;
    private boolean isSuspened = false;
    public Customer(String name, String password, String email, String phone, String address) {
        super(name, password , email);
        this.address = address;
        this.phone=phone;
        this.loyaltyPoints = 0;
        isSuspened = false;
    }
    public Customer(String name, String password, String email, String phone,  String address , int loyaltyPoints , boolean isSuspened) {
        super(name, password ,email) ;
        this.address = address;
        this.phone=phone;
        this.loyaltyPoints = loyaltyPoints;
        this.isSuspened = isSuspened;
    }
    public Customer(){}

    public Order reorder(DataManager Data) {
            Vector<Order> PrevOrders = LoadPrevOrderHistory(Data);
            System.out.println("Please select one of you past orders them and enter it's number : ");
            Scanner input = new Scanner(System.in);
            int OrderCho = input.nextInt();
            while(OrderCho > PrevOrders.size()){
                System.out.println("You Entered a invalid Order Number Please Enter it again: ");
                OrderCho = input.nextInt();
            }
            OrderCho = OrderCho-1;
            return PrevOrders.get(OrderCho);
    }
    public void DisplayPrevOrderHistory(DataManager Data){
            Vector<Order> prevOrders = this.LoadPrevOrderHistory(Data);
            if(prevOrders.isEmpty()){
                System.out.print("Customer Didn't have any orders");
            }
            for(int i = 0 ; i < prevOrders.size() ; i++){
                System.out.println("--------------------------------------------------------------------------- Order " + (i+1) + "-----------------------------------------------------------------------------");
                prevOrders.get(i).getShopcart().displayShoppingCart();
            }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
    public Vector<Order> LoadPrevOrderHistory(DataManager Data) {
        Vector<Order> prevOrders = new Vector<Order>();
        for(Order order : Data.getOrders()){
            if(order.getUser().getName().equals(this.getName())){
                prevOrders.add(order);
            }
        }
        return prevOrders;
    }
    public void displayCustomer(){
        System.out.println("Name: " + getName() + " || Email" + getEmail() + " || phone : "  + getPhone() +  " || Status : " + (getStatus() ? "Suspended" : "Not Suspended") );
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
    public boolean getStatus(){
        return isSuspened;
    }
    public void setStatus(boolean isSuspened){
        this.isSuspened = isSuspened ;
    }
    public ShoppingCart getShoppingCart(){ return shoppingCart;}
    public void setShoppingCart(ShoppingCart shoppingCart) {this.shoppingCart = shoppingCart;}
    
}