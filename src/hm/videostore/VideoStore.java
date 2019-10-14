package hm.videostore;

import hm.reuse.ListFactory;
import hm.videostore.customer.CustomerFactory;
import hm.videostore.movie.MovieAdapterFactory;
import hm.videostore.movie.api.MovieFactory;
import hm.videostore.movie.pricing.DailyRateWithDelayIncreaseFactory;
import hm.videostore.renting.RentedMovieFactory;
import hm.videostore.renting.RentedMoviesFactory;
import hm.videostore.renting.api.RenterFactory;
import hm.videostore.statement.ReturnStatementFactory;
import hm.videostore.statement.ReturnedItemFactory;
import java.math.BigDecimal;
import java.util.LinkedList;

public class VideoStore {
    private RenterFactory renterFactory;
    private MovieFactory movieFactory;

    public static VideoStore compose(BigDecimal regularRate) {
        ListFactory listFactory = LinkedList::new;
        var statementFactory = new ReturnStatementFactory();
        var statementItemFactory = new ReturnedItemFactory();
        var rentalFactory = new RentedMovieFactory(statementItemFactory);
        var rentalsFactory = new RentedMoviesFactory(rentalFactory, listFactory);
        var renterFactory = new CustomerFactory(statementFactory, rentalsFactory);
        var priceStrategyFactory = new DailyRateWithDelayIncreaseFactory();
        var movieFactory = new MovieAdapterFactory(priceStrategyFactory, regularRate);
        return new VideoStore(renterFactory, movieFactory);
    }

    private VideoStore(RenterFactory renterFactory, MovieFactory movieFactory) {
        this.renterFactory = renterFactory;
        this.movieFactory = movieFactory;
    }

    public RenterFactory getRenterFactory() {
        return renterFactory;
    }

    public MovieFactory getMovieFactory() {
        return movieFactory;
    }
}
