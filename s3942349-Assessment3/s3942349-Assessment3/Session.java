public class Session{

    //fields
    private int capacity = 60;
    private Movie sessionMovie;
    private int sessionPrice;
    private Movie[] movieList;
    private int remainingTickets = capacity;

    //constructor
    public Session(Movie movie, int price)  {
        this.sessionMovie = movie;
        this.sessionPrice = price;
    }

    public Session(Movie movie) {
        this.sessionMovie = movie;
        this.sessionPrice = 15;
    }

    //methods
    public Movie getMovie() {
        return this.sessionMovie;
    }

    public int getPrice() {
        return this.sessionPrice;
    }

    public void setMovie(Movie movie) {
        this.sessionMovie = movie;
    }

    public void setPrice(int price) {
        this.sessionPrice = price;
    }

    public boolean sellTickets(int qty) {
        if (qty > this.remainingTickets) {
            return false;
        }
        else {
            this.remainingTickets -= qty;
            return true;
        }
    }

    public void setRemainingTickets(int tickets) {
        this.remainingTickets = tickets;
    }

    public int getRemainingTickets() {
        return this.remainingTickets;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacitySparse() {
        this.capacity = 20;
        this.remainingTickets = capacity;
    }

    public int profit() {
        int price = getPrice();
        return price;
    }

    public String toString() {
        return String.format(this.sessionMovie + ", Price: " + this.sessionPrice);
    }
}