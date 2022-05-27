package sometime.exception;

public class LexerException extends Exception {
    public LexerException() {
        // not much here
    }
    
    public LexerException(String message) {
        super(message);
    }
    
    public LexerException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public LexerException(Throwable cause) {
        super(cause);
    }
}
