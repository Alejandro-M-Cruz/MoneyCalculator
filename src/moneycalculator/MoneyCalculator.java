package moneycalculator;

import Persistency.ExchangeRateLoader;
import Model.*;

public class MoneyCalculator {

    public static void main(String[] args) {
        ExchangeRateLoader loader = new ExchangeRateLoader();
        Currency euro = new Currency("EUR","Euro","€");
        Currency jpy = new Currency("JPY","Yen","¥");
        System.out.println(loader.getExchangeRate(euro,jpy).getRate());
    }
    
}
