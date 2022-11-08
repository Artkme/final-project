package ge.ufc.webapps.exception;

public class AmountNotPositiveException extends Exception {
    public AmountNotPositiveException() {
        super();
    }

    public AmountNotPositiveException(String message) {
        super(message);
    }

    public AmountNotPositiveException(String message, Throwable cause) {
        super(message, cause);
    }

    public AmountNotPositiveException(Throwable cause) {
        super(cause);
    }

    protected AmountNotPositiveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
