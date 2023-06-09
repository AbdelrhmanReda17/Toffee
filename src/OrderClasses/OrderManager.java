package OrderClasses;

import DataUserClasses.Customer;
import PaymentClasses.*;
import SystemClasses.DataManager;
import java.util.*;
/**
 * Represents the order manager responsible for placing orders.
 */
public class OrderManager {
    private Order order = new Order();
    /**
     * Places an order for a customer.
     * @param Data The data manager.
     * @param user The customer placing the order.
     * @return True if the order was successfully placed, false otherwise.
     */
    public boolean placeOrder(DataManager Data, Customer user) {
        float paymentSuccess;
        String shippingAddress = null;
        PaymentMethod payment = null;
        System.out.println("The Total Price of you items : " + user.getShoppingCart().getTotalCost() + "L.E");
        Scanner input = new Scanner(System.in);
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        String email;
        do {
            System.out.print("Enter Email : ");
            email = new Scanner(System.in).nextLine();
            if (!email.matches(emailRegex)) {
                System.out.println("Invalid Email! It must be in the correct format.");
            }
        } while (!email.matches(emailRegex));

        String addressRegex = "^[a-zA-Z0-9\\s,-]*$";
        do {
            int choice = 0;
            System.out.println("Select the Address:");
            System.out.println("1. Same Address");
            System.out.println("2. Another Address");
            choice = input.nextInt();
            if (choice == 1) {

                shippingAddress = user.getAddress();
                System.out.println("Your order will be delivered to " + shippingAddress);
            }
            if (choice == 2) {
                System.out.println("Enter the address");
                shippingAddress = new Scanner(System.in).nextLine();
            }
            if (!shippingAddress.matches(addressRegex)) {
                System.out.println("Invalid address!! Addresses can consist of letters, numbers, [  ,_ ,- ]");
            }
        } while (!shippingAddress.matches(addressRegex));

        String phoneRegex = "^[0-9]{11}$";
        String phone;
        do {
            System.out.print("Enter phone number: ");
            phone = new Scanner(System.in).nextLine();
            if (!phone.matches(phoneRegex)) {
                System.out.println("Invalid phone number !! it must only numbers and 11 digit");
            }
        } while (!phone.matches(phoneRegex));

        while (true) {
            boolean isValid = false;
            int paymentMethodChoice = 0;
            do {
                System.out.println("Select a payment method:");
                System.out.println("1. Credit Card");
                System.out.println("2. Cash on Delivery");
                System.out.println("3. Using Loyalty Points");
                System.out.println("4. Using Gift voucher");
                System.out.println("0. Back");
                paymentMethodChoice = input.nextInt();
                if (paymentMethodChoice == 1) {
                    isValid = true;
                    payment = new CreditPayment();
                } else if (paymentMethodChoice == 2) {
                    isValid = true;
                    payment = new CashPayment();
                } else if (paymentMethodChoice == 3) {
                    isValid = true;
                    payment = new LoyaltyPayment();
                } else if (paymentMethodChoice == 4) {
                    isValid = true;
                    payment = new GiftPayment();
                } else if (paymentMethodChoice == 0) {
                    return false;
                } else {
                    System.out.println("Opps! your Choice Is Wrong , Please re-Enter it");
                    isValid = false;
                }
            } while (!isValid);
            paymentSuccess = payment.processPayment(Data, user, email, phone, user.getShoppingCart().getTotalCost());
            order.setPayment(payment);
            order.setShippingAddress(shippingAddress);

            if (paymentSuccess == -1) {
                System.out.println("Failed to process payment , please Select another payment");
            } else {
                if (paymentSuccess == 0) {
                    if (!(payment instanceof LoyaltyPayment)) {
                        user.setLoyaltyPoints(user.getLoyaltyPoints() + user.getShoppingCart().getPointsEarned());
                        System.out.println("You Gained " + user.getShoppingCart().getPointsEarned() + " Loyalty Points Your Loyalty Points Balance updated to be " + (user.getLoyaltyPoints()));
                    }
                    Data.setCurrentCustomer(user);
                    CreateOrder(Data, user, Order_state.IN_PROGRESS, user.getShoppingCart(), shippingAddress, payment);
                    return true;
                } else {
                    user.getShoppingCart().setTotalCost(paymentSuccess);
                    return placeOrder(Data, user);
                }
            }
        }
    }
        /**
     * Creates an order and adds it to the data manager.
     * @param Data         The data manager.
     * @param user         The customer who placed the order.
     * @param status       The status of the order.
     * @param shoppingCart The shopping cart containing the items in the order.
     * @param address      The shipping address for the order.
     * @param payment      The payment method used for the order.
     */
    private void CreateOrder(DataManager Data, Customer user, Order_state status, ShoppingCart shoppingCart, String address, PaymentMethod payment) {
        Date ordertime = order.getOrderTime(false);
        Order order = new Order(user, status, shoppingCart, ordertime, address, payment);
        Data.setOrders(order);
        order.setOrderTime(ordertime);
        System.out.println("Delivery expected time: " + order.formatOrderTime());
    }


