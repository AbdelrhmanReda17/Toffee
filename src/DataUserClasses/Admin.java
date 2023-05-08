package DataUserClasses;
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

    }

    public void addItem(Vector<Item> items,Vector<Catalog> ct) {
        Item item = new Item();
        item.getItem();
        items.add(item);
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
        }
        Data.setCatalogs(ct);
        System.out.println("Item added successfully");
        Data.setItems(items);

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
                catalog.updateIteminCatalog(item);
                System.out.println("Item Edited Successful");
                Data.setItems(items);
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
                catalog.removeItem(itemToRemove);
                System.out.println("Item Delete Successfully");
            }
        }else{
            System.out.println("Item not found.");
        }
        Data.setItems(items);
    }


    public void setLoyaltyPointsSystem() {
        System.out.print("Enter the points per EGP : ");
        int pointsEarned = new Scanner(System.in).nextInt();
        System.out.print("Enter the maximum points Via one Order : ");
        int maximumPoint = new Scanner(System.in).nextInt();
        LoyaltyPoints loyaltyPoints = new LoyaltyPoints(pointsEarned,maximumPoint);
        if(loyaltyPoints.checkLoyaltyPoints()){
            System.out.println("Loyalty Schema Added Successful");
        }
        Data.setLoyaltyScheme(loyaltyPoints);
    }

    public void suspendUser(Vector<Customer> ct) {
        System.out.print("Enter the username of the customer you want to suspend: ");
        String username = new Scanner(System.in).nextLine();
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
                            }
                        }

                        System.out.println("User Suspended Successful");
                        Data.setCustomers(ct);
                        return ;
                    }
                } else {
                    System.out.println("Operation cancelled.");
                }
            }
        }
        System.out.println("customer not found.");
    }


    public void createAGiftVoucher(Vector<GiftVoucher> vouchers) {
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

            vouchers.add(newVoucher);
            System.out.println("Voucher Created Successful");
        }
        Data.setVouchers(vouchers);
    }

    public void viewStatistics(Vector<Order> order) {
        Map<String, Integer> map = new HashMap<>();
        double TotalOrdersPrice = 0;
        int NumberOfOrders =0;
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

    public void addNewCatalog(Vector<Catalog>ct,Vector<Item>itemM) {
        System.out.print("Enter The Catalog Name : ");
        String name = new Scanner(System.in).nextLine();
        for (Catalog x : ct) {
            if (x.getName() == name) {
                System.out.println("The Catalog Name is Already Exist !");
            }
        }
        Catalog NewCatalog = new Catalog(name);
        ct.add(NewCatalog);

        System.out.print("Press 1.Add New Item , 2.Add Existing Item, 3.No Need To Add Item  : ");
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
        System.out.println("Catalog added successfully!!");
        Data.setCatalogs(ct);

    }

    public void removeCatalog(Vector<Catalog> catalogs) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the Name of the catalog you want to remove: ");
        String catalogName = scanner.nextLine();
        Catalog ToRemove = null;
        for (Catalog catalog : catalogs) {
            if (catalog.getName().equals(catalogName)) {
                ToRemove = catalog;
                break;
            }
        }

        if (ToRemove != null) {
            catalogs.remove(ToRemove);
            System.out.println("Catalog Deleted Successfully");
        }
        scanner.close();
        Data.setCatalogs(catalogs);
    }


    public void viewAllOrders(Vector<Order>orders) {
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

    public void ChangeOrderStatus(Vector<Order> orders) {
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

    }

}