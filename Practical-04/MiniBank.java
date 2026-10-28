public class MiniBank {
    public static void main(String[] args) {
        System.out.println("isValidMobile('9876543210'): " + Validator.isValidMobile("9876543210"));
        System.out.println("isValidMobile('12345'): " + Validator.isValidMobile("12345"));
        System.out.println("isValidPan('ABCDE1234F'): " + Validator.isValidPan("ABCDE1234F"));
        System.out.println("isValidIfsc('SBIN0001234'): " + Validator.isValidIfsc("SBIN0001234"));

        Command cmd = CommandParser.parse("DEPOSIT AC0001 500");
        System.out.println("Parsed: " + cmd);

        Account acc = new Account("AC0001", "Riya", 4500);
        System.out.print(StatementFormatter.buildStatement(acc));
    }
}
