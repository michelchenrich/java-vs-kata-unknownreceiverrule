package hm.videostore;

import hm.reuse.ListFactory;
import hm.videostore.customer.CustomerFactory;
import hm.videostore.movie.TypedMovieFactory;
import hm.videostore.movie.api.MovieFactory;
import hm.videostore.renting.RentedMovieFactory;
import hm.videostore.renting.RentedMoviesFactory;
import hm.videostore.renting.api.RenterFactory;
import hm.videostore.statement.ReturnStatementFactory;
import hm.videostore.statement.ReturnedItemFactory;
import java.util.LinkedList;

public class VideoStore {
    private RenterFactory renterFactory;
    private MovieFactory movieFactory;

    public static VideoStore compose() {
        ListFactory listFactory = LinkedList::new;
        var statementFactory = new ReturnStatementFactory();
        var statementItemFactory = new ReturnedItemFactory();
        var rentalFactory = new RentedMovieFactory(statementItemFactory);
        var rentalsFactory = new RentedMoviesFactory(rentalFactory, listFactory);
        var renterFactory = new CustomerFactory(statementFactory, rentalsFactory);
        var movieFactory = new TypedMovieFactory();
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
