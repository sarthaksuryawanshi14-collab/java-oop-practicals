/*
 * Q10. Implement (a) a thread-safe Singleton (double-checked locking) and
 *      (b) the Builder pattern for an immutable Student object.
 */
final class Config {
    private static volatile Config instance;
    private Config() { System.out.println("Config created"); }

    static Config getInstance() {
        if (instance == null) {                    // 1st check (no lock)
            synchronized (Config.class) {
                if (instance == null) {            // 2nd check (with lock)
                    instance = new Config();
                }
            }
        }
        return instance;
    }
}

final class StudentProfile {                       // immutable: final class, final fields
    private final String name;
    private final int rollNo;
    private final String branch;
    private final String email;

    private StudentProfile(Builder b) {
        this.name = b.name; this.rollNo = b.rollNo; this.branch = b.branch; this.email = b.email;
    }

    static class Builder {
        private final String name;                 // required
        private final int rollNo;                  // required
        private String branch = "Not set";         // optional
        private String email = "Not set";          // optional

        Builder(String name, int rollNo) { this.name = name; this.rollNo = rollNo; }
        Builder branch(String branch) { this.branch = branch; return this; }
        Builder email(String email) { this.email = email; return this; }
        StudentProfile build() { return new StudentProfile(this); }
    }

    @Override
    public String toString() {
        return name + " | roll " + rollNo + " | " + branch + " | " + email;
    }
}

public class Q10_SingletonBuilder {
    public static void main(String[] args) throws InterruptedException {
        Thread[] ts = new Thread[5];
        Config[] got = new Config[5];
        for (int i = 0; i < ts.length; i++) {
            final int idx = i;
            ts[i] = new Thread(() -> got[idx] = Config.getInstance());
            ts[i].start();
        }
        for (Thread t : ts) t.join();
        boolean same = true;
        for (Config c : got) if (c != got[0]) same = false;
        System.out.println("All threads got same instance: " + same);

        StudentProfile p = new StudentProfile.Builder("Aarav", 21)
                .branch("Computer").email("aarav@example.com").build();
        System.out.println(p);
    }
}
