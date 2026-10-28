package service;
import model.Account;

public class MiniBank {
    public static void main(String[] args) throws InterruptedException {
        Account a = new Account("AC0001", "Riya", 5000);
        Account b = new Account("AC0002", "Aarav", 5000);

        TransactionProcessor processor = new TransactionProcessor();
        for (int i = 0; i < 5; i++) {
            processor.submit(() -> TransactionProcessor.transferOrdered(a, b, 200));
            processor.submit(() -> TransactionProcessor.transferOrdered(b, a, 100));
        }
        processor.stop();

        System.out.println("Ordered transfers finished without deadlock.");
        System.out.println("Account AC0001 Balance: " + a.getBalance());
        System.out.println("Account AC0002 Balance: " + b.getBalance());
    }
}
