package ge.ufc.model;


import com.google.gson.annotations.SerializedName;

public class Pay {
    @SerializedName("payment_id")
    private String paymentId;

    @SerializedName("user_id")
    private int userId;

    private double amount;

    public Pay(String paymentId, int userId, double amount) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.amount = amount;
    }

    public Pay(){}

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
