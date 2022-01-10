package bank;

public class BankApplication {

    public static void main(String[] args) {
        Output output = new Output();
        Clock clock = new Clock();
        TransactionRepository transactionRepository = new TransactionRepository();
        Printer printer = new Printer(output);
        Bank bank = new Bank(clock, transactionRepository, printer);

        bank.deposit(100);
        bank.deposit(200);
        bank.withdraw(50);

        bank.printStatement();
    }
}
