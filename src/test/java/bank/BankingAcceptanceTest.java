package bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class BankingAcceptanceTest {

    private Output output;
    private TransactionRepository transactionRepository;
    private Printer printer;
    private Bank bank;

    @BeforeEach
    void init() {
        output = spy(Output.class);
        transactionRepository = mock(TransactionRepository.class);
        printer = mock(Printer.class);
        bank = new Bank(transactionRepository, printer);
    }

    @Test
    void print_a_statement_showing_posted_transactions() {
        bank.deposit(100);
        bank.deposit(200);
        bank.withdraw(50);

        bank.printStatement();

        verify(output).print("| Date | Amount | Balance |\n" + "| 03/01/2020 | -50 | 250 |\n" + "| 02/01/2020 | 200 | 300 |\n" + "| 01/01/2020 | 100 | 100 |\n");
    }
}
