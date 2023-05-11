package PaymentClasses;

import java.util.Scanner;
import DataUserClasses.Customer;
import SystemClasses.DataManager;
public class CreditPayment extends PaymentMethod {
    private String methodName = "Credit Payment";
    private String cardNumber;
    private String expDate;
    private int cvv;

    /**
     * Returns the payment method name
     * @return the payment method name
     */
    public String getMethod() {
        return methodName;
    }

    /**
     * Processes the payment using the credit card information provided by the user
     * @param Data the data manager for the system
     * @param user the customer making the payment
     * @param Email the email associated with the customer's account
     * @param phone the phone number associated with the customer's account
     * @param total_price the total price of the transaction
     * @return 0 if the payment was successful, -1 otherwise
    */
    public float processPayment(DataManager Data,Customer user,String Email,String phone, double total_price) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean validCardNumber = false;
            boolean validExpDate = false;
            boolean validCVV = false;

            do {
                System.out.print("Enter card number: ");
                this.cardNumber = scanner.nextLine();
                validCardNumber = cardNumber.matches("^\\d{16}$");

                if (!validCardNumber) {
                    System.out.println("Invalid card number. Please enter a 16-digit number.");
                }
            } while (!validCardNumber);
            do {
                System.out.print("Enter expiration date (MM/YY): ");
                expDate = scanner.nextLine();
                validExpDate = expDate.matches("^(0[1-9]|1[0-2])/\\d{2}$");

                if (!validExpDate) {
                    System.out.println("Invalid expiration date. Please enter a valid date in the format MM/YY.");
                }
            } while (!validExpDate);


            do {
                System.out.print("Enter CVV: ");
                cvv = scanner.nextInt();
                validCVV = String.valueOf(cvv).matches("^\\d{3}$");

                if (!validCVV) {
                    System.out.println("Invalid CVV. Please enter a 3-digit number.");
                }
            } while (!validCVV);
        }
        return 0;
    }

}


