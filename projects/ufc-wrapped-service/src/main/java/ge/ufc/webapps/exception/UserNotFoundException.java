package ge.ufc.webapps.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("The specified user does not exist");
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
