package bank;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

public class BankingAcceptanceTest {

    @Mock
    Output output;

    @Test
    public void print_a_statement_showing_posted_transactions() {
        Bank bank = new Bank();

        bank.deposit(100);
        bank.deposit(200);
        bank.withdraw(50);
        bank.statement();

        verify(output).print("| Date | Amount | Balance |\n" +
                "|03/01/2020|-50|250|\n" +
                "|02/01/2020|200|300|\n" +
                "|01/01/2020|100|100|\n");
    }
}
