package OrderClasses;

/**
 * Represents an item in a shopping cart.
 * Extends the Item class and includes additional methods and properties specific to the cart functionality.
 */
public class CartItem extends Item {
    private int quantity;

    /**
     * Constructs a CartItem object with the specified item and quantity.
     * @param item     The item to be added to the cart.
     * @param quantity The quantity of the item to be added.
     */
    public CartItem(Item item, int quantity) {
        super(item.getID(), item.getName(), item.getCategory(), item.getDescription(), item.getBrand(), item.getPrice(), item.getDiscountPercentage(), item.getPoints(), item.getImage(), item.getQuan());
        this.quantity = quantity;
    }

    /**
     * Returns the quantity of the cart item.
     * @return The quantity of the cart item.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the cart item.
     * @param quantity The new quantity of the cart item.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Displays the details of the cart item, including the item information and quantity.
     */
    public void displayCartItem() {
        printItem(true, true);
        System.out.println(" || Quantity: " + quantity);
    }

    /**
     * Converts an Item object to a CartItem object with the specified quantity.
     * @param item     The Item object to convert.
     * @param quantity The quantity of the cart item.
     * @return The converted CartItem object.
     */
    public static CartItem convertToCartItem(Item item, int quantity) {
        CartItem cartItem = new CartItem(item, quantity);
        return cartItem;
    }

    /**
     * Default constructor for the CartItem class.
     * Creates an empty CartItem object.
     */
    public CartItem() {}
}
