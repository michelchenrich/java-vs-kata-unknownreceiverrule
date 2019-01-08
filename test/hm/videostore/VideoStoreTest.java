package hm.videostore;

import java.time.LocalDate;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class VideoStoreTest {
    private static final double REGULAR_RATE = 3.0;
    private static final LocalDate TODAY = LocalDate.now();
    private static final LocalDate TOMORROW = LocalDate.now().plusDays(1);
    private Movie regularMovie;
    private Customer customer;

    @Before
    public void setUp() {
        RegularMovie.setDailyRate(REGULAR_RATE);
        regularMovie = new RegularMovie();
        customer = new Customer();
    }

    @Test
    public void commonStatementAttributes() {
        LocalDate returnDate = TODAY;

        Statement statement = customer.returnRentalsOn(returnDate);

        assertEquals(customer, statement.getCustomer());
        assertEquals(returnDate, statement.getDate());
    }

    @Test
    public void noRentals_emptyStatement() {
        Statement statement = customer.returnRentalsOn(TOMORROW);

        assertEquals(0, statement.getItems().size());
        assertEquals(0.0, statement.getTotal(), 0.001);
    }

    @Test
    public void oneRegularMovieReturnedInSameDay_paysJustOneDailyRate() {
        customer.rent(regularMovie, TODAY);

        Statement statement = customer.returnRentalsOn(TODAY);

        assertEquals(1, statement.getItems().size());
        assertEquals(REGULAR_RATE, statement.getItems().get(0).getPrice(), 0.001);
        assertEquals(REGULAR_RATE, statement.getTotal(), 0.001);
    }

    @Test
    public void oneRegularMovieReturnedTheNextDay_paysTwoDailyRates() {
        customer.rent(regularMovie, TODAY);

        Statement statement = customer.returnRentalsOn(TOMORROW);

        assertEquals(1, statement.getItems().size());
        assertEquals(REGULAR_RATE * 2, statement.getItems().get(0).getPrice(), 0.001);
        assertEquals(REGULAR_RATE * 2, statement.getTotal(), 0.001);
    }

    @Test
    public void twoRegularMoviesReturnedTheNextDay_paysTwoDailyRates() {
        customer.rent(regularMovie, TODAY);
        customer.rent(regularMovie, TODAY);

        Statement statement = customer.returnRentalsOn(TOMORROW);

        assertEquals(2, statement.getItems().size());
        assertEquals(REGULAR_RATE * 2, statement.getItems().get(0).getPrice(), 0.001);
        assertEquals(REGULAR_RATE * 2, statement.getItems().get(1).getPrice(), 0.001);
        assertEquals(REGULAR_RATE * 2 * 2, statement.getTotal(), 0.001);
    }

    @Test
    public void subsequentStatementIsEmpty() {
        customer.rent(regularMovie, TODAY);
        customer.rent(regularMovie, TODAY);
        customer.returnRentalsOn(TOMORROW);

        Statement secondStatement = customer.returnRentalsOn(TOMORROW);

        assertEquals(0, secondStatement.getItems().size());
        assertEquals(0.0, secondStatement.getTotal(), 0.001);
    }
}
