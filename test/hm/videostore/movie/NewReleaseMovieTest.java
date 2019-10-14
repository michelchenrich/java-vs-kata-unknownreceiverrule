package hm.videostore.movie;

import static hm.videostore.BigDecimalAssert.assertEquals;
import hm.videostore.movie.api.Movie;
import hm.videostore.movie.pricing.DailyRateWithDelayIncreaseFactory;
import static java.math.BigDecimal.valueOf;
import org.junit.Before;
import org.junit.Test;

public class NewReleaseMovieTest {
    private Movie newRelease;

    @Before
    public void setUp() {
        var priceStrategyFactory = new DailyRateWithDelayIncreaseFactory();
        newRelease = new MovieAdapterFactory(priceStrategyFactory, valueOf(3.0)).makeNewReleaseMovie();
    }

    @Test
    public void rentedForZeroDays_chargesTwoRegularRates() {
        assertEquals(valueOf(6.0), newRelease.getPriceWhenRentedForDays(0));
    }

    @Test
    public void rentedForOneDay_chargesTwoRegularRates() {
        assertEquals(valueOf(6.0), newRelease.getPriceWhenRentedForDays(1));
    }

    @Test
    public void rentedForTwoDays_chargesFourRegularRates() {
        assertEquals(valueOf(12.0), newRelease.getPriceWhenRentedForDays(2));
    }

    @Test
    public void rentedForThreeDays_chargesSixRegularRates_plusLateReturnFeeOf20Percent() {
        assertEquals(valueOf(21.6), newRelease.getPriceWhenRentedForDays(3));
    }

    @Test
    public void rentedForTwelveDays_chargesTwelveRegularRate_plusLateReturnFeeOf100Percent() {
        assertEquals(valueOf(216.0), newRelease.getPriceWhenRentedForDays(12));
    }
}