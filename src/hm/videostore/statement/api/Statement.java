package hm.videostore.statement.api;

import hm.videostore.renting.api.Renter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface Statement {
    Renter getRenter();

    LocalDate getDate();

    List<StatementItem> getItems();

    BigDecimal getTotal();
}
