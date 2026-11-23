package sms;

/** Custom checked exception thrown when a student id does not exist. */
public class StudentNotFoundException extends Exception {
    public StudentNotFoundException(int id) {
        super("No student found with id " + id);
    }
}
