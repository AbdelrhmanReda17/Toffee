package PaymentClasses;
import java.util.Scanner;

import DataUserClasses.Customer;
import SystemClasses.DataManager;
public class CashPayment extends PaymentMethod {
    private String methodName;
    private String phoneNumber;
    private String email;
    private boolean isEmailSend=false;
    /**
     * Constructor for creating CashPayment object.
     */
    public CashPayment(){
        this.methodName = "Cash On Payment";
    }
    /**
     * Returns the name of the payment method.
     * @return The name of the payment method.
     */
    public String getMethod() {
        return methodName;
    }
    /**
     * Processes the payment for the customer using cash payment method.
     * @param Data The DataManager object that stores and manages all the data.
     * @param user The Customer object for which the payment is being processed.
     * @param Email The email address of the customer.
     * @param phone The phone number of the customer.
     * @param total_price The total price of the items being purchased.
     * @return The status of the payment.
     */
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

    /**
     * Sends a verification code to the customer's email address and processes the payment if the verification is successful.
     */
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