package service;
import exception.*;
import model.*;
import model.annotation.Audit;
import util.*;
import java.lang.reflect.Method;
import java.util.List;

public class BankService {
    private final Repository<Account> repo = new Repository<>();

    private void logAudit(String methodName) {
        try {
            for (Method m : BankService.class.getDeclaredMethods()) {
                if (m.getName().equals(methodName) && m.isAnnotationPresent(Audit.class)) {
                    System.out.println("[Audit] Action invoked: " + m.getAnnotation(Audit.class).action());
                    break;
                }
            }
        } catch (Exception ignored) {}
    }

    @Audit(action = "OPEN_ACCOUNT")
    public Account openAccount(String accNo, String owner, String mobile, long openingBalance)
            throws BankException {
        logAudit("openAccount");
        if (!Validator.isValidMobile(mobile)) {
            throw new BankException("Invalid mobile number: " + mobile);
        }
        Account acc = new Account(accNo, owner, openingBalance);
        return repo.save(acc);
    }

    @Audit(action = "DEPOSIT")
    public void deposit(String accNo, long amount) throws BankException {
        logAudit("deposit");
        Account acc = getRequired(accNo);
        acc.deposit(amount);
    }

    @Audit(action = "WITHDRAW")
    public void withdraw(String accNo, long amount) throws BankException {
        logAudit("withdraw");
        Account acc = getRequired(accNo);
        acc.withdraw(amount);
    }

    @Audit(action = "TRANSFER")
    public void transfer(String fromAcc, String toAcc, long amount) throws BankException {
        logAudit("transfer");
        Account from = getRequired(fromAcc);
        Account to = getRequired(toAcc);
        from.withdraw(amount);
        to.deposit(amount);
    }

    public String statement(String accNo) throws BankException {
        Account acc = getRequired(accNo);
        return StatementFormatter.buildStatement(acc);
    }

    public List<Account> listAccounts() {
        return repo.findAll();
    }

    public Account getRequired(String accNo) throws AccountNotFoundException {
        Account acc = repo.findById(accNo);
        if (acc == null) throw new AccountNotFoundException(accNo);
        return acc;
    }
}
