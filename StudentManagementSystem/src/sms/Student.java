package sms;

import java.util.LinkedHashMap;
import java.util.Map;

/** Inheritance: Student IS-A Person. Implements Gradable interface. */
public class Student extends Person implements Gradable {
    // Collections: subject -> marks (keeps insertion order)
    private final Map<String, Integer> marks = new LinkedHashMap<>();

    public Student(int id, String name) {
        super(id, name);
    }

    public void addMarks(String subject, int score) throws InvalidMarksException {
        if (score < 0 || score > 100) {
            throw new InvalidMarksException("Marks must be 0-100, got " + score + " for " + subject);
        }
        marks.put(subject, score);
    }

    public Map<String, Integer> getMarks() {
        return new LinkedHashMap<>(marks); // defensive copy
    }

    @Override
    public double percentage() {
        if (marks.isEmpty()) return 0;
        int total = 0;
        for (int m : marks.values()) total += m;
        return (double) total / marks.size();
    }

    /** Polymorphism: overriding Person's abstract method. */
    @Override
    public String getRole() {
        return "Student";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", %.2f%%, grade %s", percentage(), grade());
    }
}
