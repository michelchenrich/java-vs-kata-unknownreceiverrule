package hm.videostore;

import hm.reuse.ListFactory;
import hm.videostore.customer.Customer;
import hm.videostore.movie.RegularMovie;
import hm.videostore.movie.api.Movie;
import hm.videostore.movie.api.MovieFactory;
import hm.videostore.renting.RentedMovie;
import hm.videostore.renting.RentedMovies;
import hm.videostore.renting.api.Rental;
import hm.videostore.renting.api.RentalFactory;
import hm.videostore.renting.api.Rentals;
import hm.videostore.renting.api.RentalsFactory;
import hm.videostore.renting.api.Renter;
import hm.videostore.renting.api.RenterFactory;
import hm.videostore.statement.ReturnStatement;
import hm.videostore.statement.ReturnedItem;
import hm.videostore.statement.api.Statement;
import hm.videostore.statement.api.StatementFactory;
import hm.videostore.statement.api.StatementItem;
import hm.videostore.statement.api.StatementItemFactory;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Factory implements ListFactory,
                                MovieFactory,
                                RentalFactory,
                                RentalsFactory,
                                RenterFactory,
                                StatementFactory,
                                StatementItemFactory {
    public <E> List<E> makeList() {
        return new LinkedList<>();
    }

    public Movie makeRegularMovie() {
        return new RegularMovie();
    }

    public Rental makeRental(Movie movie, LocalDate rentalDate) {
        return new RentedMovie(this, movie, rentalDate);
    }

    public Rentals makeRentals() {
        return new RentedMovies(this, this);
    }

    public Renter makeRenter() {
        return new Customer(this, makeRentals());
    }

    public Statement makeStatement(Renter renter, LocalDate returnDate, List<StatementItem> statementItems) {
        return new ReturnStatement(renter, returnDate, statementItems);
    }

    public StatementItem makeStatementItem(Rental rental, LocalDate returnDate) {
        return new ReturnedItem(rental, returnDate);
    }
}
