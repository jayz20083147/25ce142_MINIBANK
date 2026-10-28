package service;
import model.Account;

public class MiniBank {
    public static void main(String[] args) {
        AccountStore store = new AccountStore();
        store.add(new Account("AC0001", "Riya", 5000));
        store.add(new Account("AC0002", "Aarav", 2000));
        store.add(new Account("AC0003", "Kabir", 9000));

        System.out.println("Lookup AC0002: " + store.get("AC0002"));
        System.out.println("Accounts sorted by balance (descending):");
        for (Account a : store.sortedByBalance()) {
            System.out.println("  " + a);
        }
    }
}
