package DataUserClasses;

import ControllerClasses.OrderController;
import ControllerClasses.UserController;
import OrderClasses.Item;

import java.util.Vector;
import java.util.Scanner;
import SystemClasses.DataManager;

public class Catalog{
    private Vector<Category> sealed = new Vector<>();
    private Vector<Category> notsealed = new Vector<>();

    private DataManager Data ;


    /**
     * Creates a new Catalog with the given DataManager.
     * @param data the DataManager object to use
     */
    public Catalog(DataManager data) {
        this.Data = data;
    }

    /**
     * Creates a new empty Catalog.
     */
    public Catalog() {}

    /**
     * Adds the given Category to the sealed catalog.
     * @param category the Category to add
     */
    public void addSealedCategory(Category category) {
        sealed.add(category);
    }

    /**
     * Adds the given Category to the not sealed catalog.
     * @param category the Category to add
     */
    public void addNSealedCategory(Category category) {
        notsealed.add(category);
    }

    /**
     * Displays a list of catalogs and prompts the user to choose one to view.
     * @return the number of the catalog to view, or -1 to exit
     */
    public int displayCatalogs() {
        System.out.println("----------------------------------------------------------------------------------- Catalogs -----------------------------------------------------------------------------------");
        System.out.println("1. Sealed Categories");
        System.out.println("2. Not Sealed Categories");
        System.out.println("Please enter the number of the catalog to view or enter 0 to exit:");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n == 0)
            return -1;
        while (n != 1 && n != 2) {
            System.out.println("Wrong Choose, please enter only 1 or 2 or 0");
            n = scanner.nextInt();
        }
        return n;
    }

    /**
     * Displays all categories in the sealed catalog.
     */
    public void displaySealed() {
        if (sealed.size() == 0) {
            System.out.println("Sealed Catalog is Empty");
        } else {
            for (Category cg : sealed) {
                cg.displayCategory();
            }
        }
    }

    /**
     * Displays all categories in the not sealed catalog.
     */
    public void displayNSealed() {
        if (notsealed.isEmpty()) {
            System.out.println("Not Sealed Catalog is Empty");
        } else {
            for (Category cg : notsealed) {
                cg.displayCategory();
            }
        }
    }

    /**
     * Adds a new category to the catalog.
     * @param ct the vector of Category objects representing the current categories
     * @param itemM the vector of Item objects representing the current items
     */

    public void addNewCategory(Vector<Category>ct,Vector<Item>itemM) {
        System.out.print("Enter The Category Name : ");
        String name = new Scanner(System.in).nextLine();
        for (Category x : ct) {
            if (x.getName() == name) {
                System.out.println("The Catalog Name is Already Exist !");
            }
        }
        Category NewCatalog = new Category(name);
        ct.add(NewCatalog);
        System.out.print("Press 1.Add New Item , 2.Add Existing Item, 3.No Need To Add Item : ");
        int choice = new Scanner(System.in).nextInt();
        if(choice == 1){
            System.out.println("You Must Add The Item First ! ");
            Item item = new Item();
            item.getItem();
            itemM.add(item);
            Data.setItems(itemM);
            NewCatalog.addItem(item);
        }else if(choice == 2){
            System.out.print("Enter Item ID : ");
            int id = new Scanner(System.in).nextInt();
            Vector<Item> it = Data.getItems();
            for (Item item : it) {
                if (id == item.getID()) {
                    item.setCategory(name);
                    NewCatalog.addItem(item);
                }
            }
        }
        System.out.println("Category added successfully!!");
        Data.setCategories(ct);

    }

    /**
     * Removes a category from the list of categories.
     * @param catalogs A vector containing all the available categories.
     */
    public void removeCategory(Vector<Category> catalogs) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the Name of the category you want to remove: ");
        String catalogName = scanner.nextLine();
        Category ToRemove = null;
        for (Category catalog : catalogs) {
            if (catalog.getName().equals(catalogName)) {
                ToRemove = catalog;
                break;
            }
        }

        if (ToRemove != null) {
            catalogs.remove(ToRemove);
            System.out.println("Category Deleted Successfully");
        }

        Data.setCategories(catalogs);
    }

    /**
     * Returns a sealed vector containing all categories that have been marked as sealed.
     * @return a vector containing all sealed categories
     */
    public Vector<Category> getSealedVector() {
        return sealed;
    }

    /**
     * Returns a not sealed vector containing all categories that have not been marked as sealed.
     * @return a vector containing all not sealed categories
     */
    public Vector<Category> getNSealedVector() {
        return notsealed;
    }

}
