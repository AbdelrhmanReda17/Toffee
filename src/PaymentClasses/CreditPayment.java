package PaymentClasses;

import java.util.Scanner;

public class CreditPayment extends PaymentMethod {
    private String cardNumber;
    private String expDate;
    private int cvv;

    public boolean processPayment() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter card number: ");
        cardNumber = scanner.nextLine();

        System.out.print("Enter expiration date : ");
        expDate = scanner.nextLine();

        System.out.print("Enter CVV: ");
        cvv = scanner.nextInt();
        return true;


    }

    // Implement other methods and attributes specific to CreditPayment
}


