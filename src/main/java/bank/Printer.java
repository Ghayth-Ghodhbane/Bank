package bank;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.stream.Collectors.toCollection;

public class Printer {

    private static final String HEADER = "| Date | Amount | Balance |";

    private final Output output;

    public Printer(Output output) {
        this.output = output;
    }

    void print(List<Transaction> transactions) {
        output.print(HEADER);
        printTransactions(transactions);
    }

    private void printTransactions(List<Transaction> transactions) {
        AtomicInteger balance = new AtomicInteger(0);
        transactions.stream()
                .map(transaction -> toStatement(transaction, balance))
                .collect(toCollection(LinkedList::new))
                .descendingIterator()
                .forEachRemaining(output::print);
    }

    private String toStatement(Transaction transaction, AtomicInteger balance) {
        return "| " +
                transaction.getDate().format(ofPattern("dd/MM/yyyy")) +
                " | " +
                transaction.getAmount() +
                " | " +
                balance.addAndGet(transaction.getAmount()) +
                " |";
    }
}
