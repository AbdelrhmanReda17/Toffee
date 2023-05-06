package PaymentClasses;
import java.util.Scanner;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.management.RuntimeErrorException;

import java.util.Properties;

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
    public float processPayment(int CustomerLoyalty ,String Email , String phone,double total_price  , int loyaltyPoints) {
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
        if (SendOTP(email,code)){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the verification code - 0 to cancel order:");
            int inputCode = scanner.nextInt();
            while(true){
                if (inputCode == code) {
                    isEmailSend = true;
                    System.out.println("Email verified successfully.");
                    System.out.println("Delivery man will call you on " + phoneNumber);
                    break;
                }else if (inputCode == 0){
                    break;
                }
                else {
                    System.out.println("Invalid verification code.");
                    inputCode = scanner.nextInt();
                }

            }
        }else{
            System.out.println("Failed to Send OTP to your email");
            isEmailSend = false;
        }
    }
    private boolean SendOTP(String email , int code){
        String host = "smtp.gmail.com"; 
        String username = "fcai.toffeeshop@gmail.com";
        String password = "dfpzbhgihyfxtbjp";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(username));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

            message.setSubject("TOFFE SHOP VERIFICATION CODE");
            message.setText("Your OTP IS : " + code );   

            Transport.send(message);
            System.out.println("Email sent successfully!");
            return true;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
            }
        }
}