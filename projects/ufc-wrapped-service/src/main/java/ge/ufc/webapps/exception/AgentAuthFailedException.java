package ge.ufc.webapps.exception;

public class AgentAuthFailedException extends Exception {

    public AgentAuthFailedException() {
        super("Authorization Failed");
    }

    public AgentAuthFailedException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
