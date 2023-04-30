package DataUserClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import OrderClasses.Item;

public class Catalog {
    private String name;
    private Vector<Item> items;
    public Catalog(String name , Vector<Item> items) {
        this.name = name;
        this.items = items;
    }
    public Catalog(String name) {
        this.name = name;
        this.items = new Vector<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Catalog() {
        this.items = new Vector<>();
    }

    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    public Vector<Item> getItems() {
        return items;
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