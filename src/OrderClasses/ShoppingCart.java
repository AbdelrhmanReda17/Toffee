package OrderClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

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
        totalCost += ((item.getPrice()-item.getPrice()*(item.getDiscountPercentage()/100)) * item.getQuantity());
        loyaltyPoints += (item.getPoints() *item.getQuantity());
        if(pointsEarned < LoyaltyScheme.getMaximumpoint()){
            pointsEarned += (item.getPrice() * LoyaltyScheme.getPointsEarnedperEgp() *item.getQuantity());
        }else{
            pointsEarned = LoyaltyScheme.getMaximumpoint();
        }
    }

    public void removeCartItem(CartItem item) {
        cartItems.remove(item);
        totalCost -=  ((item.getPrice()-item.getPrice()*(item.getDiscountPercentage()/100)) *item.getQuantity());
        loyaltyPoints -= item.getPoints() * item.getQuantity();
        pointsEarned -= (item.getPrice()*LoyaltyScheme.getPointsEarnedperEgp() *item.getQuantity()) ;
    }

    public void displayShoppingCart() {
        System.out.print("Total Points Earned: "+pointsEarned+"  ||  "+"Total Cost: " + totalCost+"  ||  " + "Total Points Cost: " + loyaltyPoints + "\n");
        for(CartItem d : cartItems){
                d.displayCartItem();
        }
    }
    public void updateCartItem() {
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
            System.out.println("Enter the name of the item you want to update its Quantity: ");
            String name = scanner.nextLine();
            int quantity = 0;
            while (true) {
                System.out.println("Enter the Quantity you want -maximum 50-: ");
                quantity = scanner.nextInt();
                if (quantity <= 50) {
                    break;
                }
                System.out.println("quantity should not exceed 50!! ");
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
        }
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
