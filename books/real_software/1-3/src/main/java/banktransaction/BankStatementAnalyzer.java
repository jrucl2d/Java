package banktransaction;

import banktransaction.parser.BankStatementParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {
    public void analyze(final String fileName, final BankStatementParser bankStatementParser) throws IOException {
        final Path path = Paths.get(fileName);
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        collectSummary(bankStatementProcessor);
    }

    private static void collectSummary(final BankStatementProcessor bankStatementProcessor) {
        System.out.println("모든 거래 내역의 합은 " + bankStatementProcessor.calculateTotalAmount());
        System.out.println("1월의 모든 거래 내역의 합은 " + bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
        System.out.println("2월의 모든 거래 내역의 합은 " + bankStatementProcessor.calculateTotalInMonth(Month.FEBRUARY));
        System.out.println("salary 카테고리 거래 내역의 합은 " + bankStatementProcessor.calculateTotalForCategory("Salary"));

        final List<BankTransaction> transactions = bankStatementProcessor.findTransactionsGreaterThanEqual(1_000);
        System.out.println("총 거래액 1000 이상은 " + transactions);


    }
}
