package hm.videostore.movie;

import hm.videostore.movie.api.Movie;
import hm.videostore.movie.api.MovieFactory;

public class TypedMovieFactory implements MovieFactory {
    public Movie makeRegularMovie() {
        return new RegularMovie();
    }
}
