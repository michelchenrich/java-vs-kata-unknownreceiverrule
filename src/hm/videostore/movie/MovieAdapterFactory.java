package hm.videostore.movie;

import hm.videostore.movie.api.Movie;
import hm.videostore.movie.api.MovieFactory;
import hm.videostore.movie.pricing.api.PriceStrategyFactory;
import java.math.BigDecimal;
import static java.math.BigDecimal.valueOf;

public class MovieAdapterFactory implements MovieFactory {
    private PriceStrategyFactory priceStrategyFactory;
    private BigDecimal regularRate;

    public MovieAdapterFactory(PriceStrategyFactory priceStrategyFactory, BigDecimal regularRate) {
        this.priceStrategyFactory = priceStrategyFactory;
        this.regularRate = regularRate;
    }

    public Movie makeRegularMovie() {
        return new MovieAdapter(priceStrategyFactory.makePriceStrategy(regularRate,
                                                                       valueOf(0.1)));
    }

    public Movie makeNewReleaseMovie() {
        return new MovieAdapter(priceStrategyFactory.makePriceStrategy(regularRate.multiply(valueOf(2.0)),
                                                                       valueOf(0.2)));
    }
}
