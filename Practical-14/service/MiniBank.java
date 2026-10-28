package service;
import exception.BankException;
import java.util.Scanner;

public class MiniBank {
    public static void main(String[] args) {
        BankService service = new BankService();
        Scanner sc = new Scanner(System.in);
        System.out.println("=== MiniBank Capstone Service ===");
        try {
            service.openAccount("AC0001", "Riya", "9876543210", 5000);
            service.deposit("AC0001", 2000);
            System.out.print(service.statement("AC0001"));
        } catch (BankException e) {
            System.out.println("Error: " + e.getMessage());
        }
        sc.close();
    }
}
