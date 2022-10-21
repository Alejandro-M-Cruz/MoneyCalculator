package MoneyCalculator;

import Model.Currency;
import Persistency.CurrencyLoaderFromFile;
import View.DisplayPanel;
import View.MoneyCalculatorView;
import java.util.List;
import javax.swing.SwingUtilities;



public class MoneyCalculator {

    public static void main(String[] args) {
        CurrencyLoaderFromFile currencyLoaderFromFile = new CurrencyLoaderFromFile("Currencies.txt");
        List<Currency> currencies = currencyLoaderFromFile.loadCurrencies();
        
        DisplayPanel panel = new DisplayPanel(currencies);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MoneyCalculatorView(panel, "Currency Exchanger");
            }
        });
    }
}
