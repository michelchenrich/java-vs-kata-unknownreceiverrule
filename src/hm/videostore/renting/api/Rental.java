package hm.videostore.renting.api;

import hm.videostore.statement.api.StatementItem;
import java.math.BigDecimal;
import java.time.LocalDate;

public interface Rental {
    StatementItem returnOn(LocalDate returnDate);

    BigDecimal getPriceReturnedOn(LocalDate returnDate);
}
