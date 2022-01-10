package bank;

import java.time.LocalDate;
import java.util.List;

public class Bank {

    private final TransactionRepository transactionRepository;
    private final Printer printer;

    public Bank(TransactionRepository transactionRepository, Printer printer) {
        this.transactionRepository = transactionRepository;
        this.printer = printer;
    }

    public void deposit(int amount) {
        Transaction transaction = new Transaction(amount, LocalDate.now());
        this.transactionRepository.add(transaction);
    }

    public void withdraw(int amount) {
        Transaction transaction = new Transaction(-amount, LocalDate.now());
        this.transactionRepository.add(transaction);
    }

    public void printStatement() {
        List<Transaction> allTransactions = this.transactionRepository.getTransactions();
        this.printer.print(allTransactions);
    }
}
