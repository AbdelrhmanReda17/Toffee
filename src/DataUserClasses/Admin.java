package DataUserClasses;
import java.lang.invoke.VarHandle;
import java.util.*;

import OrderClasses.CartItem;
import OrderClasses.Item;
import OrderClasses.Order;
import OrderClasses.Order_state;
import SystemClasses.*;
public class Admin extends User {
    private DataManager Data = new DataManager();
    private Catalog catalog = new Catalog();
    public Admin(String name, String password,String email) {
        super(name, password , email);
        Data.loadItems();
        Data.loadCustomers();
        Data.loadOrders();
        Data.loadCatalogs();
        Data.loadVouchers();
    }

    public void addItem() {
        Item item = new Item();
        item.getItem();
        if (Data.addItemToVector(item)) {
            Data.updateItems();
            Vector<Catalog> ct = Data.getCatalogs();
            boolean foundCategory = false;
            for (Catalog x : ct) {
                if (item.getCategory().equals(x.getName())) {
                    x.addItem(item);
                    foundCategory = true;
                    break;
                }
            }
            if (!foundCategory) {
                Catalog newCatalog = new Catalog(item.getCategory());
                newCatalog.addItem(item);
                ct.add(newCatalog);
                Data.setCatalogs(ct);
                Data.updateCatalogs();
            }
            Data.setCatalogs(ct);
            Data.updateCatalogs();
            System.out.println("Item added successfully");
        }
    }


