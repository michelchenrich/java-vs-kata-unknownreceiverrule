package hm.videostore.renting;

import hm.reuse.ListFactory;
import hm.videostore.movie.api.Movie;
import hm.videostore.renting.api.Rental;
import hm.videostore.renting.api.RentalFactory;
import hm.videostore.renting.api.Rentals;
import hm.videostore.statement.api.StatementItem;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

class RentedMovies implements Rentals {
    private RentalFactory rentalFactory;
    private List<Rental> list;

    RentedMovies(RentalFactory rentalFactory, ListFactory listFactory) {
        this.rentalFactory = rentalFactory;
        this.list = listFactory.makeList();
    }

    public void add(Movie movie, LocalDate rentalDate) {
        list.add(rentalFactory.makeRental(movie, rentalDate));
    }

    public List<StatementItem> returnAllOn(LocalDate returnDate) {
        var statementItems = list.stream().map(rental -> rental.returnOn(returnDate))
                                 .collect(Collectors.toList());
        list.clear();
        return statementItems;
    }
}
