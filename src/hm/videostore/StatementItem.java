package hm.videostore;

import java.time.LocalDate;

public class StatementItem {
    private Rental rental;
    private LocalDate returnDate;

    public StatementItem(Rental rental, LocalDate returnDate) {
        this.rental = rental;
        this.returnDate = returnDate;
    }

    public double getPrice() {
        return rental.getPriceReturnedOn(returnDate);
    }
}
