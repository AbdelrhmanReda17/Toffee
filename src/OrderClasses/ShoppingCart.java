package OrderClasses;

import java.util.*;

import SystemClasses.DataManager;
import SystemClasses.LoyaltyPoints;

/**
 * Represents a shopping cart that allows adding, removing, and updating cart items.
 */
public class ShoppingCart {
    private double totalCost;
    private int loyaltyPoints;
    private List<CartItem> cartItems;
    private int pointsEarned;
    private LoyaltyPoints LoyaltyScheme; 
    private DataManager Data = new DataManager();
/**
     * Initializes a new instance of the ShoppingCart class.
     * Loads the loyalty scheme from the data manager.
     */
    public ShoppingCart() {
        this.totalCost = 0.0;
        this.loyaltyPoints = 0;
        this.pointsEarned=0;
        this.cartItems = new ArrayList<>();
        Data.loadLoyaltyScheme();
        LoyaltyScheme = Data.getLoyaltyScheme();
    }

 /**
     * Adds a cart item to the shopping cart.
     * @param item The cart item to add.
     */
    public void addCartItem(CartItem item) {
            int index = -1;
            for(int i = 0 ; i < cartItems.size() ; i++){
                if(cartItems.get(i).getID() == item.getID()){
                    index = i;
                }
            }
            if(index == -1){
                cartItems.add(item);
            }else{
                cartItems.get(index).setQuantity(cartItems.get(index).getQuantity()+1);
            }
            totalCost += ((item.getPrice()-item.getPrice()*(item.getDiscountPercentage()/100)) * item.getQuantity());
            loyaltyPoints += (item.getPoints() *item.getQuantity());
            if(pointsEarned < LoyaltyScheme.getMaximumpoint()){
                pointsEarned += (item.getPrice() * LoyaltyScheme.getPointsEarnedperEgp() *item.getQuantity());
            }else{
                pointsEarned = LoyaltyScheme.getMaximumpoint();
            }
    }

     /**
     * Removes a cart item from the shopping cart.
     * @param item The cart item to remove.
     */
    public void removeCartItem(CartItem item) {
        cartItems.remove(item);
        totalCost -=  ((item.getPrice()-item.getPrice()*(item.getDiscountPercentage()/100)) *item.getQuantity());
        loyaltyPoints -= item.getPoints() * item.getQuantity();
        pointsEarned -= (item.getPrice()*LoyaltyScheme.getPointsEarnedperEgp() * item.getQuantity()) ;
    }
    /**
     * Displays the shopping cart with total points earned, total cost, and individual cart items.
     */
    public void displayShoppingCart() {
        System.out.print("Total Points Earned: "+pointsEarned+"  ||  "+"Total Cost: " + totalCost+"  ||  " + "Total Points Cost: " + loyaltyPoints + "\n");
        for(CartItem d : cartItems){
                d.displayCartItem();
        }
    }
    
/**
Updates a cart item in the shopping cart.
Allows discarding an item or updating its quantity.
*/
    public void updateCartItem(Vector<Item> items) {
        Scanner scanner = new Scanner(System.in);

        int choice =0;
        while (true) {
            System.out.println("What Do You Want To Update ?");
            System.out.println("1 : Discard item");
            System.out.println("2 : Update item quantity");
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1 || choice == 2) {
                break;
            }
            System.out.println("OPPS ! Invalid Choice , Please Re-enter your choice");
        }
        if(choice == 1){
            System.out.println("Enter the name of the item you want to Discard: ");
            String namee = new Scanner(System.in).nextLine();
            for (CartItem cartItem : cartItems) {
                if (Objects.equals(cartItem.getName(), namee)) {
                   removeCartItem(cartItem);
                   break;

                }}

        }else {
            Boolean isFound = false;
            System.out.println("Enter the name of the item you want to update its Quantity: ");
            String name = scanner.nextLine();
            for (CartItem cartItem : cartItems) {
                if (Objects.equals(cartItem.getName(), name)) {
                    isFound = true;
                }
            }
            if(isFound){
                int quantity = 0;
                boolean isexist = false;
                while (true) {
                    System.out.println("Enter the Quantity you want -maximum 50-: ");
                    quantity = scanner.nextInt();
                    if (quantity <= 50) {
                        for(Item i : items){
                            if(i.getName().equals(name)){
                                if(quantity <= i.getQuan()){
                                    isexist = true;
                                    break;
                                }else{
                                    System.out.println("Your Needed Quantity are not avalible now , we have : "+ i.getQuan() + " Only in Stock");
                                }
                            }
                        }
                    }else{
                        System.out.println("Sorry , The Maximum Quantity You Can get per Item is 50 !!");
                    }
    
                    if(isexist){
                        break;
                    }
                }
                for (CartItem cartItem : cartItems) {
                    if (Objects.equals(cartItem.getName(), name)) {
                        int oldQuantity = cartItem.getQuantity();
                        double oldCost = (cartItem.getPrice() - cartItem.getPrice() * (cartItem.getDiscountPercentage() / 100))
                                * oldQuantity;
                        cartItem.setQuantity(quantity);
                        double newCost = (cartItem.getPrice() - cartItem.getPrice() * (cartItem.getDiscountPercentage() / 100))
                                * quantity;
                        totalCost += newCost - oldCost;
                        loyaltyPoints += cartItem.getPoints() * (quantity - oldQuantity);
                        pointsEarned += (cartItem.getPrice() * LoyaltyScheme.getPointsEarnedperEgp())
                                * (quantity - oldQuantity);
                        if (pointsEarned > LoyaltyScheme.getMaximumpoint()) {
                            pointsEarned = LoyaltyScheme.getMaximumpoint();
                        }
                    }
                }
                System.out.println("Item Updated Successfully");
                displayShoppingCart();
            }else{
                System.out.println("Item not found ");
            }
        }
    }
 
    /**
     * Clears the shopping cart by removing all cart items and resetting the total cost to zero.
    */
public void clearCart() {
    cartItems.clear();
    totalCost = 0.0;
    }
    /**
     * Retrieves the list of cart items in the shopping cart.
     * @return The list of cart items.
    */
    public List<CartItem> getCartItems() {
    return cartItems;
    }
    /**
     * Retrieves the total cost of the items in the shopping cart.
     * @return The total cost.
    */
    public double getTotalCost() {
    return totalCost;
    }
    /**
     * Retrieves the loyalty points accumulated in the shopping cart.
     * @return The loyalty points.
    */
    public int getLoyaltyPoints() {
    return loyaltyPoints;
    }
    /** 
     * Retrieves the total points earned in the shopping cart.
     * @return The total points earned.
    */
    public int getPointsEarned() {
    return pointsEarned;
    }
    /**
     * Sets the loyalty points in the shopping cart.
     * @param loyaltyPoints The loyalty points to set.
    */
    public void setLoyaltyPoints(int loyaltyPoints) {
    this.loyaltyPoints = loyaltyPoints;
    }
    /**
     * Sets the total cost of the items in the shopping cart.
     * @param totalCost The total cost to set.
    */
    public void setTotalCost(double totalCost) {
    this.totalCost = totalCost;
    }
}
