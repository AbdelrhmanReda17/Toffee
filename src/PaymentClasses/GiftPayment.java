package PaymentClasses;

import SystemClasses.DataManager;
import SystemClasses.GiftVoucher;
import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.Random;

public class GiftPayment extends PaymentMethod {
    private String methodName;
    private String voucherCode;
    private DataManager DATA = new DataManager();
    Vector<GiftVoucher> vouchers;
    public GiftPayment() {
        this.methodName = "Gift Voucher Payment";
    }

    public String getMethod() {
        return methodName;
    }

    public float processPayment(double total_price) {
        int choice; 
        DATA.loadVouchers();
        vouchers = DATA.getVouchers();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your voucher code: ");
        voucherCode = scanner.nextLine();
        for (int i = 0; i < vouchers.size(); i++) {
            if(Objects.equals(vouchers.get(i).getCode(), voucherCode))
            {
                System.out.println("You Enter a Voucher With Value " + vouchers.get(i).getValue());
                if (vouchers.get(i).getValue()==total_price){
                    System.out.println("Order will be paid , Confirm that you want to Add the voucher (y->1 /n->2): ");
                    choice = scanner.nextInt();
                    if(choice == 1){
                        System.out.println("Sucessfully Added");
                        remove_voucher();
                        return 0;
                    }else if(choice == 2){
                        System.out.println("Voucher Didn't Applied");
                        return -1;
                    }
                    scanner.close();
                }else if (vouchers.get(i).getValue()<total_price){
                    System.out.print("Total Price Will be Updated to be  :" + (total_price - vouchers.get(i).getValue()) + "L.E");
                    System.out.println(" , Confirm that you want to Add the voucher (y->1 /n->2): ");
                    choice = scanner.nextInt();
                    if(choice == 1){
                        System.out.println("Sucessfully Added");
                        return vouchers.get(i).getValue();
                    }else if(choice == 2){
                        System.out.println("Voucher Didn't Applied");
                        return -1;
                    }
                }
                else if (vouchers.get(i).getValue()>total_price){
                    System.out.println("You have a Remaining price : " + ( vouchers.get(i).getValue() - total_price) + "L.E");
                    System.out.println(" You will take anthor Voucher with the Remaining Price , Confirm that you want to Add the voucher (y->1 /n->2): ");
                    choice = scanner.nextInt();
                    if(choice == 1){
                        GiftVoucher voucher = generate_voucher(new Float(vouchers.get(i).getValue() - total_price));
                        System.out.println("Sucessfully Added");
                        System.out.println("Here's your New Voucher, Code: " + voucher.getCode() + " - Value : " + voucher.getValue());
                        remove_voucher();
                        return 0;
                    }else if(choice == 2){
                        System.out.println("Voucher Didn't Applied");
                        return -1;
                    }
                }
            }
        }
        return 0;
    }
    private void remove_voucher(){
        for (int i = 0; i < vouchers.size(); i++) {
            if (vouchers.get(i).getCode().equals(voucherCode)) {
                {
                    vouchers.remove(i);
                }
            }
        }
        DATA.loadLoyaltyScheme();
        DATA.setVouchers(vouchers);
        DATA.UpdateVouchers_Loyalty();
    }
    private String generateCode(){
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new SecureRandom();
        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            codeBuilder.append(randomChar);
        }
        return codeBuilder.toString();
    }
    private GiftVoucher generate_voucher(Float Value){
        String code =  generateCode();
        GiftVoucher voucher = new GiftVoucher(code, Value);
        for(int i = 0 ; i < vouchers.size() ; i++){
            if(vouchers.get(i).getCode() == code){
                generate_voucher(Value);
                break;
            }
        }
        vouchers.add(voucher);
        DATA.loadLoyaltyScheme();
        DATA.setVouchers(vouchers);
        DATA.UpdateVouchers_Loyalty();
        return voucher;
    }
}
