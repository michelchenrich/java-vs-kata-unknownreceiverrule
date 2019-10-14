package hm.videostore.statement;

import hm.videostore.renting.api.Rental;
import hm.videostore.statement.api.StatementItem;
import java.math.BigDecimal;
import java.time.LocalDate;

class ReturnedItem implements StatementItem {
    private Rental rental;
    private LocalDate returnDate;

    ReturnedItem(Rental rental, LocalDate returnDate) {
        this.rental = rental;
        this.returnDate = returnDate;
    }

    public BigDecimal getPrice() {
        return rental.getPriceReturnedOn(returnDate);
    }
}
