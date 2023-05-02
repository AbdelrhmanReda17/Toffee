package PaymentClasses;

import DataUserClasses.Customer;

import java.util.Objects;
import java.util.Scanner;

public class LoyaltyPayment extends PaymentMethod {
    private String methodName;

    public LoyaltyPayment() {
        this.methodName = "Loyalty Payment";
    }

    public String getMethod() {
        return methodName;
    }

    public boolean processPayment(String phoneNumber, double total_price) {
        Customer c = null;
        if (c.getLoyaltyPoints() == total_price) {
            c.setLoyaltyPoints(0);
            return true;

        } else if (c.getLoyaltyPoints() < total_price) {
            total_price -= c.getLoyaltyPoints();
            System.out.println("Please select another way to complete payment :");
            int choice = new Scanner(System.in).nextInt();
            System.out.println("1. pay cash");
            System.out.println("2. pay with credit");
            if (choice == 1) {
                PaymentMethod PM = new CashPayment();
                PM.processPayment(phoneNumber, total_price);
            }
            if (choice == 2) {
                PaymentMethod CP = new CreditPayment();
                CP.processPayment(phoneNumber, total_price);
            }
        }

        return false;
    }
}