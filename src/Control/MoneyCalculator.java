package Control;

import Persistency.ExchangeRateLoader;
import Model.*;

public class MoneyCalculator {

    public static void main(String[] args) {
        ExchangeRateLoader loader = new ExchangeRateLoader();
        Currency euro = new Currency("EUR","Euro","€");
        Currency jpy = new Currency("JPY","Yen","¥");
        Currency usd = new Currency("USD","Dólar USA","$");
        System.out.println(loader.getExchangeRate(euro,usd).getRate());
    }
    
}
