package hm.videostore.statement;

import hm.videostore.renting.api.Rental;
import hm.videostore.statement.api.StatementItem;
import hm.videostore.statement.api.StatementItemFactory;
import java.time.LocalDate;

public class ReturnedItemFactory implements StatementItemFactory {
    public StatementItem makeStatementItem(Rental rental, LocalDate returnDate) {
        return new ReturnedItem(rental, returnDate);
    }
}
