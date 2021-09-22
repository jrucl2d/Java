package banktransaction.functionalinterfaces;

import banktransaction.SummaryStatistics;

@FunctionalInterface
public interface Exporter {
    String export(SummaryStatistics summaryStatistics);
}
