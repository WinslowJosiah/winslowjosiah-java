package sometime.exception;

public class CommandLineException extends Exception {
    public CommandLineException() {
        // not much here
    }
    
    public CommandLineException(String message) {
        super(message);
    }
    
    public CommandLineException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public CommandLineException(Throwable cause) {
        super(cause);
    }
}
