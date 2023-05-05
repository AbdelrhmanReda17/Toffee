package SystemClasses;

public class GiftVoucher {
    private String code;
    private float value;
    public GiftVoucher(String code , float val){
        this.code = code;
        this.value = val;
    }
    public String getCode() {
        return code;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}

   
