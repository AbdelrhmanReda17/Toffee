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

    /**
     * Constructs a new Category object with the given name, sealed status, and item list.
     * @param name the name of the category
     * @param isSealed the sealed status of the category
     * @param items the list of items in the category
     */
    public Category(String name, boolean isSealed, Vector<Item> items) {
        this.name = name;
        this.isSealed = isSealed;
        this.items = items;
    }

    /**
     * Creates a new Category object with the given name and sealed status.
     * @param name the name of the category
     * @param isSealed whether the category is sealed or not
     */
    public Category(String name ,boolean isSealed ) {
        this.name = name;
        this.isSealed = isSealed;
        this.items = new Vector<>();
    }

    /**
     * Constructor for an unsealed category with a given name.
     * @param name the name of the category
     */
    public Category(String name) {
        this.name=name;
    }

    /**
     * Getter method for the name of the category.
     * @return the name of the category
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for the name of the category.
     * @param name the new name for the category
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter method for the seal status of the category.
     * @param isSealed the new seal status for the category (true for sealed, false for unsealed)
     */

    public void setSealed(boolean isSealed) {
        this.isSealed = isSealed;
    }

    /**
     * Getter method for the seal status of the category.
     * @return the seal status of the category (true for sealed, false for unsealed)
     */

    public boolean getSealed(){
        return isSealed;
    }

    /**
     * Constructor for a category with an empty list of items.
     */
    public Category() {
        this.items = new Vector<>();
    }
    /**
     * Method for removing an item from the category's list of items.
     * @param item the item to be removed
     * @param Data the DataManager object used to load and update the list of categories
     */
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
    /**
     * Getter method for the list of items in the category.
     * @return the list of items in the category
     */
    public Vector<Item> getItems() {
        return items;
    }

    /**
     * Setter method for the list of items in the category.
     * @param items the new list of items for the category
     */
    public void setItems(Vector<Item> items) {
        this.items = items;
    }

    /**
     * Method for updating an item in the category's list of items.
     * @param item the item to be updated
     * @param Data the DataManager object used to load and update the list of categories
     */
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
    /**
     * Adds an item to the category.
     * @param item the item to add
     * @return true if the item was added successfully, false otherwise
     */

    public boolean addItem(Item item) {
        items.add(item);
        return true;
    }

    /**
     * Displays the category and its items in a formatted manner.
     */
    public void displayCategory() {
        System.out.println("----------------------------------------------------------------------------------------------- " + getName() + " -----------------------------------------------------------------------------------------------");
        int counter = 1;
        for (Item item : items) {
            System.out.print(counter++ + " - ");
            item.printItem(true, false);
            System.out.println("");
        }
    }
    /**
     * Searches the category's items by name.
     * @param name the name of the items to search for
     * @return a list of items matching the name
     */
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

    /**
     * Searches the category's items by brand.
     * @param Brand the brand of the items to search for
     * @return a list of items matching the brand
     */
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
    /**
     * Adds an item to the category and the item vector.
     * If the item's category does not exist, a new category is created.
     * @param items the item vector to add to
     * @param ct the category vector to add to
     */

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
            while(type != 1 && type != 2){
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

    /**
     * Edits an item's fields.
     * @param items the item vector containing the item to edit
     */
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

    /**
     * Removes an item from the inventory by its ID.
     * Prompts the user to enter the ID of the item they want to delete,
     * displays the item information, and asks for confirmation before removing it.
     * If the user confirms the deletion, the item is removed from the Vector of items,
     * the item is removed from the inventory file, and the inventory is updated.
     * @param items a Vector of Item objects representing the inventory
     */
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