package OrderClasses;

public class CartItem extends Item{
    private int quantity;
    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public CartItem(Item item, int quantity) {
        super(item.getID(), item.getName(), item.getCategory(), item.getDescription(), item.getBrand(), item.getPrice(), item.getDiscountPercentage(), item.getPoints(), item.getImage(), item.getQuantity());
        this.quantity = quantity;
    }
    public void displayCartItem(){
        printItem(true , true);
        System.out.println(" || Quantity: " + quantity);
    }
    public CartItem convertToCartItem(Item item, int quantity) {
        CartItem cartItem = new CartItem(item, quantity);
        return cartItem;
    }
    public CartItem(){};
}