package Voucher;
import java.util.ArrayList;
public class Voucher {
    private String voucherId;

    private String voucherValue;

    private String voucherCode;

    public static ArrayList<Voucher> voucherList = new ArrayList<>();

    public Voucher(String voucherId, String voucherValue, String voucherCode){
        this.voucherId = voucherId;
        this.voucherValue = voucherValue;
        this.voucherCode = voucherCode;
        Voucher.voucherList.add(this);
    }
    public String getVoucherId(){
        return voucherId;
    }
    public String getVoucherValue(){
        return voucherValue;
    }
    public String getVoucherCode(){
        return voucherCode;
    }
}
