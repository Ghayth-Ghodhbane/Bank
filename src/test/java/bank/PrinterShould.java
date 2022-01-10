package bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.List;

import static java.time.LocalDate.of;
import static java.util.List.of;
import static org.mockito.Mockito.*;

class PrinterShould {

    private static final int AMOUNT_100 = 100;
    private static final int AMOUNT_200 = 200;
    private static final int AMOUNT_50 = 50;

    private Clock clock;
    private Output output;

    @BeforeEach
    void init() {
        output = mock(Output.class);
        clock = mock(Clock.class);
    }

    @Test
    void print_header() {
        Printer printer = new Printer(output);
        printer.print(allTransactions());
        verify(output).print("| Date | Amount | Balance |");
    }

    @Test
    void print_statements_in_reverse_order() {
        when(clock.getDate())
                .thenReturn(of(2020, 01, 03))
                .thenReturn(of(2020, 01, 02))
                .thenReturn(of(2020, 01, 01));

        Printer printer = new Printer(output);
        printer.print(allTransactions());
        InOrder inOrder = inOrder(output);
        inOrder.verify(output).print("| 03/01/2020 | -50 | 250 |");
        inOrder.verify(output).print("| 02/01/2020 | 200 | 300 |");
        inOrder.verify(output).print("| 01/01/2020 | 100 | 100 |");
    }

    private List<Transaction> allTransactions() {
        return of(
                new Transaction(AMOUNT_100, of(2020, 01, 01)),
                new Transaction(AMOUNT_200, of(2020, 01, 02)),
                new Transaction(-AMOUNT_50, of(2020, 01, 03))
        );
    }
}