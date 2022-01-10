package bank;

import java.util.List;

public class Bank {

    private final Clock clock;
    private final TransactionRepository transactionRepository;
    private final Printer printer;

    public Bank(Clock clock, TransactionRepository transactionRepository, Printer printer) {
        this.clock = clock;
        this.transactionRepository = transactionRepository;
        this.printer = printer;
    }

    public void deposit(int amount) {
        Transaction transaction = new Transaction(amount, clock.getDate());
        this.transactionRepository.add(transaction);
    }

    public void withdraw(int amount) {
        Transaction transaction = new Transaction(-amount, clock.getDate());
        this.transactionRepository.add(transaction);
    }

    public void printStatement() {
        List<Transaction> allTransactions = this.transactionRepository.getTransactions();
        this.printer.print(allTransactions);
    }
}
