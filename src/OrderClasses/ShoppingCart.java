package OrderClasses;

import java.util.ArrayList;
import java.util.List;

import SystemClasses.DataManager;
import SystemClasses.LoyaltyPoints;

public class ShoppingCart {
    private double totalCost;
    private int loyaltyPoints;
    private List<CartItem> cartItems;
    private int pointsEarned;
    private LoyaltyPoints LoyaltyScheme; 
    private DataManager Data = new DataManager();

    public ShoppingCart() {
        this.totalCost = 0.0;
        this.loyaltyPoints = 0;
        this.pointsEarned=0;
        this.cartItems = new ArrayList<>();
        Data.loadLoyaltyScheme();
        LoyaltyScheme = Data.getLoyaltyScheme();
    }

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
        totalCost += ((item.getPrice()-item.getPrice()*(item.getDiscountPercentage()/100)));
        loyaltyPoints += item.getPoints();
        if(pointsEarned < LoyaltyScheme.getMaximumpoint()){
            pointsEarned += (item.getPrice() * LoyaltyScheme.getPointsEarnedperEgp());
        }else{
            pointsEarned = LoyaltyScheme.getMaximumpoint();
        }
    }

    public void removeCartItem(CartItem item) {
        cartItems.remove(item);
        totalCost -=  ((item.getPrice()-item.getPrice()*(item.getDiscountPercentage()/100)));
        loyaltyPoints -= item.getPoints();
        pointsEarned -= (item.getPrice()*LoyaltyScheme.getPointsEarnedperEgp());
    }

    public void displayShoppingCart() {
        System.out.print("Total Points Earned: "+pointsEarned+"  ||  "+"Total Cost: " + totalCost+"  ||  " + "Total Points Cost: " + loyaltyPoints + "\n");
        for(CartItem d : cartItems){
                d.displayCartItem();
        }
    }
    public boolean updateCartItem(){
        return true;
    }
    public void clearCart() {
        cartItems.clear();
        totalCost = 0.0;
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
    public int getPointsEarned() {
        return pointsEarned;
    }
    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
