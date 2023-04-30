package OrderClasses;

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
    public void setCounter(int counter) {
        this.counter = counter;
    }
    public String getName() {
        return name;
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
