package Model;

import java.text.DecimalFormat;

public class Money {
    private final double amount;
    private final Currency currency;
    
    public Money(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public Currency getCurrency() {
        return currency;
    }
    
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.000000");
        return df.format(amount)+" "+currency.getSymbol();
    }
}
