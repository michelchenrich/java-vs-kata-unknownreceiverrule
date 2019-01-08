package hm.videostore;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Rentals {
    private RentalFactory rentalFactory;
    private List<Rental> list;

    public Rentals() {
        this(Rental::new, LinkedList::new);
    }

    public Rentals(RentalFactory rentalFactory, Supplier<List<Rental>> listFactory) {
        this.rentalFactory = rentalFactory;
        this.list = listFactory.get();
    }

    public void add(Movie movie, LocalDate rentalDate) {
        list.add(rentalFactory.make(movie, rentalDate));
    }

    public List<StatementItem> returnAllOn(LocalDate returnDate) {
        List<StatementItem> statementItems = list.stream()
                                                 .map(rental -> rental.returnOn(returnDate))
                                                 .collect(Collectors.toList());
        list.clear();
        return statementItems;
    }
}
