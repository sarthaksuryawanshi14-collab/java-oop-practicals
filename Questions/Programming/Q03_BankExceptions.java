/*
 * Q3. Bank system with custom checked exception InsufficientFundsException,
 *     try-with-resources (AutoCloseable), finally block and exception chaining.
 */
class InsufficientFundsException extends Exception {
    InsufficientFundsException(String msg) { super(msg); }
    InsufficientFundsException(String msg, Throwable cause) { super(msg, cause); }
}

class Account {
    private double balance;
    Account(double balance) { this.balance = balance; }

    void withdraw(double amt) throws InsufficientFundsException {
        if (amt <= 0) throw new IllegalArgumentException("Amount must be positive");
        if (amt > balance) {
            throw new InsufficientFundsException("Need " + (amt - balance) + " more");
        }
        balance -= amt;
    }

    double getBalance() { return balance; }
}

class Transaction implements AutoCloseable {
    private final String id;
    Transaction(String id) { this.id = id; System.out.println("Transaction " + id + " opened"); }
    @Override
    public void close() { System.out.println("Transaction " + id + " closed"); }
}

public class Q03_BankExceptions {
    public static void main(String[] args) {
        Account acc = new Account(1000);

        try (Transaction t = new Transaction("T1")) {
            acc.withdraw(400);
            System.out.println("Balance: " + acc.getBalance());
            acc.withdraw(900);                       // fails
        } catch (InsufficientFundsException e) {
            System.out.println("Failed: " + e.getMessage());
        } finally {
            System.out.println("finally always runs");
        }

        // Exception chaining
        try {
            try {
                acc.withdraw(-5);
            } catch (IllegalArgumentException e) {
                throw new InsufficientFundsException("Withdrawal rejected", e);
            }
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage() + " <- cause: " + e.getCause().getMessage());
        }
    }
}
