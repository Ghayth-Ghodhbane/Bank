package bank;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.time.LocalDate.now;
import static java.util.Collections.singletonList;
import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;

class TransactionRepositoryShould {

    private static final int AMOUNT_100 = 100;

    Transaction transaction1 = new Transaction(AMOUNT_100, now());
    Transaction transaction2 = new Transaction(-AMOUNT_100, now());

    @Test
    void have_no_transaction_initially() {
        TransactionRepository transactionRepository = new TransactionRepository();
        List<Transaction> transactions = transactionRepository.getTransactions();
        assertThat(transactions).isEmpty();
    }

    @Test
    void add_transaction() {
        TransactionRepository transactionRepository = new TransactionRepository();
        transactionRepository.add(transaction1);
        List<Transaction> transactions = transactionRepository.getTransactions();
        assertThat(transactions).isEqualTo(singletonList(transaction1));
    }

    @Test
    void retrieve_all_transactions() {
        TransactionRepository transactionRepository = new TransactionRepository();
        transactionRepository.add(transaction1);
        transactionRepository.add(transaction2);
        List<Transaction> transactions = transactionRepository.getTransactions();
        assertThat(transactions).isEqualTo(allTransactions());
    }

    private List<Transaction> allTransactions() {
        return of(
                transaction1,
                transaction2
        );
    }
}