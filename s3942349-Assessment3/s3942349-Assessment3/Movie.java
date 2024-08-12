public class Movie {

    //fields
    private String movieTitle;
    private int movieDuration;
    private String movieRating;
    private int movieFee;

    //constructor
    public Movie(String title, int duration, String rating, int fee) {
        this.movieTitle = title;
        this.movieDuration = duration;
        this.movieRating = rating;
        this.movieFee = fee;
    }

    //methods
    public String getTitle() {
        return this.movieTitle;
    }

    public int getDuration() {
        return this.movieDuration;
    }

    public String getRating() {
        return this.movieRating;
    }

    public int getFee() {
        return this.movieFee;
    }

    public void setTitle(String title) {
        this.movieTitle = title;
    }

    public void setDuration(int duration) {
        this.movieDuration = duration;
    }

    public void setRating(String rating) {
        this.movieRating = rating;
    }

    public void setFee(int fee) {
        this.movieFee = fee;
    }

    public String toString() {
        return String.format(this.movieTitle+ ", " +this.movieDuration+" minutes, Rating: "+this.movieRating+", Fee: "+this.movieFee);
    }
}