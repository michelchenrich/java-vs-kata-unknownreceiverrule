package hm.videostore.customer;

import hm.videostore.renting.api.RentalsFactory;
import hm.videostore.renting.api.Renter;
import hm.videostore.renting.api.RenterFactory;
import hm.videostore.statement.api.StatementFactory;

public class CustomerFactory implements RenterFactory {
    private StatementFactory statementFactory;
    private RentalsFactory rentalsFactory;

    public CustomerFactory(StatementFactory statementFactory, RentalsFactory rentalsFactory) {
        this.statementFactory = statementFactory;
        this.rentalsFactory = rentalsFactory;
    }

    public Renter makeRenter() {
        return new Customer(statementFactory, rentalsFactory.makeRentals());
    }
}
