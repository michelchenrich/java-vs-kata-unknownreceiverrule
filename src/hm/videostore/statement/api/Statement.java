package hm.videostore.statement.api;

import hm.videostore.renting.api.Renter;
import java.time.LocalDate;
import java.util.List;

public interface Statement {
    Renter getRenter();

    LocalDate getDate();

    List<StatementItem> getItems();

    double getTotal();
}
