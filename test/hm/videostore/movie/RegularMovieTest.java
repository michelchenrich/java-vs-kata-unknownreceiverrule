package hm.videostore.movie;

import static hm.videostore.BigDecimalAssert.assertEquals;
import hm.videostore.movie.api.Movie;
import hm.videostore.movie.pricing.DailyRateWithDelayIncreaseFactory;
import static java.math.BigDecimal.valueOf;
import org.junit.Before;
import org.junit.Test;

public class RegularMovieTest {
    private Movie regularMovie;

    @Before
    public void setUp() {
        var priceStrategyFactory = new DailyRateWithDelayIncreaseFactory();
        regularMovie = new MovieAdapterFactory(priceStrategyFactory, valueOf(3.0)).makeRegularMovie();
    }

    @Test
    public void rentedForZeroDays_chargesOneRegularRate() {
        assertEquals(valueOf(3.0), regularMovie.getPriceWhenRentedForDays(0));
    }

    @Test
    public void rentedForOneDay_chargesOneRegularRate() {
        assertEquals(valueOf(3.0), regularMovie.getPriceWhenRentedForDays(1));
    }

    @Test
    public void rentedForTwoDays_chargesTwiceRegularRate() {
        assertEquals(valueOf(6.0), regularMovie.getPriceWhenRentedForDays(2));
    }

    @Test
    public void rentedForThreeDays_chargesThreeRegularRate_plusLateReturnFeeOf10Percent() {
        assertEquals(valueOf(9.9), regularMovie.getPriceWhenRentedForDays(3));
    }

    @Test
    public void rentedForTwelveDays_chargesTwelveRegularRate_plusLateReturnFeeOf100Percent() {
        assertEquals(valueOf(72.0), regularMovie.getPriceWhenRentedForDays(12));
    }
}