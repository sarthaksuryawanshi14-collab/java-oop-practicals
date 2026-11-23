/*
 * Q7. Write (a) a generic Stack<T> with push/pop/peek and (b) a bounded generic
 *     method max(T[] arr) where T extends Comparable<T>.
 */
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

class MyStack<T> {
    private final List<T> items = new ArrayList<>();

    void push(T item) { items.add(item); }

    T pop() {
        if (items.isEmpty()) throw new EmptyStackException();
        return items.remove(items.size() - 1);
    }

    T peek() {
        if (items.isEmpty()) throw new EmptyStackException();
        return items.get(items.size() - 1);
    }

    boolean isEmpty() { return items.isEmpty(); }
}

public class Q07_Generics {
    static <T extends Comparable<T>> T max(T[] arr) {
        T best = arr[0];
        for (T x : arr) if (x.compareTo(best) > 0) best = x;
        return best;
    }

    public static void main(String[] args) {
        MyStack<String> st = new MyStack<>();
        st.push("A"); st.push("B"); st.push("C");
        System.out.println("peek = " + st.peek());
        while (!st.isEmpty()) System.out.println("pop  = " + st.pop());

        System.out.println("max Integer = " + max(new Integer[]{4, 19, 7}));
        System.out.println("max String  = " + max(new String[]{"pear", "apple", "zebra"}));
        try {
            st.pop();
        } catch (EmptyStackException e) {
            System.out.println("Stack empty -> EmptyStackException caught");
        }
    }
}
