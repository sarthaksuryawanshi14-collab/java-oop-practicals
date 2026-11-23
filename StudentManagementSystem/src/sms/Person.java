package sms;

/**
 * Abstraction: base class for everyone in the system.
 * Encapsulation: fields are private, accessed via getters/setters.
 */
public abstract class Person {
    private final int id;
    private String name;

    protected Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    /** Abstract method: every subclass must define its own role. */
    public abstract String getRole();

    @Override
    public String toString() {
        return getRole() + " [id=" + id + ", name=" + name + "]";
    }
}
