package PaymentClasses;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class sendOtp {
    /**
     * Sends an OTP (One-Time Password) to the specified email address using SMTP.
     * @param email the email address to send the OTP to
     * @param code the OTP code to send
     * @return true if the email was sent successfully, false otherwise
     */
    public static boolean SendOTP(String email , int code){
        String host = "smtp.gmail.com";
        String username = "toffeewebsite8@gmail.com";
        String password = "knfmmhrszhkabtlm";
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

            message.setSubject("TOFFEE SHOP VERIFICATION CODE");
            message.setText("Your OTP IS : " + code);

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
