package DataUserClasses;

import OrderClasses.Order;
import OrderClasses.ShoppingCart;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.plaf.synth.SynthPopupMenuUI;



public class Customer extends User {
    private String address;
    private String phone;
    private ShoppingCart shoppingCart = new ShoppingCart();
    private int loyaltyPoints;
    private boolean isSuspened = false;


    /**
     * Constructs a new customer with the given name, password, email, phone, and address.
     * @param name the name of the customer
     * @param password the password of the customer
     * @param email the email address of the customer
     * @param phone the phone number of the customer
     * @param address the home address of the customer
     */
    public Customer(String name, String password, String email, String phone, String address) {
        super(name, password, email);
        this.address = address;
        this.phone = phone;
        this.loyaltyPoints = 0;
        isSuspened = false;
    }
    /**
     * Constructs a new customer with the given name, password, email, phone, address, loyalty points, and suspension status.
     * @param name the name of the customer
     * @param password the password of the customer
     * @param email the email address of the customer
     * @param phone the phone number of the customer
     * @param address the home address of the customer
     * @param loyaltyPoints the number of loyalty points the customer has
     * @param isSuspened the suspension status of the customer
     */
    public Customer(String name, String password, String email, String phone, String address, int loyaltyPoints, boolean isSuspened) {
        super(name, password, email);
        this.address = address;
        this.phone = phone;
        this.loyaltyPoints = loyaltyPoints;
        this.isSuspened = isSuspened;
    }
    /**
     * Constructs an empty customer with default values for all fields.
     */
    public Customer() {}

    /**
     * Allows the customer to reorder one of their previous orders.
     * @param orders A vector of all the orders in the system.
     * @return The order that the customer selected to reorder.
     */
    public ShoppingCart reorder(Vector<Order>orders) {
        Vector<Order> PrevOrders = LoadPrevOrderHistory(orders);
        System.out.println("Please select one of your past orders them and enter it's number : ");
        Scanner input = new Scanner(System.in);
        int OrderCho = input.nextInt();
        while(OrderCho > PrevOrders.size()){
            System.out.println("You Entered a invalid Order Number Please Enter it again: ");
            OrderCho = input.nextInt();
        }
        OrderCho = OrderCho-1;
        ShoppingCart selectedCart = PrevOrders.get(OrderCho).getShopcart();
        ShoppingCart newCart = new ShoppingCart(selectedCart.getCartItems());
        return newCart;
    }

    /**
     * Displays the customer's previous order history.
     * @param orders A vector of all the orders in the system.
     * @return True if the customer has previous orders to display, false otherwise.
     */
    public boolean DisplayPrevOrderHistory(Vector<Order>orders){
        Vector<Order> prevOrders = this.LoadPrevOrderHistory(orders);
        if(prevOrders.isEmpty()){
            System.out.println("Customer Didn't have any orders");
            return false;
        }
        for(int i = 0 ; i < prevOrders.size() ; i++){
            System.out.println("--------------------------------------------------------------------------- Order " + (i+1) + "-----------------------------------------------------------------------------");
            prevOrders.get(i).getShopcart().displayShoppingCart();
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        return true;
    }

    /**
     * Loads the customer's previous order history.
     * @param orders A vector of all the orders in the system.
     * @return A vector of the customer's previous orders.
     */
    public Vector<Order> LoadPrevOrderHistory(Vector<Order> orders) {
        Vector<Order> prevOrders = new Vector<Order>();
        for(Order order : orders){
            if(order.getUser().getName().equals(this.getName())){
                prevOrders.add(order);
            }
        }
        return prevOrders;
    }

    /**
     * Displays customer information including name, email, phone and suspension status
     */
    public void displayCustomer(){
        System.out.println("Name: " + getName() + " || Email" + getEmail() + " || phone : "  + getPhone() +  " || Status : " + (getStatus() ? "Suspended" : "Not Suspended") );
    }

    /**
     * Returns the loyalty points of the customer
     * @return loyaltyPoints the loyalty points of the customer
     */
    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    /**
     * Returns the address of the customer
     * @return address the address of the customer
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the customer
     * @param address the new address to be set for the customer
     */

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the phone number of the customer
     * @return phone the phone number of the customer
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the customer
     * @param phone the new phone number to be set for the customer
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Sets the loyalty points of the customer
     * @param loyaltyPoints the new loyalty points to be set for the customer
     */
    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints= loyaltyPoints;
    }

    /**
     * Returns the suspension status of the customer
     * @return isSuspened true if customer is suspended, false otherwise
     */
    public boolean getStatus(){
        return isSuspened;
    }

    /**
     * Sets the suspension status of the customer
     * @param isSuspened the new suspension status to be set for the customer
     */
    public void setStatus(boolean isSuspened){
        this.isSuspened = isSuspened ;
    }
    /**
     * Returns the shopping cart of the customer
     * @return shoppingCart the shopping cart of the customer
     */
    public ShoppingCart getShoppingCart(){
        return shoppingCart;
    }
    /**
     * Sets the shopping cart of the customer
     * @param shoppingCart the new shopping cart to be set for the customer
     */
    public void setShoppingCart(ShoppingCart shoppingCart) {this.shoppingCart = shoppingCart;}

}