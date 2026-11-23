/*
 * Q8. Demonstrate all four kinds of nested classes: static nested, inner (member),
 *     local, and anonymous. Show that the inner class can access outer private fields.
 */
interface Greeter { String greet(); }

public class Q08_InnerClasses {
    private String secret = "outer-private";
    private static int instances = 0;

    Q08_InnerClasses() { instances++; }

    // 1. Static nested class: no outer instance needed
    static class Helper {
        String info() { return "Static nested: instances created = " + instances; }
    }

    // 2. Inner class: tied to an outer instance
    class Inner {
        String reveal() { return "Inner accesses -> " + secret; }
    }

    String demoLocalAndAnonymous() {
        // 3. Local class
        class Local {
            String hi() { return "Local class sees secret: " + secret; }
        }
        // 4. Anonymous class
        Greeter g = new Greeter() {
            @Override public String greet() { return "Anonymous class says hello"; }
        };
        return new Local().hi() + "\n" + g.greet();
    }

    public static void main(String[] args) {
        Q08_InnerClasses outer = new Q08_InnerClasses();
        System.out.println(new Helper().info());
        Q08_InnerClasses.Inner in = outer.new Inner();
        System.out.println(in.reveal());
        System.out.println(outer.demoLocalAndAnonymous());
    }
}
