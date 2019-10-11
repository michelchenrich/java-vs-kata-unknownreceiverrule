package hm.videostore.renting;

import hm.videostore.movie.api.Movie;
import hm.videostore.renting.api.Rental;
import hm.videostore.renting.api.RentalFactory;
import hm.videostore.statement.api.StatementItemFactory;
import java.time.LocalDate;

public class RentedMovieFactory implements RentalFactory {
    private StatementItemFactory statementItemFactory;

    public RentedMovieFactory(StatementItemFactory statementItemFactory) {
        this.statementItemFactory = statementItemFactory;
    }

    public Rental makeRental(Movie movie, LocalDate rentalDate) {
        return new RentedMovie(statementItemFactory, movie, rentalDate);
    }
}
