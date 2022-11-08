package ge.ufc.conf;

public class Conf {
    private String getUserUrl;
    private String fillBalanceUrl;
    private int timeout;

    public void setGetUserUrl(final String getUserUrl) {
        this.getUserUrl = getUserUrl;
    }

    public void setFillBalanceUrl(final String fillBalanceUrl) {
        this.fillBalanceUrl = fillBalanceUrl;
    }

    public void setTimeout(final int timeout) {
        this.timeout = timeout;
    }

    public String getGetUserUrl() {
        return getUserUrl;
    }

    public String getFillBalanceUrl() {
        return fillBalanceUrl;
    }

    public int getTimeout() {
        return timeout;
    }
}
