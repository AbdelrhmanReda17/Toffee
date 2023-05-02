package PaymentClasses;

import DataUserClasses.Catalog;
import OrderClasses.Item;
import SystemClasses.DataManager;
import SystemClasses.GiftVoucher;

import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;

public class GiftPayment extends PaymentMethod {
    private String methodName;
    private String voucherCode;


    private DataManager DATA = new DataManager();

    public GiftPayment() {
        this.methodName = "Gift Voucher Payment";
    }

    public String getMethod() {
        return methodName;
    }

    public boolean processPayment(String phoneNumber,double total_price) {
        DATA.loadVouchers();
        Vector<GiftVoucher> vouchers = DATA.getVouchers();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your voucher code: ");
        voucherCode = scanner.nextLine();
        scanner.close();
        for (int i = 0; i < vouchers.size(); i++) {
            if (Objects.equals(vouchers.get(i).getCode(), voucherCode) && vouchers.get(i).getValue()==total_price) {
                remove_voucher();
                return true;
            }
            else if(Objects.equals(vouchers.get(i).getCode(), voucherCode) && vouchers.get(i).getValue()<total_price){
                total_price-= vouchers.get(i).getValue();
                System.out.println("Please select another way to complete payment :");
                int choice = new Scanner(System.in).nextInt();
                scanner.close();
                System.out.println("1. pay cash");
                System.out.println("2. pay with credit");
                if (choice==1){
                    PaymentMethod PM=new CashPayment();
                    PM.processPayment( phoneNumber,total_price);
                }
                if (choice==2){
                    PaymentMethod CP=new CreditPayment();
                    CP.processPayment(phoneNumber,total_price);
                }
            }
        }
        return false;
    }
    void remove_voucher(){
        DATA.loadVouchers();
        Vector<GiftVoucher>vouchers = DATA.getVouchers();
        for (int i = 0; i < vouchers.size(); i++) {
            if (vouchers.get(i).getCode() == voucherCode) {
                {
                    vouchers.remove(i);
                }
            }
        }
        DATA.setVouchers(vouchers);
        DATA.updateData();
    }
}
