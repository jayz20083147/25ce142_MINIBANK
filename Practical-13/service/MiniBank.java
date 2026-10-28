package service;
import model.*;

public class MiniBank {
    public static void main(String[] args) {
        Repository<Account> accRepo = new Repository<>();
        accRepo.saveAll(
            new Account("AC0001", "Riya", 5000),
            new Account("AC0002", "Aarav", 8000),
            new Account("AC0003", "Kabir", 11000)
        );

        System.out.println("findById('AC0002'): " + accRepo.findById("AC0002"));
        System.out.println("All Accounts: " + accRepo.findAll());
        Long[] balances = {5000L, 8000L, 11000L};
        System.out.println("Average Balance: Rs. " + Repository.average(balances));

        Repository<Customer> custRepo = new Repository<>();
        custRepo.save(new Customer("CUST101", "Riya"));
        System.out.println("Customer findById('CUST101'): " + custRepo.findById("CUST101"));
    }
}
