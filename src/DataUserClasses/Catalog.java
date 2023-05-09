package DataUserClasses;

import java.util.Vector;
import java.util.Scanner;
public class Catalog{
    private Vector<Category> sealed = new Vector<>();
    private Vector<Category> notsealed = new Vector<>();
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
        Scanner scanner = new Scanner(System.in);
        int n  = scanner.nextInt();
        while(n != 1 && n != 2){
            System.out.println("Wrong Choose");
            n  = scanner.nextInt();
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        return n;
    }

    public void displaySealed(){
        for(Category cg : sealed){
            cg.displayCatalog();
        }
    }
    public void displayNSealed(){
        for(Category cg : notsealed){
            cg.displayCatalog();
        }
    }

    public Vector<Category> getSealedVector(){return sealed;}
    public Vector<Category> getNSealedVector(){return notsealed;}
}
