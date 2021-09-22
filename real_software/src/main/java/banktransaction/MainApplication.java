package banktransaction;

import banktransaction.parser.BankStatementCSVParser;
import banktransaction.parser.BankStatementParser;

import java.io.IOException;

public class MainApplication {
    private static final String fileName = "src/main/resources/info.csv";

    public static void main(String[] args) throws IOException {
        final BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();
        final BankStatementParser bankStatementParser = new BankStatementCSVParser();

        bankStatementAnalyzer.analyze(fileName, bankStatementParser);
    }
}
