package sms;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/** Multithreading: builds each student's report card in parallel. */
public class ReportGenerator {

    private static String buildReport(Student s) {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Report Card: ").append(s.getName()).append(" ---\n");
        s.getMarks().forEach((sub, m) -> sb.append(String.format("  %-10s %3d%n", sub, m)));
        sb.append(String.format("  Percentage: %.2f%%  Grade: %s", s.percentage(), s.grade()));
        return sb.toString();
    }

    public static List<String> generateAll(List<Student> students)
            throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        try {
            List<Future<String>> futures = new ArrayList<>();
            for (Student s : students) {
                Callable<String> task = () -> buildReport(s);
                futures.add(pool.submit(task));
            }
            List<String> reports = new ArrayList<>();
            for (Future<String> f : futures) reports.add(f.get()); // keeps order
            return reports;
        } finally {
            pool.shutdown();
        }
    }
}
