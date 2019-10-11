package hm.videostore.renting;

import hm.reuse.ListFactory;
import hm.videostore.renting.api.RentalFactory;
import hm.videostore.renting.api.Rentals;
import hm.videostore.renting.api.RentalsFactory;

public class RentedMoviesFactory implements RentalsFactory {
    private RentalFactory rentalFactory;
    private ListFactory listFactory;

    public RentedMoviesFactory(RentalFactory rentalFactory, ListFactory listFactory) {
        this.rentalFactory = rentalFactory;
        this.listFactory = listFactory;
    }

    public Rentals makeRentals() {
        return new RentedMovies(rentalFactory, listFactory);
    }
}
