/**
 * Custom checked exception thrown when a rainfall measurement is negative.
 * This exception extends the standard Exception class.
 */
public class InvalidRainfallException extends Exception {
    
    /**
     * Creates a new InvalidRainfallException with the specified detail message.
     * 
     * @param message the detail message explaining the exception
     */
    public InvalidRainfallException(String message) {
        super(message);
    }
    
    /**
     * Creates a new InvalidRainfallException with a default message.
     */
    public InvalidRainfallException() {
        super("Invalid rainfall measurement: negative value not allowed");
    }
}