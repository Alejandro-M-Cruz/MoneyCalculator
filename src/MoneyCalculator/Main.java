package MoneyCalculator;

import Control.ConvertCommand;
import Model.Currency;
import Persistency.CurrencyLoaderFromFile;
import View.DialogPanel;
import View.MoneyCalculatorView;
import Web.ExchangeRateLoaderFromWeb;
import java.util.List;
import javax.swing.SwingUtilities;



public class Main {

    public static void main(String[] args) {
        CurrencyLoaderFromFile currencyLoaderFromFile = new CurrencyLoaderFromFile("Currencies.txt");
        List<Currency> currencies = currencyLoaderFromFile.loadCurrencies();
        
        DialogPanel panel = new DialogPanel(currencies);
        ConvertCommand controller = new ConvertCommand(panel, new ExchangeRateLoaderFromWeb());
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MoneyCalculatorView(panel, "Currency Exchanger");
            }
        });
    }
}
