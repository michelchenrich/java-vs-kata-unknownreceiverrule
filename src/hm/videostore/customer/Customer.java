package hm.videostore.customer;

import hm.videostore.movie.api.Movie;
import hm.videostore.renting.api.Rentals;
import hm.videostore.renting.api.Renter;
import hm.videostore.statement.api.Statement;
import hm.videostore.statement.api.StatementFactory;
import java.time.LocalDate;

class Customer implements Renter {
    private StatementFactory statementFactory;
    private Rentals rentals;

    Customer(StatementFactory statementFactory, Rentals rentals) {
        this.statementFactory = statementFactory;
        this.rentals = rentals;
    }

    public void rent(Movie regularMovie, LocalDate rentalDate) {
        rentals.add(regularMovie, rentalDate);
    }

    public Statement returnRentalsOn(LocalDate returnDate) {
        var statementItems = rentals.returnAllOn(returnDate);
        return statementFactory.makeStatement(this, returnDate, statementItems);
    }
}
