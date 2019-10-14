package hm.videostore.movie.pricing;

import hm.videostore.movie.pricing.api.PriceStrategy;
import hm.videostore.movie.pricing.api.PriceStrategyFactory;
import java.math.BigDecimal;

public class DailyRateWithDelayIncreaseFactory implements PriceStrategyFactory {
    public PriceStrategy makePriceStrategy(BigDecimal dailyRate, BigDecimal increaseRate) {
        return new DailyRateWithDelayIncrease(dailyRate, increaseRate);
    }
}
