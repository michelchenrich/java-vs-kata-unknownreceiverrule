package hm.videostore.renting.api;

import hm.videostore.movie.api.Movie;
import hm.videostore.statement.api.Statement;
import java.time.LocalDate;

public interface Renter {
    void rent(Movie regularMovie, LocalDate rentalDate);

    Statement returnRentalsOn(LocalDate returnDate);
}
