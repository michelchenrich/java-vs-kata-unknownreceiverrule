package hm.videostore;

public class RegularMovie extends Movie {
    private static double dailyRate;

    public static void setDailyRate(double rate) {
        RegularMovie.dailyRate = rate;
    }

    public double getPriceWhenRentedForDays(long days) {
        return days * dailyRate;
    }
}
