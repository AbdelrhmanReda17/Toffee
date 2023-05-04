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
    public float processPayment(int CustomerLoyalty ,String phone, double total_price  , int loyaltyPoints) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter card number: ");
        this.cardNumber = scanner.nextLine();
        System.out.print("Enter expiration date : ");
        expDate = scanner.nextLine();

        System.out.print("Enter CVV: ");
        cvv = scanner.nextInt();
        return 0;
    }

}


