package hm.videostore;

import static hm.videostore.BigDecimalAssert.assertEquals;
import hm.videostore.movie.api.Movie;
import hm.videostore.renting.api.Renter;
import java.math.BigDecimal;
import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;
import java.time.LocalDate;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class VideoStoreTest {
    private static final BigDecimal REGULAR_RATE = valueOf(3.0);
    private static final BigDecimal DOUBLE_RATE = REGULAR_RATE.multiply(valueOf(2.0));
    private static final LocalDate TODAY = LocalDate.now();
    private static final LocalDate TOMORROW = LocalDate.now().plusDays(1);
    private Movie regularMovie;
    private Renter customer;

    @Before
    public void setUp() {
        var videoStore = VideoStore.compose(REGULAR_RATE);
        regularMovie = videoStore.getMovieFactory().makeRegularMovie();
        customer = videoStore.getRenterFactory().makeRenter();
    }

    @Test
    public void commonStatementAttributes() {
        var statement = customer.returnRentalsOn(TODAY);

        assertEquals(customer, statement.getRenter());
        assertEquals(TODAY, statement.getDate());
    }

    @Test
    public void noRentals_emptyStatement() {
        var statement = customer.returnRentalsOn(TOMORROW);

        assertEquals(0, statement.getItems().size());
        assertEquals(ZERO, statement.getTotal());
    }

    @Test
    public void oneRegularMovieReturnedInSameDay_paysJustOneDailyRate() {
        customer.rent(regularMovie, TODAY);

        var statement = customer.returnRentalsOn(TODAY);

        assertEquals(1, statement.getItems().size());
        assertEquals(REGULAR_RATE, statement.getItems().get(0).getPrice());
        assertEquals(REGULAR_RATE, statement.getTotal());
    }

    @Test
    public void oneRegularMovieReturnedTheNextDay_paysTwoDailyRates() {
        customer.rent(regularMovie, TODAY);

        var statement = customer.returnRentalsOn(TOMORROW);

        assertEquals(1, statement.getItems().size());
        assertEquals(DOUBLE_RATE, statement.getItems().get(0).getPrice());
        assertEquals(DOUBLE_RATE, statement.getTotal());
    }

    @Test
    public void twoRegularMoviesReturnedTheNextDay_paysTwoDailyRates() {
        customer.rent(regularMovie, TODAY);
        customer.rent(regularMovie, TODAY);

        var statement = customer.returnRentalsOn(TOMORROW);

        assertEquals(2, statement.getItems().size());
        assertEquals(DOUBLE_RATE, statement.getItems().get(0).getPrice());
        assertEquals(DOUBLE_RATE, statement.getItems().get(1).getPrice());
        assertEquals(DOUBLE_RATE.add(DOUBLE_RATE), statement.getTotal());
    }

    @Test
    public void subsequentStatementIsEmpty() {
        customer.rent(regularMovie, TODAY);
        customer.rent(regularMovie, TODAY);
        customer.returnRentalsOn(TOMORROW);

        var secondStatement = customer.returnRentalsOn(TOMORROW);

        assertEquals(0, secondStatement.getItems().size());
        assertEquals(ZERO, secondStatement.getTotal());
    }
}
