package OrderClasses;
import java.util.Scanner;

/**
 * Represents an item in a shopping catalog.
 */
public class Item {
    public int counter = 0;
    private int ID ;
    private String name;
    private String category;
    private String description;
    private String brand;
    private double price;
    private double discountPercentage;
    private int points;
    private String image;
    private int quantity;
    
    /**
     * Default constructor for the Item class.
     * Creates an empty Item object.
     */
    public Item(){};
    /**
     * Constructs an Item object with the specified properties.
     * @param ID                 The ID of the item.
     * @param name               The name of the item.
     * @param category           The category of the item.
     * @param description        The description of the item.
     * @param brand              The brand of the item.
     * @param price              The price of the item.
     * @param discountPercentage The discount percentage of the item.
     * @param points             The loyalty points awarded for purchasing the item.
     * @param image              The image URL of the item.
     * @param quantity           The quantity of the item in stock.
     */
    public Item(int ID ,String name, String category, String description , String brand, double price, double discountPercentage, int points, String image , int quantity) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.discountPercentage = discountPercentage;
        this.points = points;
        this.image = image;
    }
    /**
     * Sets the properties of the item based on the specified Item object.
     * @param item The Item object containing the new property values.
     */
    public void setItem(Item item) {
        setID(item.getID());
        setName(item.getName());
        setCategory(item.getCategory());
        setDescription(item.getDescription());
        setBrand(item.getBrand());
        setPrice(item.getPrice());
        setDiscountPercentage(item.getDiscountPercentage());
        setPoints(item.getPoints());
        setImage(item.getImage());
        setQuantity(item.getQuantity());
    }

    /**
     * Prompts the user to enter the details of the item and sets the properties accordingly.
     */
    public String getName() {
        return name;
    }
    /**
     * Prompts the user to enter the details of the item and sets the properties accordingly.
     */
    public void getItem(){
       // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        // Read and store item ID
        System.out.print("Enter item ID: ");
        int id = scanner.nextInt();
        this.ID = id;
        scanner.nextLine();

        // Read and store item name
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        this.name = name;

        // Read and store item category
        System.out.print("Enter item category: ");
        String category = scanner.nextLine();
        this.category = category;

        // Read and store item description
        System.out.print("Enter item description: ");
        String description = scanner.nextLine();
        this.description = description;

        // Read and store item brand
        System.out.print("Enter item brand: ");
        String brand = scanner.nextLine();
        this.brand = brand;

        // Read and store item price
        System.out.print("Enter item price: ");
        double price = scanner.nextDouble();
        this.price = price;
        scanner.nextLine();

        // Read and store item discount percentage
        System.out.print("Enter item discount percentage: ");
        double discountPercentage = scanner.nextDouble();
        this.discountPercentage = discountPercentage;
        // Read and store item points
        scanner.nextLine();

        System.out.print("Enter item points: ");
        int points = scanner.nextInt();
        this.points = points;
        // Read and store item image
        scanner.nextLine();

        System.out.print("Enter item image: ");
        String image = scanner.nextLine();
        this.image = image;

        // Read and store item quantity
        System.out.print("Enter item quantity: ");
        int quantity = scanner.nextInt();
        this.quantity = quantity;
    }
        /**
     * Prints the details of the item.
     * @param isCustomer  Indicates whether the caller is a customer.
     * @param isCartItem  Indicates whether the item is in the cart.
     */
    public void printItem(boolean IsCustomer , boolean isCartItem){
        System.out.print("ID: " + getID()+ " || ");
        System.out.print("Name: " + getName() + " || ");
        if(isCartItem)
            System.out.print("Category: " + getCategory()+ " || ");
        if(!isCartItem){
            System.out.print("Description: " + getDescription()+ " || ");
            System.out.print("Brand: " + getBrand()+ " || ");
        }
        if(getDiscountPercentage() > 1){
            System.out.print("Discount percentage: " + getDiscountPercentage()+ "% || ");
            System.out.print("Price Before : " + getPrice() + "L.E || ");
            System.out.print("Price After  : " + (getPrice()-getPrice()*(getDiscountPercentage()/100)) + "L.E || ");
        }
        else{
            System.out.print("Price: " + getPrice() + "L.E || ");
        }
        
        System.out.print("Points: " + getPoints());
        if(!IsCustomer)
        {
            System.out.print(" || "+ "Image: " + getImage()+ " || ");
            System.out.print("Quantity: " + getQuantity());
        }
    }
        
    /**
     * Returns the ID of the item.
     * @return The ID of the item.
     */
    public int getID() {
        return ID;
    }

    /**
     * Returns the category of the item.
     * @return The category of the item.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Returns the description of the item.
     * @return The description of the item.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the brand of the item.
     * @return The brand of the item.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Returns the price of the item.
     * @return The price of the item.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the discount percentage of the item.
     * @return The discount percentage of the item.
     */
    public double getDiscountPercentage() {
        return discountPercentage;
    }

    /**
     * Returns the points of the item.
     * @return The points of the item.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Returns the image of the item.
     * @return The image of the item.
     */
    public String getImage() {
        return image;
    }

    /**
     * Returns the quantity of the item.
     * @return The quantity of the item.
     */
    public int getQuantity() {
        return quantity;
    }


        /**
     * Sets the ID of the item.
     * @param ID The ID of the item.
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Sets the name of the item.
     * @param name The name of the item.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the category of the item.
     * @param category The category of the item.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Sets the description of the item.
     * @param description The description of the item.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the brand of the item.
     * @param brand The brand of the item.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Sets the price of the item.
     * @param price The price of the item.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the discount percentage of the item.
     * @param discountPercentage The discount percentage of the item.
     */
    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    /**
     * Sets the points of the item.
     * @param points The points of the item.
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Sets the image of the item.
     * @param image The image of the item.
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Sets the quantity of the item.
     * @param quantity The quantity of the item.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
