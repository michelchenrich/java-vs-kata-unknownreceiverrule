package hm.videostore;

import java.time.LocalDate;
import java.util.List;

public class Customer {
    private StatementFactory statementFactory;
    private Rentals rentals;

    public Customer() {
        this(Statement::new, new Rentals());
    }

    public Customer(StatementFactory statementFactory, Rentals rentals) {
        this.statementFactory = statementFactory;
        this.rentals = rentals;
    }

    public void rent(Movie regularMovie, LocalDate rentalDate) {
        rentals.add(regularMovie, rentalDate);
    }

    public Statement returnRentalsOn(LocalDate returnDate) {
        List<StatementItem> statementItems = rentals.returnAllOn(returnDate);
        return statementFactory.make(this, returnDate, statementItems);
    }
}
