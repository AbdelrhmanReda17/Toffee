package SystemClasses;

import java.util.Scanner;
import java.util.Vector;
/**
 * The GiftVoucher class represents a gift voucher in the Toffee shop.
 */
public class GiftVoucher {
    private String code;
    private DataManager Data = new DataManager();
    private float value;

    /**
     * Constructs a GiftVoucher object with the specified code and value.
     * @param code The code of the gift voucher.
     * @param val The value of the gift voucher.
     */
    public GiftVoucher(String code , float val){
        this.code = code;
        this.value = val;
    }

    /**
     * Default constructor for the GiftVoucher class.
     */
    public GiftVoucher(){};

    /**
     * Returns the code of the gift voucher.
     * @return The code of the gift voucher.
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the value of the gift voucher.
     * @return The value of the gift voucher.
     */
    public float getValue() {
        return value;
    }

    /**
     * Sets the value of the gift voucher.
     * @param value The value to set for the gift voucher.
     */
    public void setValue(float value) {
        this.value = value;
    }

    /**
     * Creates a new gift voucher and adds it to the list of vouchers.
     * @param vouchers The list of existing vouchers.
     */
    public void createAGiftVoucher(Vector<GiftVoucher> vouchers) {
        Scanner X = new Scanner(System.in);
        //voucher code
        String codeRegex = "^[A-Za-z0-9]{16}$";
        String vouchercode;
        do {
            System.out.print("Enter Voucher code: ");
            vouchercode = X.nextLine();
            if (!vouchercode.matches(codeRegex)) {
                System.out.println("Invalid code! The code must be 16 characters and consist of letters or digits.");
            }
        } while (!vouchercode.matches(codeRegex));


        String valueRegex = "^[0-9]+(\\.[0-9]+)?$";
        String value;
        do {
            System.out.print("Enter Voucher Value : ");
            value = X.nextLine();
            if (!value.matches(valueRegex)) {
                System.out.println("Invalid voucher value, please enter a valid int or float value.");
            }
        } while (!value.matches(valueRegex));

        float VoucherValue = Float.parseFloat(value);
        GiftVoucher newVoucher = new GiftVoucher(vouchercode,VoucherValue);
        if (newVoucher != null) {

            vouchers.add(newVoucher);
            System.out.println("Voucher Created Successfully");
        }
        Data.setVouchers(vouchers);
    }
}
