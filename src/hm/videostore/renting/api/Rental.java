package hm.videostore.renting.api;

import hm.videostore.statement.api.StatementItem;
import java.time.LocalDate;

public interface Rental {
    StatementItem returnOn(LocalDate returnDate);

    double getPriceReturnedOn(LocalDate returnDate);
}
