package DataUserClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import OrderClasses.Item;
import SystemClasses.DataManager;

public class Catalog {
    private String name;
    private Vector<Item> items;
    private DataManager DATA = new DataManager();

    public Catalog(String name, Vector<Item> items) {
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

    public void removeItem(Item item) {
        DATA.loadCatalogs();
        Vector<Catalog> catalogs = DATA.getCatalogs();
        for (Catalog catalog : catalogs) {
            Vector<Item> items = catalog.getItems();
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getID() == item.getID()) {
                    items.remove(item);
                }
            }
        }
        DATA.setCatalogs(catalogs);
        DATA.updateCatalogs();
    }

    public Vector<Item> getItems() {
        return items;
    }
    public void setItems(Vector<Item> items) {
        this.items = items;
    }
    public void updateIteminCatalog(Item item) {
        DATA.loadCatalogs();
        Vector<Catalog> catalogs = DATA.getCatalogs();
        for (Catalog catalog : catalogs) {
            Vector<Item> items = catalog.getItems();
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getID() == item.getID()) {
                    items.set(i, item);
                }
            }
        }
        DATA.setCatalogs(catalogs);
        DATA.updateCatalogs();
    }

    public boolean addItem(Item item) {
        items.add(item);
        return true;
    }

    public void displayCatalog() {
            System.out.println("Catalog: " + getName());
            int counter = 1;
            for (Item item : items) {
                System.out.println("ITEM " + counter++);
                item.printItem();
                System.out.println("");
            }
            System.out.println("");
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