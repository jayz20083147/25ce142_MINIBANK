package service;
import exception.*;
import model.Account;

public class BankServiceTest {
    public static void testDepositIncreasesBalance() throws Exception {
        BankService svc = new BankService();
        svc.openAccount("AC0001", "Riya", "9876543210", 5000);
        svc.deposit("AC0001", 1500);
        assert svc.getRequired("AC0001").getBalance() == 6500 : "Balance should be 6500";
        System.out.println("PASS: testDepositIncreasesBalance");
    }

    public static void testOverLimitWithdrawThrows() throws Exception {
        BankService svc = new BankService();
        svc.openAccount("AC0001", "Riya", "9876543210", 2000);
        try {
            svc.withdraw("AC0001", 5000);
            throw new AssertionError("Expected InsufficientFundsException");
        } catch (InsufficientFundsException e) {
            System.out.println("PASS: testOverLimitWithdrawThrows (" + e.getMessage() + ")");
        }
    }

    public static void testConcurrentDepositsKeepBalanceCorrect() throws Exception {
        BankService svc = new BankService();
        Account acc = svc.openAccount("AC0001", "Riya", "9876543210", 1000);
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) acc.depositSync(10);
            });
            threads[i].start();
        }
        for (Thread t : threads) t.join();
        assert acc.getBalance() == 11000 : "Expected 11000";
        System.out.println("PASS: testConcurrentDepositsKeepBalanceCorrect (Final Balance: " + acc.getBalance() + ")");
    }

    public static void main(String[] args) throws Exception {
        testDepositIncreasesBalance();
        testOverLimitWithdrawThrows();
        testConcurrentDepositsKeepBalanceCorrect();
    }
}
