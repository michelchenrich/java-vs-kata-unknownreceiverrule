package hm.videostore;

import java.time.LocalDate;
import java.util.List;

public interface StatementFactory {
    Statement make(Customer customer, LocalDate returnDate, List<StatementItem> statementItems);
}
