package bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static java.time.LocalDate.of;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class BankingAcceptanceTest {

    private Output output;
    private Clock clock;

    @BeforeEach
    void init() {
        output = spy(Output.class);
        clock = mock(Clock.class);
    }

    @Test
    void print_a_statement_showing_posted_transactions() {
        TransactionRepository transactionRepository = new TransactionRepository();
        Printer printer = new Printer(output);
        Bank bank = new Bank(clock, transactionRepository, printer);

        given(clock.getDate())
                .willReturn(of(2020, 01, 01))
                .willReturn(of(2020, 01, 02))
                .willReturn(of(2020, 01, 03));

        bank.deposit(100);
        bank.deposit(200);
        bank.withdraw(50);

        bank.printStatement();

        InOrder inOrder = inOrder(output);
        inOrder.verify(output).print("| Date | Amount | Balance |");
        inOrder.verify(output).print("| 03/01/2020 | -50 | 250 |");
        inOrder.verify(output).print("| 02/01/2020 | 200 | 300 |");
        inOrder.verify(output).print("| 01/01/2020 | 100 | 100 |");
    }
}
