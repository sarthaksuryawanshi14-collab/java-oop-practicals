package sms;

/** Custom checked exception for marks outside 0-100. */
public class InvalidMarksException extends Exception {
    public InvalidMarksException(String message) {
        super(message);
    }
}
