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
    public Catalog(DataManager data) {
        this.Data=data;

    }

    public Catalog(){

    };
    public void addSealedCategory(Category category){
        sealed.add(category);
    }
    public void addNSealedCategory(Category category){
        notsealed.add(category);
    }
    public int displayCatalogs(){
        System.out.println("----------------------------------------------------------------------------------- Catalogs -----------------------------------------------------------------------------------");
        System.out.println("1. Sealed Categories");
        System.out.println("2. Not Sealed Categories");
        System.out.println("Please enter the number of the catalog to view or enter 0 to exit:");
        Scanner scanner = new Scanner(System.in);
        int n  = scanner.nextInt();
        if(n == 0)
        return -1;
        while(n != 1 && n != 2){
            System.out.println("Wrong Choose , please enter only 1 or 2 or 0");
            n  = scanner.nextInt();
        }
        return n;
    }
    public void displaySealed(){
        if(sealed.size() == 0 ){
            System.out.println("Sealed Catalog is Empty");
        }else
        {
            for(Category cg : sealed){
                cg.displayCategory();
            }
        }
    }
    public void displayNSealed(){
        if(notsealed.isEmpty()){
            System.out.println("Not Sealed Catalog is Empty");
        }else{
            for(Category cg : notsealed){
                cg.displayCategory();
            }
        }
    }
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

    public Vector<Category> getSealedVector(){return sealed;}
    public Vector<Category> getNSealedVector(){return notsealed;}

}
