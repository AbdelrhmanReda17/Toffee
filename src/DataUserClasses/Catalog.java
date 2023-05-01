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

    public boolean removeItem(Item item) {
        Vector<Catalog> catalogs = DATA.getCatalogs();
        for (Catalog catalog : catalogs) {
            Vector<Item> items = catalog.getItems();
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getID() == item.getID()) {
                    items.remove(item);
                    DATA.updateCatalogs();
                    return true;
                }
            }
        }
        return false;
    }

    public Vector<Item> getItems() {
        return items;
    }

    public boolean updateIteminCatalog(Item item) {
        Vector<Catalog> catalogs = DATA.getCatalogs();
        for (Catalog catalog : catalogs) {
            Vector<Item> items = catalog.getItems();
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getID() == item.getID()) {
                    items.set(i, item);
                    DATA.updateCatalogs();
                    return true;
                }
            }
        }
        return false;
    }



    public boolean addItem(Item item) {
        Vector<Catalog> catalogs = DATA.getCatalogs();
        for (Catalog catalog : catalogs) {
            Vector<Item> items = catalog.getItems();
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getCategory() == item.getCategory()) {
                    items.add(item);
                    DATA.updateCatalogs();
                    return true;
                }
            }
        }
        Catalog newCatalog = new Catalog();
        newCatalog.setName(item.getCategory());
        newCatalog.addItem(item);
        catalogs.add(newCatalog);
        DATA.updateCatalogs();
        return true;
    }

    public void displayCatalogs() {
        Vector<Catalog> catalogs = DATA.getCatalogs();
        for (Catalog catalog : catalogs) {
            System.out.println("Catalog: " + catalog.getName());
            Vector<Item> items = catalog.getItems();
            for (Item item : items) {
                System.out.println(item.getName() + "   "+"Price:" + item.getPrice());
            }
            System.out.println();
        }
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