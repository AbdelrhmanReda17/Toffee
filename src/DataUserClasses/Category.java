package DataUserClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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


    public void displayCategory() {
        System.out.println("----------------------------------------------------------------------------------------------- " + getName() + " -----------------------------------------------------------------------------------------------");
        int counter = 1;
        for (Item item : items) {
            System.out.print(counter++ + " - ");
            item.printItem(true, false);
            System.out.println("");
        }
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

    public void addItem(Vector<Item> items,Vector<Category> ct) {
        Item item = new Item();
        item.getItem();
        items.add(item);
        boolean foundCategory = false;
        for (Category x : ct) {
            if (item.getCategory().equals(x.getName())) {
                x.addItem(item);
                foundCategory = true;
                break;
            }
        }

        if (!foundCategory) {
            Boolean isSealed = false;
            System.out.println("Due to no category for this item we will create one for you");
            System.out.println("Please let us now the catalog is it Sealed or no (1 for Sealed , 2 for not Sealed)");
            int type = new Scanner(System.in).nextInt();
            while(type != 1 || type != 2){
                System.out.println("You entered a wrong value please one 1 or 2 [ 1 -> Sealed , 2->Not sealed ]");
                type = new Scanner(System.in).nextInt();
            }
            if(type == 1)   isSealed = true;
            Category newCatalog = new Category(item.getCategory() , isSealed);
            newCatalog.addItem(item);
            ct.add(newCatalog);
            DATA.setCategories(ct);
        }
        DATA.setCategories(ct);
        System.out.println("Item added successfully");
        DATA.setItems(items);

    }


    public void editItem(Vector<Item> items) {
        System.out.print("Enter The id of The item you want to Edit: ");
        int id = new Scanner(System.in).nextInt();
        for (Item item : items) {
            if (item.getID() == id) {
                item.printItem(false , false);
                int choice = 0;
                while (choice != 10) {
                    System.out.println("\nWhich field do you want to edit? Choose from the following options:\n" +
                            "1. Name   2. Category   3. Description   4. Brand   5. Price\n" +
                            "6. Discount percentage  7. Points        8. Image   9. Quantity\n"
                            +"10. Save");
                    choice = new Scanner(System.in).nextInt();
                    switch (choice) {
                        case 1:
                            System.out.print("Enter the new name: ");
                            item.setName(new Scanner(System.in).nextLine());
                            break;
                        case 2:
                            System.out.print("Enter the new category: ");
                            item.setCategory(new Scanner(System.in).nextLine());
                            break;
                        case 3:
                            System.out.print("Enter the new description: ");
                            item.setDescription(new Scanner(System.in).nextLine());
                            break;
                        case 4:
                            System.out.print("Enter the new brand: ");
                            item.setBrand(new Scanner(System.in).nextLine());
                            break;
                        case 5:
                            System.out.print("Enter the new price: ");
                            item.setPrice(new Scanner(System.in).nextDouble());
                            break;
                        case 6:
                            System.out.print("Enter the new discount percentage: ");
                            item.setDiscountPercentage(new Scanner(System.in).nextDouble());
                            break;
                        case 7:
                            System.out.print("Enter the new points: ");
                            item.setPoints(new Scanner(System.in).nextInt());
                            break;
                        case 8:
                            System.out.print("Enter the new image: ");
                            item.setImage(new Scanner(System.in).nextLine());
                            break;
                        case 9:
                            System.out.print("Enter the new quantity: ");
                            item.setQuantity(new Scanner(System.in).nextInt());
                            break;
                        case 10:
                            break;
                        default:
                            System.out.println("Invalid choice");
                            break;
                    }
                }
                updateIteminCatalog(item ,DATA);
                System.out.println("Item Edited Successful");
                DATA.setItems(items);
                return;
            }
        }
        System.out.println("Item not found");
    }

    public void deleteItem(Vector<Item> items) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID of the item you want to delete: ");
        int id = scanner.nextInt();
        Item itemToRemove = null;
        boolean FoundItem = false;
        for (Item item : items) {
            if (item.getID() == id) {
                item.printItem(false , false);
                System.out.println();
                System.out.println("Do you want to delete this item? Press 1 to confirm, 2 to cancel.");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    itemToRemove = item;
                    FoundItem = true;
                } else {
                    System.out.println("Operation Cancelled.");
                    return;
                }
            }
        }
        if (itemToRemove != null) {
            if (FoundItem) {
                items.remove(itemToRemove);
                removeItem(itemToRemove , DATA);
                System.out.println("Item Delete Successfully");
            }
        }else{
            System.out.println("Item not found.");
        }
        DATA.setItems(items);
    }












}