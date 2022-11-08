package ge.ufc.webapps.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class Pay {

    private String paymentId;
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

    @JsonProperty("payment_id")
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public int getUserId() {
        return userId;
    }

    @JsonProperty("user_id")
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
