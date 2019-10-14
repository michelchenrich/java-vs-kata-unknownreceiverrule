package hm.videostore.movie;

import hm.videostore.movie.api.Movie;
import hm.videostore.movie.pricing.api.PriceStrategy;
import java.math.BigDecimal;

class MovieAdapter implements Movie {
    private PriceStrategy priceStrategy;

    protected MovieAdapter(PriceStrategy priceStrategy) {
        this.priceStrategy = priceStrategy;
    }

    public BigDecimal getPriceWhenRentedForDays(long days) {
        return priceStrategy.calculateRentalPrice(days);
    }
}
