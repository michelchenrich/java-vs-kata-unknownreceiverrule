package hm.videostore.movie.api;

import java.math.BigDecimal;

public interface Movie {
    BigDecimal getPriceWhenRentedForDays(long days);
}
