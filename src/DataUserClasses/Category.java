package DataUserClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import OrderClasses.Item;
import SystemClasses.DataManager;

public class Category {
    private String name;
    private boolean isSealed;
    private Vector<Item> items;
    private DataManager DATA = new DataManager();
    public Category(String name, boolean isSealed, Vector<Item> items) {
        this.name = name;
        this.isSealed = isSealed;
        this.items = items;
    }

    public Category(String name ,boolean isSealed ) {
        this.name = name;
        this.isSealed = isSealed;
        this.items = new Vector<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setSealed(boolean isSealed) {
        this.isSealed = isSealed;
    }
    public boolean getSealed(){
        return isSealed;
    }
    public Category() {
        this.items = new Vector<>();
    }

    public void removeItem(Item item , DataManager Data) {
        DATA.loadCategories();
        Vector<Category> catalogs = DATA.getCategories();
        for (Category catalog : catalogs) {
            Vector<Item> items = catalog.getItems();
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getID() == item.getID()) {
                    items.remove(item);
                }
            }
        }
        DATA.setCategories(catalogs);
        DATA.updateCategories();
    }

    public Vector<Item> getItems() {
        return items;
    }

    public void setItems(Vector<Item> items) {
        this.items = items;
    }

    public void updateIteminCatalog(Item item , DataManager Data) {
        Vector<Category> catalogs = DATA.getCategories();
        for (Category catalog : catalogs) {
            Vector<Item> items = catalog.getItems();
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getID() == item.getID()) {
                    items.set(i, item);
                }
            }
        }
        DATA.setCategories(catalogs);
    }

    public boolean addItem(Item item) {
        items.add(item);
        return true;
    }

    public void displayCatalog() {
        System.out.println("----------------------------------------------------------------------------------------------- " + getName() + " -----------------------------------------------------------------------------------------------");
        int counter = 1;
        for (Item item : items) {
            System.out.print(counter++ + " - ");
            item.printItem(true, false);
            System.out.println("");
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public List<Item> searchItemsByName(String name) {
        DATA.loadItems();
        Vector<Item> itemss = DATA.getItems();
        List<Item> foundItems = new ArrayList<>();
        for (Item item : itemss) {
            if (item.getName().equalsIgnoreCase(name)) {
                foundItems.add(item);
            }
        }
        return foundItems;
    }
    public List<Item> searchItemsByBrand(String Brand) {
        DATA.loadItems();
        Vector<Item> itemss = DATA.getItems();
        List<Item> foundItems = new ArrayList<>();
        for (Item item : itemss) {
            if (item.getBrand().equalsIgnoreCase(Brand)) {
                foundItems.add(item);
            }
        }
        return foundItems;

    }
}