package Model;

public class ExchangeRate {
    private double rate;
    private final Currency base;
    private final Currency destination;

    public ExchangeRate(double rate, Currency base, Currency destination) {
        this.rate = rate;
        this.base = base;
        this.destination = destination;
    }
    
    public double getRate() {
        return rate;
    }
}
