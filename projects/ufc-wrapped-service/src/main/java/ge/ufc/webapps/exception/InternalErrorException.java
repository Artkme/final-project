package ge.ufc.webapps.exception;

public class InternalErrorException extends Exception {

    public InternalErrorException(String message) {
        super(message);
    }

    public InternalErrorException(Throwable cause) {
        super(cause);
    }

    protected InternalErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public InternalErrorException() {
        super("Internal Error");
    }

    public InternalErrorException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
