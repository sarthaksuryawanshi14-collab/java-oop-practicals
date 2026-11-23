/*
 * Q9. Create an Employee class that correctly overrides equals() and hashCode(),
 *     implements Comparable (by salary), and show the behaviour in HashSet and TreeSet.
 *     Also sort using a Comparator (by name).
 */
import java.util.*;

class Employee implements Comparable<Employee> {
    private final int id;
    private final String name;
    private final double salary;

    Employee(int id, String name, double salary) {
        this.id = id; this.name = name; this.salary = salary;
    }

    String getName() { return name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        return id == ((Employee) o).id;            // identity = id
    }

    @Override
    public int hashCode() { return Integer.hashCode(id); }

    @Override
    public int compareTo(Employee other) {         // natural order: salary ascending
        return Double.compare(salary, other.salary);
    }

    @Override
    public String toString() { return name + "(" + id + ", " + salary + ")"; }
}

public class Q09_EqualsHashCode {
    public static void main(String[] args) {
        Employee a = new Employee(1, "Ravi", 50000);
        Employee b = new Employee(1, "Ravi Duplicate", 50000);   // same id
        Employee c = new Employee(2, "Sita", 70000);
        Employee d = new Employee(3, "Amit", 30000);

        Set<Employee> hash = new HashSet<>(List.of(a, c, d));
        System.out.println("a.equals(b)        : " + a.equals(b));
        System.out.println("HashSet contains b : " + hash.contains(b));
        hash.add(b);
        System.out.println("HashSet size after adding b: " + hash.size() + " (duplicate ignored)");

        Set<Employee> tree = new TreeSet<>(hash);
        System.out.println("TreeSet (by salary): " + tree);

        List<Employee> list = new ArrayList<>(hash);
        list.sort(Comparator.comparing(Employee::getName));
        System.out.println("Sorted by name     : " + list);
    }
}
