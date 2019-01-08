package hm.videostore;

import java.time.LocalDate;

public interface StatementItemFactory {
    StatementItem make(Rental rental, LocalDate returnDate);
}
