package sms;

/** Interface: anything that can be graded. */
public interface Gradable {
    double percentage();

    /** Default method: grade is derived from percentage. */
    default String grade() {
        double p = percentage();
        if (p >= 75) return "A";
        if (p >= 60) return "B";
        if (p >= 40) return "C";
        return "F";
    }
}
