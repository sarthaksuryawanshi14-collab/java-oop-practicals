package sms;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        StudentManager manager = new StudentManager();
        Teacher teacher = new Teacher(100, "Prof. Sharma", "Java");
        System.out.println(teacher);

        // Add students and marks
        String[][] data = {
            {"1", "Aarav", "85", "90", "78"},
            {"2", "Diya", "35", "28", "40"},
            {"3", "Kabir", "65", "70", "62"},
            {"4", "Meera", "30", "38", "25"}
        };
        for (String[] row : data) {
            Student s = new Student(Integer.parseInt(row[0]), row[1]);
            String[] subjects = {"OOP", "DBMS", "Maths"};
            for (int i = 0; i < subjects.length; i++) {
                try {
                    s.addMarks(subjects[i], Integer.parseInt(row[i + 2]));
                } catch (InvalidMarksException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            manager.add(s);
        }

        // Exception handling demo
        try {
            manager.find(1).addMarks("Physics", 150);
        } catch (InvalidMarksException e) {
            System.out.println("Caught: " + e.getMessage());
        }
        try {
            manager.find(99);
        } catch (StudentNotFoundException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        System.out.println("\n== Rank list ==");
        manager.rankList().forEach(System.out::println);

        System.out.println("\n== Advanced learners (above 60%) - mentors ==");
        manager.advancedLearners().forEach(s -> System.out.println(s.getName()));

        System.out.println("\n== Slow learners (below 40%) - need mentoring ==");
        manager.slowLearners().forEach(s -> System.out.println(s.getName()));

        // Polymorphism: Person reference holds Student or Teacher
        System.out.println("\n== Polymorphism ==");
        Person[] people = { teacher, manager.find(1) };
        for (Person p : people) System.out.println(p.getRole() + " -> " + p.getName());

        System.out.println("\n== Reports (generated using threads) ==");
        List<String> reports = ReportGenerator.generateAll(manager.all());
        reports.forEach(System.out::println);
    }
}
