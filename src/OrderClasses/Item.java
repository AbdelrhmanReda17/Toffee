package OrderClasses;
import java.util.Scanner;
import SystemClasses.DataManager;

public class Item {
    private DataManager data;
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
    public Item(){};
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
    
    public void setCounter(int counter) {
        this.counter = counter;
    }
    public String getName() {
        return name;
    }
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
        scanner.close();
    }
    public void printItem(boolean IsCustomer){
        System.out.print("ID: " + getID()+ " || ");
        System.out.print("Name: " + getName() + " || ");
        System.out.print("Category: " + getCategory()+ " || ");
        System.out.print("Description: " + getDescription()+ " || ");
        System.out.print("Brand: " + getBrand()+ " || ");
        System.out.print("Price: " + getPrice()+ " || ");
        System.out.print("Discount percentage: " + getDiscountPercentage()+ " || ");
        System.out.print("Points: " + getPoints());
        if(!IsCustomer)
        {
            System.out.print(" || "+ "Image: " + getImage()+ " || ");
        System.out.print("Quantity: " + getQuantity());
        }
    }
    
    public int getID() {
        return ID;
    }
    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }


    public int getPoints() {
        return points;
    }

    public String getImage() {
        return image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setID(int ID) {this.ID = ID;}
    public void setName(String name) {this.name = name;}
    public void setCategory(String category) {this.category = category;}
    public void setDescription(String description) {this.description = description;}
    public void setBrand(String brand) {this.brand = brand;}
    public void setPrice(double price) {this.price = price;}
    public void setDiscountPercentage(double discountPercentage) {this.discountPercentage = discountPercentage;}
    public void setPoints(int points) {this.points = points;}
    public void setImage(String image) {this.image = image;}
    public void setQuantity(int quantity) {this.quantity = quantity;}

}
