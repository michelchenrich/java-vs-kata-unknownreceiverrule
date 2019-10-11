package hm.videostore.renting.api;

import hm.videostore.movie.api.Movie;
import hm.videostore.statement.api.StatementItem;
import java.time.LocalDate;
import java.util.List;

public interface Rentals {
    void add(Movie movie, LocalDate rentalDate);

    List<StatementItem> returnAllOn(LocalDate returnDate);
}
