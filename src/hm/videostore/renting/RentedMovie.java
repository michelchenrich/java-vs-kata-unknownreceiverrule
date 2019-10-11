package hm.videostore.renting;

import hm.videostore.movie.api.Movie;
import hm.videostore.renting.api.Rental;
import hm.videostore.statement.api.StatementItem;
import hm.videostore.statement.api.StatementItemFactory;
import java.time.LocalDate;

class RentedMovie implements Rental {
    private StatementItemFactory statementItemFactory;
    private Movie movie;
    private LocalDate rentalDate;

    RentedMovie(StatementItemFactory statementItemFactory, Movie movie, LocalDate rentalDate) {
        this.statementItemFactory = statementItemFactory;
        this.movie = movie;
        this.rentalDate = rentalDate;
    }

    public StatementItem returnOn(LocalDate returnDate) {
        return statementItemFactory.makeStatementItem(this, returnDate);
    }

    public double getPriceReturnedOn(LocalDate returnDate) {
        long daysRented = returnDate.toEpochDay() - rentalDate.toEpochDay() + 1;
        return movie.getPriceWhenRentedForDays(daysRented);
    }
}
