package PaymentClasses;
import java.util.Scanner;
public class CashPayment extends PaymentMethod {
    private String methodName;
    private String phoneNumber;
    private boolean isPhoneVerified=false;
    public CashPayment(){
        this.methodName = "Cash On Payment";
    }
    public String getMethod() {
        return methodName;
    }
    public float processPayment(double total_price) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your phone number: ");
        this.phoneNumber = scanner.nextLine();
        scanner.close();
        // System.out.println(phoneNumber);
        sendVerificationCode();
        if  (!isPhoneVerified) {
            System.out.println("Payment could not be processed.");
            return 0;
        }
        else{
            System.out.println("Payment processed successfully!");
            return 1;
        }
    }

    private void sendVerificationCode() {
        int code = (int) (Math.random() * 900000) + 100000;
        System.out.println("Your verification code is: " + code);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the verification code:");
        int inputCode = scanner.nextInt();
        scanner.close();
        if (inputCode == code) {
            isPhoneVerified = true;
            System.out.println("Phone number verified successfully.");
        } else {
            System.out.println("Invalid verification code.");
        }

    }
}