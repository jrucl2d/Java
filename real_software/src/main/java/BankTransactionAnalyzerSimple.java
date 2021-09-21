import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankTransactionAnalyzerSimple {
    private static final String fileName = "src/main/resources/info.csv";

    public static void main(String[] args) throws IOException {
        final Path path = Paths.get(fileName);
        final List<String> lines = Files.readAllLines(path);

        final BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();

        final List<BankTransaction> bankTransactions = bankStatementCSVParser.parseLinesFromCSV(lines);

        System.out.println("모든 거래 내역의 합은 " + calculateTotalAmount(bankTransactions));
        System.out.println("1월의 모든 거래 내역은 " + selectInMonth(bankTransactions, Month.JANUARY));
    }

    private static List<BankTransaction> selectInMonth(final List<BankTransaction> bankTransactions, final Month month) {
        final List<BankTransaction> bankTransactionInMonth = new ArrayList<>();
        for (final BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDate().getMonth() == month) {
                bankTransactionInMonth.add(bankTransaction);
            }
        }
        return bankTransactionInMonth;
    }

    private static double calculateTotalAmount(final List<BankTransaction> bankTransactions) {
        double total = 0d;
        for (final BankTransaction bankTransaction : bankTransactions) {
            total += bankTransaction.getAmount();
        }
        return total;
    }
}
