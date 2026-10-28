package service;
import model.SavingsAccount;
import util.AnnotationValidator;
import java.util.Arrays;

public class MiniBank {
    public static void main(String[] args) throws Exception {
        SavingsAccount invalidAcc = new SavingsAccount("AC0001", "Riya", -100, 500);
        SavingsAccount validAcc   = new SavingsAccount("AC0002", "Aarav", 5000, 500);

        System.out.println("Invalid account errors: " + Arrays.toString(AnnotationValidator.validate(invalidAcc)));
        System.out.println("Valid account errors:   " + Arrays.toString(AnnotationValidator.validate(validAcc)));
    }
}
