package MoneyCalculator;

import Control.ConvertCommand;
import Persistency.CurrencyLoaderFromFile;
import View.DialogPanel;
import View.MoneyCalculatorView;
import Web.ExchangeRateLoaderFromWeb;
import javax.swing.SwingUtilities;



public class Main {

    public static void main(String[] args) {
        DialogPanel panel = new DialogPanel(new CurrencyLoaderFromFile("Currencies.txt").loadCurrencies());
        panel.addCommand(new ConvertCommand(panel, new ExchangeRateLoaderFromWeb()));
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MoneyCalculatorView(panel, "Currency Exchanger");
            }
        });
    }
}
