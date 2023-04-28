package DataUserClasses;
import java.util.ArrayList;
import java.util.List;
import OrderClasses.Item;

public class Catalog {
    private List<Item> items;

    public Catalog() {
        this.items = new ArrayList<>();
    }

    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    public boolean updateItem(Item item) {
        // Update logic goes here
        // You can update the details of the item in the catalog
        return true; // Placeholder return statement
    }

    public boolean addItem(Item item) {
        return items.add(item);
    }

    public List<Item> viewItems() {
        return items;
    }

    public List<Item> searchItemsByName(String name) {
        List<Item> foundItems = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                foundItems.add(item);
            }
        }
        return foundItems;
    }

    public List<Item> searchItemsByCategory(String category) {
        List<Item> foundItems = new ArrayList<>();
        for (Item item : items) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                foundItems.add(item);
            }
        }
        return foundItems;
    }
}