    public void editItem() {
        System.out.print("Enter The id of The item you want to Edit: ");
        int id = new Scanner(System.in).nextInt();
        Vector<Item> items = Data.getItems();
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
                Data.setItems(items);
                Data.updateItems();
                catalog.updateIteminCatalog(item);
                System.out.println("Item Edited Successful");
                return;
            }
        }
        System.out.println("Item not found");
    }

    public void deleteItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID of the item you want to delete: ");
        int id = scanner.nextInt();
        Vector<Item> items = Data.getItems();
        Item itemToRemove = null;
        for (Item item : items) {
            if (item.getID() == id) {
                item.printItem(false , false);
                System.out.println();
                System.out.println("Do you want to delete this item? Press 1 to confirm, 2 to cancel.");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    itemToRemove = item;
                } else {
                    System.out.println("Operation Cancelled.");
                    return;
                }
            }
        }
        if (itemToRemove != null) {
            boolean isRemoved = Data.removeItemFromVector(itemToRemove);
            if (isRemoved) {
                items.remove(itemToRemove);
                Data.setItems(items);
                Data.updateItems();
                catalog.removeItem(itemToRemove);
                System.out.println("Item Delete Successfully");
            }
        } else {
            System.out.println("Item not found.");
        }
    }


    public void setLoyaltyPointsSystem() {
        System.out.print("Enter the points per EGP : ");
        int pointsEarned = new Scanner(System.in).nextInt();

        System.out.print("Enter the maximum points Via one Order : ");
        int maximumPoint = new Scanner(System.in).nextInt();
        LoyaltyPoints loyaltyPoints = new LoyaltyPoints(pointsEarned,maximumPoint);
        if(loyaltyPoints.checkLoyaltyPoints()){
            Data.setLoyaltyScheme(loyaltyPoints);
            Data.UpdateVouchers_Loyalty();
            System.out.println("Loyalty Schema Added Successful");
        }
    }

    public boolean suspendUser() {
        System.out.print("Enter the username of the customer you want to suspend: ");
        String username = new Scanner(System.in).nextLine();
        Vector<Customer>ct = Data.getCustomers();
        Vector<Order> or = Data.getOrders();
        for (Customer c : ct) {
            if (Objects.equals(c.getName(), username)){
                System.out.println("Name: " + c.getName());
                System.out.println("Phone: " + c.getPhone());
                System.out.println("Address: " + c.getAddress());
                System.out.println("Do you want to suspend this customer? Press 1 to confirm, 2 to cancel.");
                int choice = new Scanner(System.in).nextInt();
                if (choice == 1) {
                    boolean isRemoved = Data.removeCustomerFromVector(c);
                    if (isRemoved) {
                        Iterator<Order> iterator = or.iterator();
                        while (iterator.hasNext()) {
                            Order order = iterator.next();
                            if (order.getUser() == c) {
                                iterator.remove();
                                Data.setOrderVector(or);
                                Data.updateOrders();
                            }
                        }

                        Data.setCustomers(ct);
                        Data.updateCustomers();
                        System.out.println("User Suspended Successful");
                        return true;
                    }
                } else {
                    System.out.println("Operation cancelled.");
                }
            }
        }
        System.out.println("customer not found.");
        return false;
    }


    public void createAGiftVoucher() {
        Scanner X = new Scanner(System.in);
        //voucher code
        String codeRegex = "^[A-Za-z0-9]{16}$";
        String vouchercode;
        do {
            System.out.print("Enter Voucher code: ");
            vouchercode = X.nextLine();
            if (!vouchercode.matches(codeRegex)) {
                System.out.println("Invalid code! The code must be  16 characters  and consist of letters or digits.");
            }
        } while (!vouchercode.matches(codeRegex));


        String valueRegex = "^[0-9]+(\\.[0-9]+)?$";
        String value;
        do {
            System.out.print("Enter Voucher Value : ");
            value = X.nextLine();
            if (!value.matches(valueRegex)) {
                System.out.println("Invalid voucher value, please enter a valid int or float values.");
            }
        } while (!value.matches(valueRegex));

        float VoucherValue = Float.parseFloat(value);
        GiftVoucher newVoucher = new GiftVoucher(vouchercode,VoucherValue);
        if (newVoucher != null) {
            Vector<GiftVoucher> vouchersS = Data.getVouchers();
            vouchersS.add(newVoucher);
            Data.setVouchers(vouchersS);
            Data.UpdateVouchers_Loyalty();
            System.out.println("Voucher Created Successful");

        }
    }

    public void viewStatistics() {
        Map<String, Integer> map = new HashMap<>();
        double TotalOrdersPrice = 0;
        int NumberOfOrders =0;
        Vector<Order> order = Data.getOrders();
        for(Order x : order){
            List<CartItem> orderItems = x.getShopcart().getCartItems();
            NumberOfOrders++;
            for(CartItem l : orderItems){
                TotalOrdersPrice += (l.getPrice()*l.getQuantity());
                if (map.containsKey(l.getName())){
                    map.put(l.getName(), map.get(l.getName()) + 1);
                } else {
                    map.put(l.getName(), 1);
                }
            }
        }
        System.out.println("We Made " + NumberOfOrders + " Orders With Total Price " + TotalOrdersPrice);
        Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            System.out.println("The Best Selling  Item is ( "  + entry.getKey() + " ) with " + entry.getValue() + " sales");
        }
    }

    public void addNewCatalog() {
        Data.loadCatalogs();
        Vector<Catalog> ct = Data.getCatalogs();
        System.out.print("Enter The Catalog Name : ");
        String name = new Scanner(System.in).nextLine();
        for (Catalog x : ct) {
            if (x.getName() == name) {
                System.out.println("The Catalog Name is Already Exist !");
            }
        }
        Catalog NewCatalog = new Catalog(name);
        ct.add(NewCatalog);
        Data.updateCatalogs();

        System.out.print("Press 1.Add New Item , 2.Add Existing Item, 3.No Need To Add Item  : ");
        int choice = new Scanner(System.in).nextInt();
        if(choice == 1){
            System.out.println("You Must Add The Item First ! ");
            Item item = new Item();
            item.getItem();
            Data.addItemToVector(item);
            Data.updateItems();
            NewCatalog.addItem(item);
            Data.updateCatalogs();
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
        Data.updateCatalogs();
        System.out.println("Catalog added successfully!!");

    }

    public void removeCatalog() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the Name of the catalog you want to remove: ");
        String catalogName = scanner.nextLine();
        Vector<Catalog> catalogs = Data.getCatalogs();
        Catalog ToRemove = null;
        for (Catalog catalog : catalogs) {
            if (catalog.getName().equals(catalogName)) {
                ToRemove = catalog;
                break;
            }
        }

        if (ToRemove != null) {
            catalogs.remove(ToRemove);
            Data.setCatalogs(catalogs);
            Data.updateCatalogs();
            System.out.println("Catalog Deleted Successfully");
        }
        scanner.close();
    }


    public void viewAllOrders() {
        Vector<Order> orders = Data.getOrders();
        for(Order order : orders){
            System.out.println("Order id : " + order.getOrderId());
            System.out.println("User: " + order.getUser().getName());
            System.out.println("Order status: " + order.getStatus().toString());
            System.out.println("Shopping cart: ");
            for (CartItem item : order.getShopcart().getCartItems()) {
                System.out.println(" - " + item.getName() + " (" + item.getID() + ")");
            }
            System.out.println("Shipping address: " + order.getShippingAddress());
            System.out.println("Order time: " + order.getOrdertime().toString());
            System.out.println("Loyalty points: " + order.getUser().getShoppingCart().getLoyaltyPoints());
            System.out.println("Total cost: " + order.getUser().getShoppingCart().getTotalCost());
            System.out.println("Payment method: " + order.getPayment().toString());
        }
    }

    public void ChangeOrderStatus() {
        Vector<Order> orders = Data.getOrders();
        System.out.print("Enter the ID of the order you want to change status for: ");
        int orderId = new Scanner(System.in).nextInt();
        Order_state status = null;
        System.out.println("Choose a new order status:");
        System.out.println("1. IN_PROGRESS");
        System.out.println("2. Ordered");
        System.out.println("3. InDelivery");
        System.out.println("4. Canceled");
        System.out.println("5. Delivered");
        int choice = new Scanner(System.in).nextInt();

        switch (choice) {
            case 1:
                status = Order_state.IN_PROGRESS;
                break;
            case 2:
                status = Order_state.Ordered;
                break;
            case 3:
                status = Order_state.InDelivery;
                break;
            case 4:
                status = Order_state.Canceled;
                break;
            case 5:
                status = Order_state.Delivered;
                break;
            default:
                System.out.println("Invalid choice. Please choose again.");
                break;
        }

        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                order.setStatus(status);
                System.out.println("Order " + orderId + " status updated to " + status);
                break;
            }
        }
        Data.setOrderVector(orders);
        Data.updateOrders();

    }

}