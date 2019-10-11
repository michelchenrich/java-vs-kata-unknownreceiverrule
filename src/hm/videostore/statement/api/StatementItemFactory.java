package hm.videostore.statement.api;

import hm.videostore.renting.api.Rental;
import java.time.LocalDate;

public interface StatementItemFactory {
    StatementItem makeStatementItem(Rental rental, LocalDate returnDate);
}
