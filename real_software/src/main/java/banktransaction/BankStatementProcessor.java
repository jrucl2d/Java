package banktransaction;

import banktransaction.functionalinterfaces.BankTransactionFilter;
import banktransaction.functionalinterfaces.BankTransactionSummarizer;

import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double summarizeTransactions(final BankTransactionSummarizer bankTransactionSummarizer) {
        double result = 0d;
        for (final BankTransaction bankTransaction : bankTransactions) {
            result = bankTransactionSummarizer.summarize(result, bankTransaction);
        }
        return result;
    }

    public double calculateTotalAmount() {
        return summarizeTransactions((acc, bankTransaction) -> acc + bankTransaction.getAmount());
    }

    public double calculateTotalInMonth(final Month month) {
        return summarizeTransactions((acc, bankTransaction) ->
                bankTransaction.getDate().getMonth() == month ? acc + bankTransaction.getAmount() : acc);
    }

    public double calculateTotalForCategory(final String category) {
        return summarizeTransactions((acc, bankTransaction) ->
                bankTransaction.getDescription().equals(category) ? acc + bankTransaction.getAmount() : acc);
    }

    public List<BankTransaction> findTransactions(final BankTransactionFilter bankTransactionFilter) {
        return bankTransactions.stream()
                .filter(bankTransactionFilter::test)
                .collect(Collectors.toList());
    }

    public List<BankTransaction> findTransactionsGreaterThanEqual(final int amount) {
        return findTransactions(bankTransaction -> bankTransaction.getAmount() >= amount);
    }
}
