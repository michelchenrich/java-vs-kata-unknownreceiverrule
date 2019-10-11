package hm.videostore.renting.api;

import hm.videostore.movie.api.Movie;
import java.time.LocalDate;

public interface RentalFactory {
    Rental makeRental(Movie movie, LocalDate rentalDate);
}
