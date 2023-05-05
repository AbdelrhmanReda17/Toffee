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
    public float processPayment(int CustomerLoyalty ,String phone,double total_price  , int loyaltyPoints) {
        Scanner scanner = new Scanner(System.in);
        this.phoneNumber = phone;
        sendVerificationCode();
        if  (!isPhoneVerified) {
            System.out.println("Payment could not be processed.");
            return -1;
        }
        else{
            System.out.println("Payment processed successfully!");
            return 0;
        }
    }

    private void sendVerificationCode() {
        int code = (int) (Math.random() * 900000) + 100000;
        System.out.println("Your verification code is: " + code);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the verification code - 0 to cancel order:");
        int inputCode = scanner.nextInt();
        while(true){
            if (inputCode == code) {
                isPhoneVerified = true;
                System.out.println("Phone number verified successfully.");
                break;
            }else if (inputCode == 0){
                break;
            }
            else {
                System.out.println("Invalid verification code.");
                inputCode = scanner.nextInt();
            }

        }


    }
}