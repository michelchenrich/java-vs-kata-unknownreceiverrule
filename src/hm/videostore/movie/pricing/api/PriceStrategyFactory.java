package hm.videostore.movie.pricing.api;

import java.math.BigDecimal;

public interface PriceStrategyFactory {
    PriceStrategy makePriceStrategy(BigDecimal dailyRate, BigDecimal increaseRate);
}
