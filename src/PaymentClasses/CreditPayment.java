package PaymentClasses;

import java.util.Scanner;

public class CreditPayment extends PaymentMethod {
    private String methodName = "Credit Payment";
    private String cardNumber;
    private String expDate;
    private int cvv;
    
    public String getMethod() {
        return methodName;
    }
    public float processPayment(int CustomerLoyalty ,String Email,String phone, double total_price  , int loyaltyPoints) {
        Scanner scanner = new Scanner(System.in);
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


        return 0;
    }

}


