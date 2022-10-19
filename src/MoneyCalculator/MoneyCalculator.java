package MoneyCalculator;

import Web.ExchangeRateLoader;
import Model.*;
import Persistency.CurrencyLoader;
import Persistency.CurrencyLoaderFromFile;
import Web.ExchangeRateLoaderFromWeb;
import View.DisplayView;
import java.util.List;
import java.util.ArrayList;


public class MoneyCalculator {

    public static void main(String[] args) {
        CurrencyLoader currencyLoaderFromFile = new CurrencyLoaderFromFile("Currencies.txt");
        ExchangeRateLoaderFromWeb exchangeRateLoaderFromWeb = new ExchangeRateLoaderFromWeb();
        Currency eur = new Currency("EUR","Euro","€");
        Currency jpy = new Currency("JPY","Yen","¥");
        Currency usd = new Currency("USD","Dólar USA","$");
        // System.out.println(exchangeRateLoaderFromWeb.loadExchangeRate(eur,usd).getRate());
        ArrayList<Currency> currencies1 = new ArrayList<Currency>();
        currencies1.add(jpy);
        currencies1.add(eur);
        currencies1.add(usd);
        currencyLoaderFromFile.saveCurrencies(currencies1);
        List<Currency> currencies2 = currencyLoaderFromFile.loadCurrencies();
        for(Currency currency : currencies2) {
            System.out.println(currency.toString());
        }
        
        DisplayView displayView = new DisplayView();
        displayView.display();
    }
}
