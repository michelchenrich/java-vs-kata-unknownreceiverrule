package hm.videostore.movie.pricing.api;

import java.math.BigDecimal;

public interface PriceStrategy {
    BigDecimal calculateRentalPrice(long days);
}