    /**
     * Displays information for all orders in a vector.
     * @param orders The vector of orders to display.
     */
    public void viewAllOrders(Vector<Order> orders) {
        Collections.reverse(orders);
        if (orders.size() == 0) {
            System.out.println("No orders to display.");
            return;
        }

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.format("%-12s %-16s %-20s %-20s %-20s %-20s %-36s %-34s %-50s%n", "Order ID", "User", "Order status", "Loyalty points", "Total cost", "Payment method", "Shipping address", "Order time", "Shopping cart items");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (Order order : orders) {
            System.out.format("%-12s %-16s %-20s %-20s %-20s %-20s %-36s %-34s %-50s%n",
                    order.getOrderId(),
                    order.getUser().getName(),
                    order.getStatus().toString(),
                    order.getUser().getShoppingCart().getLoyaltyPoints(),
                    order.getUser().getShoppingCart().getTotalCost(),
                    order.getPayment().getMethod(),
                    order.getShippingAddress(),
                    order.getOrdertime().toString(),
                    getCartItemsString(order.getShopcart()));
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }



    /**
     * Converts the items in a shopping cart into a formatted string.
     * @param cart The shopping cart.
     * @return The formatted string of cart items.
     */
    private String getCartItemsString(ShoppingCart cart) {
        StringBuilder sb = new StringBuilder();
        for (CartItem item : cart.getCartItems()) {
            sb.append(item.getName()).append(", ");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

    /**
     * Displays the past order history for a customer and allows reordering.
     * @param customer The customer.
     * @param orders   The vector of orders.
     * @return True if the customer wants to reorder, false otherwise.
     */
    public boolean PastOrders(Customer customer, Vector<Order> orders) {
        boolean isValid = true, HaveOrders = false;
        do {
            Scanner input = new Scanner(System.in);
            System.out.println("-----------------------------------------------------------------  Past Ordered Page ----------------------------------------------------------------");
            System.out.println();
            HaveOrders = customer.DisplayPrevOrderHistory(orders);
            if (HaveOrders) {
                System.out.println("Would you like to reorder? (Please enter 1 for yes, 2 for no)");
                int ch2 = input.nextInt();
                if (ch2 == 1) {
                    customer.setShoppingCart(customer.reorder(orders));
                    return true;
                } else if (ch2 == 2) {
                    return false;
                } else {
                    isValid = false;
                }
            }
        } while (!isValid);

        return false;
    }

    /**
     * Displays statistics based on the orders.
     * @param orders The vector of orders.
     */
    public void viewStatistics(Vector<Order> orders) {
        Map<String, Integer> map = new HashMap<>();
        double TotalOrdersPrice = 0;
        int NumberOfOrders = 0;
        for (Order x : orders) {
            List<CartItem> orderItems = x.getShopcart().getCartItems();
            NumberOfOrders++;
            for (CartItem l : orderItems) {
                TotalOrdersPrice += (l.getPrice() * l.getQuantity());
                if (map.containsKey(l.getName())) {
                    map.put(l.getName(), map.get(l.getName()) + 1);
                } else {
                    map.put(l.getName(), 1);
                }
            }
        }
        System.out.println("--------------------------------------------------------------------------------- Statistics Page-----------------------------------------------------------------------------------------------");
        System.out.println("Total Number Of Orders : " + NumberOfOrders + " With Revenue : " + TotalOrdersPrice);

        Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            System.out.println("The Best Selling  Item is : " + entry.getKey() + " with : " + entry.getValue() + " sales");
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

}
