package sms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Manages students using Collections (List + Map). */
public class StudentManager {
    private final List<Student> students = new ArrayList<>();
    private final Map<Integer, Student> byId = new HashMap<>();

    public void add(Student s) {
        if (byId.containsKey(s.getId())) {
            throw new IllegalArgumentException("Duplicate id: " + s.getId());
        }
        students.add(s);
        byId.put(s.getId(), s);
    }

    public Student find(int id) throws StudentNotFoundException {
        Student s = byId.get(id);
        if (s == null) throw new StudentNotFoundException(id);
        return s;
    }

    public void remove(int id) throws StudentNotFoundException {
        Student s = find(id);
        students.remove(s);
        byId.remove(id);
    }

    /** Returns students sorted by percentage, highest first. */
    public List<Student> rankList() {
        List<Student> copy = new ArrayList<>(students);
        copy.sort(Comparator.comparingDouble(Student::percentage).reversed());
        return copy;
    }

    /** Students below 40% = slow learners, above 60% = advanced learners. */
    public List<Student> slowLearners() {
        List<Student> out = new ArrayList<>();
        for (Student s : students) if (s.percentage() < 40) out.add(s);
        return out;
    }

    public List<Student> advancedLearners() {
        List<Student> out = new ArrayList<>();
        for (Student s : students) if (s.percentage() > 60) out.add(s);
        return out;
    }

    public List<Student> all() {
        return new ArrayList<>(students);
    }
}
