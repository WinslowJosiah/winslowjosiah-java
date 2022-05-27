package sometime.exception;

public class NodeVisitorException extends Exception {
    public NodeVisitorException() {
        // not much here
    }
    
    public NodeVisitorException(String message) {
        super(message);
    }
    
    public NodeVisitorException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public NodeVisitorException(Throwable cause) {
        super(cause);
    }
}
