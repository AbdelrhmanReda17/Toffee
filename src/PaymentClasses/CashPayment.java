package PaymentClasses;
import java.util.Scanner;

import DataUserClasses.Customer;
import SystemClasses.DataManager;
public class CashPayment extends PaymentMethod {
    private String methodName;
    private String phoneNumber;
    private String email;

    private boolean isEmailSend=false;
    public CashPayment(){
        this.methodName = "Cash On Payment";
    }
    public String getMethod() {
        return methodName;
    }
    public float processPayment(DataManager Data,Customer user , String Email , String phone,double total_price ) {
        Scanner scanner = new Scanner(System.in);
        this.phoneNumber = phone;
        this.email = Email;
        System.out.println("Please wait While Sending The verification code to your email.... ");
        sendVerificationCode();
        if  (!isEmailSend) {
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
        if (sendOtp.SendOTP(email, code)) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the verification code - 0 to cancel order:");
            int inputCode = scanner.nextInt();
            while (true) {
                if (inputCode == code) {
                    isEmailSend = true;
                    System.out.println("Email verified successfully.");
                    System.out.println("Delivery man will call you on " + phoneNumber);
                    break;
                }
               else  if (inputCode != code) {
                    isEmailSend = false;
                    System.out.println("Wrong OTP");

                    break;
                }
                else if (inputCode == 0) {
                    break;

                } else {
                    System.out.println("Failed to Send OTP to your email");
                    isEmailSend = false;
                }
            }
        }
    }
}