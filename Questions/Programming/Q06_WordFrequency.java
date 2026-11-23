/*
 * Q6. Count word frequency in a paragraph using HashMap, then print
 *     (a) alphabetically using TreeMap and
 *     (b) by highest frequency using a Comparator.
 */
import java.util.*;

public class Q06_WordFrequency {
    public static void main(String[] args) {
        String text = "Java is object oriented and Java is platform independent "
                    + "and object oriented design makes Java code reusable";

        Map<String, Integer> freq = new HashMap<>();
        for (String w : text.toLowerCase().split("\\s+")) {
            freq.merge(w, 1, Integer::sum);
        }

        System.out.println("Alphabetical:");
        new TreeMap<>(freq).forEach((k, v) -> System.out.println("  " + k + " = " + v));

        List<Map.Entry<String, Integer>> list = new ArrayList<>(freq.entrySet());
        list.sort(Map.Entry.<String, Integer>comparingByValue().reversed()
                  .thenComparing(Map.Entry.comparingByKey()));

        System.out.println("By frequency:");
        for (Map.Entry<String, Integer> e : list) {
            System.out.println("  " + e.getKey() + " = " + e.getValue());
        }
    }
}
