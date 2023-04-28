package OrderClasses;

public class Item {
    private String name;
    private String category;
    private String description;
    private String brand;
    private double price;
    private double discountPercentage;
    private boolean inStock;
    private int points;
    private String image;
    private int quantity;

    // public Item(String name, String category, String brand, double price, double discountPercentage, boolean inStock, int points, String image) {
    //     this.name = name;
    //     this.category = category;
    //     this.brand = brand;
    //     this.price = price;
    //     this.discountPercentage = discountPercentage;
    //     this.inStock = inStock;
    //     this.points = points;
    //     this.image = image;
    // }
    public String getName() {
        return name;
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

    public boolean getStock() {
        return inStock;
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
}
