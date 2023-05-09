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
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
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

    public Vector<Category> getSealedVector(){return sealed;}
    public Vector<Category> getNSealedVector(){return notsealed;}
}
