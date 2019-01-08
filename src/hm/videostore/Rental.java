package hm.videostore;

import java.time.LocalDate;

public class Rental {
    private StatementItemFactory statementItemFactory;
    private Movie movie;
    private LocalDate rentalDate;

    public Rental(Movie movie, LocalDate rentalDate) {
        this(StatementItem::new, movie, rentalDate);
    }

    public Rental(StatementItemFactory statementItemFactory, Movie movie, LocalDate rentalDate) {
        this.statementItemFactory = statementItemFactory;
        this.movie = movie;
        this.rentalDate = rentalDate;
    }

    public StatementItem returnOn(LocalDate returnDate) {
        return statementItemFactory.make(this, returnDate);
    }

    public double getPriceReturnedOn(LocalDate returnDate) {
        long daysRented = returnDate.toEpochDay() - rentalDate.toEpochDay() + 1;
        return movie.getPriceWhenRentedForDays(daysRented);
    }
}
