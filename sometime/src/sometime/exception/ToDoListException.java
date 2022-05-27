package sometime.exception;

public class ToDoListException extends Exception {
    public ToDoListException() {
        // not much here
    }
    
    public ToDoListException(String message) {
        super(message);
    }
    
    public ToDoListException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ToDoListException(Throwable cause) {
        super(cause);
    }
}
