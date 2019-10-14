package hm.videostore.movie.pricing;

import hm.videostore.movie.pricing.api.PriceStrategy;
import static java.lang.Long.max;
import java.math.BigDecimal;
import static java.math.BigDecimal.valueOf;

public class DailyRateWithDelayIncrease implements PriceStrategy {
    private BigDecimal regularRate;
    private BigDecimal dailyDelayIncrease;

    public DailyRateWithDelayIncrease(BigDecimal dailyRate, BigDecimal delayIncrease) {
        this.regularRate = dailyRate;
        this.dailyDelayIncrease = delayIncrease;
    }

    public BigDecimal calculateRentalPrice(long days) {
        return getTotalDailyRate(days).multiply(getDelayIncrease(days));
    }

    private BigDecimal getTotalDailyRate(long days) {
        return regularRate.multiply(valueOf(max(1, days)));
    }

    private BigDecimal getDelayIncrease(long days) {
        return valueOf(1.0).add(dailyDelayIncrease.multiply(valueOf(getDaysAfterExpectedReturnDate(days))));
    }

    private long getDaysAfterExpectedReturnDate(long days) {
        return max(0, days - 2);
    }
}
