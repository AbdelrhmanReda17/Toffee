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
    public boolean processPayment(String phoneNumber,double total_price) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter card number: ");
<<<<<<< HEAD
        this.cardNumber = scanner.nextLine();

=======
        cardNumber = scanner.nextLine();
>>>>>>> c0997b5831fb6aeee612587a23b7b56d0b61be2e
        System.out.print("Enter expiration date : ");
        expDate = scanner.nextLine();

        System.out.print("Enter CVV: ");
        cvv = scanner.nextInt();
        
        scanner.close();
        return true;
    }

}


