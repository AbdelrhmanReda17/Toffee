package SystemClasses;

import java.util.Scanner;
import java.util.Vector;

public class GiftVoucher {
    private String code;
    private DataManager Data = new DataManager();
    private float value;
    public GiftVoucher(String code , float val){
        this.code = code;
        this.value = val;
    }
    public GiftVoucher(){};
    public String getCode() {
        return code;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void createAGiftVoucher(Vector<GiftVoucher> vouchers) {
        Scanner X = new Scanner(System.in);
        //voucher code
        String codeRegex = "^[A-Za-z0-9]{16}$";
        String vouchercode;
        do {
            System.out.print("Enter Voucher code: ");
            vouchercode = X.nextLine();
            if (!vouchercode.matches(codeRegex)) {
                System.out.println("Invalid code! The code must be  16 characters  and consist of letters or digits.");
            }
        } while (!vouchercode.matches(codeRegex));


        String valueRegex = "^[0-9]+(\\.[0-9]+)?$";
        String value;
        do {
            System.out.print("Enter Voucher Value : ");
            value = X.nextLine();
            if (!value.matches(valueRegex)) {
                System.out.println("Invalid voucher value, please enter a valid int or float values.");
            }
        } while (!value.matches(valueRegex));

        float VoucherValue = Float.parseFloat(value);
        GiftVoucher newVoucher = new GiftVoucher(vouchercode,VoucherValue);
        if (newVoucher != null) {

            vouchers.add(newVoucher);
            System.out.println("Voucher Created Successful");
        }
        Data.setVouchers(vouchers);
    }

}

   
