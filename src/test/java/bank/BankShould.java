package bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.time.LocalDate.now;
import static java.util.List.of;
import static org.mockito.Mockito.*;

public class BankShould {

    private static final int AMOUNT = 100;

    private TransactionRepository transactionRepository;
    private Printer printer;
    private Bank bank;

    @BeforeEach
    void init() {
        transactionRepository = spy(TransactionRepository.class);
        printer = mock(Printer.class);
        bank = new Bank(transactionRepository, printer);
    }

    @Test
    void make_deposit() {
        Transaction transaction = new Transaction(AMOUNT, now());
        bank.deposit(AMOUNT);
        verify(transactionRepository).add(transaction);
    }

    @Test
    void make_withdrawal() {
        Transaction transaction = new Transaction(-AMOUNT, now());
        bank.withdraw(AMOUNT);
        verify(transactionRepository).add(transaction);
    }

    @Test
    void print_statement() {
        when(transactionRepository.findAll()).thenReturn(allTransactions());
        bank.printStatement();
        verify(transactionRepository).findAll();
        verify(printer).print(allTransactions());
    }

    private List<Transaction> allTransactions() {
        return of(
                new Transaction(100, now()),
                new Transaction(200, now()),
                new Transaction(-50, now())
        );
    }
}