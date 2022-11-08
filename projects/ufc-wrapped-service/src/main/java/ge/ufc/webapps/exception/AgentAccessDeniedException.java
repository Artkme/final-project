package ge.ufc.webapps.exception;

public class AgentAccessDeniedException extends Exception {

    public AgentAccessDeniedException() {
        super("Access denied");
    }

    public AgentAccessDeniedException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
