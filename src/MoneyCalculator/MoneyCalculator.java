package MoneyCalculator;

import Control.Controller;
import Model.Currency;
import Persistency.CurrencyLoader;
import Persistency.CurrencyLoaderFromFile;
import View.DisplayPanel;
import View.MoneyCalculatorView;
import Web.ExchangeRateLoaderFromWeb;
import java.util.List;
import javax.swing.SwingUtilities;



public class MoneyCalculator {

    public static void main(String[] args) {
        CurrencyLoader currencyLoaderFromFile = new CurrencyLoaderFromFile("Currencies.txt");
        List<Currency> currencies = currencyLoaderFromFile.loadCurrencies();
        
        DisplayPanel panel = new DisplayPanel(currencies);
        Controller controller = new Controller(panel, new ExchangeRateLoaderFromWeb());
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MoneyCalculatorView(panel, "Currency Exchanger");
            }
        });
    }
}
