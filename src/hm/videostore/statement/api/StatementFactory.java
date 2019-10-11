package hm.videostore.statement.api;

import hm.videostore.renting.api.Renter;
import java.time.LocalDate;
import java.util.List;

public interface StatementFactory {
    Statement makeStatement(Renter renter, LocalDate returnDate, List<StatementItem> statementItems);
}
