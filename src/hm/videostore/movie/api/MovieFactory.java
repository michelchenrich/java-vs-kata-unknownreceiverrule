package hm.videostore.movie.api;

public interface MovieFactory {
    Movie makeRegularMovie();

    Movie makeNewReleaseMovie();
}
