package MoneyCalculator;

import Control.ConvertCommand;
import File.CurrencyLoaderFromFile;
import View.DialogPanel;
import View.MoneyCalculatorView;
import Web.ExchangeRateLoaderFromWeb;
import java.io.File;
import javax.swing.SwingUtilities;



public class Main {

    public static void main(String[] args) {
        DialogPanel panel = new DialogPanel(new CurrencyLoaderFromFile(new File("Currencies.txt")).loadCurrencies());
        panel.addCommand(new ConvertCommand(panel, new ExchangeRateLoaderFromWeb()));
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MoneyCalculatorView(panel, "Currency Exchanger");
            }
        });
    }
}
