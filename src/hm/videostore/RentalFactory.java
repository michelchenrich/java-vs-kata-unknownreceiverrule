package hm.videostore;

import java.time.LocalDate;

public interface RentalFactory {
    Rental make(Movie movie, LocalDate rentalDate);
}
