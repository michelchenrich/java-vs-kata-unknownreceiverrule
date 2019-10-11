package hm.videostore.movie;

import hm.videostore.movie.api.Movie;

public class RegularMovie implements Movie {
    private static double dailyRate;

    public static void setDailyRate(double rate) {
        RegularMovie.dailyRate = rate;
    }

    public double getPriceWhenRentedForDays(long days) {
        return days * dailyRate;
    }
}
