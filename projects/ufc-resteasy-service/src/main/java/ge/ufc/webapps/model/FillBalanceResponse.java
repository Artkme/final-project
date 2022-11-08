package ge.ufc.webapps.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class FillBalanceResponse {
    long sysTransId;
    int status;

    public FillBalanceResponse(long sysTransId, int statusCode) {
        this.sysTransId = sysTransId;
        this.status = statusCode;
    }

    public FillBalanceResponse(){}

    public long getSysTransId() {
        return sysTransId;
    }

    @JsonProperty("transaction_id")
    public void setSysTransId(long sysTransId) {
        this.sysTransId = sysTransId;
    }

    public int getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(int status) {
        this.status = status;
    }
}
