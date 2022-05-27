package sometime.exception;

public class ParserException extends Exception {
    public ParserException() {
        // not much here
    }
    
    public ParserException(String message) {
        super(message);
    }
    
    public ParserException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ParserException(Throwable cause) {
        super(cause);
    }
}
