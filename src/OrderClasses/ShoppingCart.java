package OrderClasses;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private double totalCost;
    private int loyaltyPoints;
    private List<CartItem> cartItems;

    public ShoppingCart() {
        this.totalCost = 0.0;
        this.loyaltyPoints = 0;
        this.cartItems = new ArrayList<>();
    }

    public void addCartItem(CartItem item) {
        cartItems.add(item);
        totalCost += (item.getPrice() * item.getDiscountPercentage());
        loyaltyPoints += item.getPoints();
    }

    public void removeCartItem(CartItem item) {
        cartItems.remove(item);
        totalCost -= (item.getPrice() * item.getDiscountPercentage());
        loyaltyPoints -= item.getPoints();
    }

    public void updateCartItem() {
        // Update logic goes here
        // You can update the quantity, price, or any other details of the cart item
    }

    public void clearCart() {
        cartItems.clear();
        totalCost = 0.0;
    }

    public void printCartItems(){
        for(int i=0;i<cartItems.size();i++){
            System.out.println(" Name : " + cartItems.get(i).getName() +" Price : "+ cartItems.get(i).getPrice() + "EGP" + "Quantity : " +cartItems.get(i).getQuantity());
        }
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public double getTotalCost() {
        return totalCost;
    }
    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }
    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
