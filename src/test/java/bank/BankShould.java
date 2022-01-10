package bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.time.LocalDate.now;
import static java.util.List.of;
import static org.mockito.Mockito.*;

class BankShould {

    private static final int AMOUNT_100 = 100;
    private static final int AMOUNT_200 = 200;
    private static final int AMOUNT_50 = 50;

    private Clock clock;
    private TransactionRepository transactionRepository;
    private Printer printer;
    private Bank bank;

    @BeforeEach
    void init() {
        clock = spy(Clock.class);
        transactionRepository = spy(TransactionRepository.class);
        printer = mock(Printer.class);
        bank = new Bank(clock, transactionRepository, printer);
    }

    @Test
    void make_deposit() {
        Transaction transaction = new Transaction(AMOUNT_100, now());
        bank.deposit(AMOUNT_100);
        verify(transactionRepository).add(transaction);
    }

    @Test
    void make_withdrawal() {
        Transaction transaction = new Transaction(-AMOUNT_100, now());
        bank.withdraw(AMOUNT_100);
        verify(transactionRepository).add(transaction);
    }

    @Test
    void print_statement() {
        when(transactionRepository.getTransactions()).thenReturn(allTransactions());
        bank.printStatement();
        verify(transactionRepository).getTransactions();
        verify(printer).print(allTransactions());
    }

    private List<Transaction> allTransactions() {
        return of(
                new Transaction(AMOUNT_100, now()),
                new Transaction(AMOUNT_200, now()),
                new Transaction(-AMOUNT_50, now())
        );
    }
}