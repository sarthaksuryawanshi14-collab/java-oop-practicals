/*
 * Q2. Two interfaces have the same default method hello(). A class implements both.
 *     Resolve the diamond conflict and also call each interface's version.
 *     Also show a static interface method.
 */
interface Walker {
    default String move() { return "Walking"; }
    static String info() { return "Walker interface"; }
}

interface Swimmer {
    default String move() { return "Swimming"; }
}

class Duck implements Walker, Swimmer {
    // Compile error unless we override: class must resolve the conflict.
    @Override
    public String move() {
        return Walker.super.move() + " and " + Swimmer.super.move();
    }
}

public class Q02_DiamondInterfaces {
    public static void main(String[] args) {
        Duck d = new Duck();
        System.out.println(d.move());
        System.out.println(Walker.info());   // static method called via interface name
    }
}
