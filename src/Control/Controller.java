package Control;

import Model.Currency;
import Model.ExchangeRate;
import Model.Money;
import View.DisplayPanel;
import Web.ExchangeRateLoaderFromWeb;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Controller implements ActionListener {
    DisplayPanel panel;
    ExchangeRateLoaderFromWeb exchangeRateLoader;
    
    public Controller(DisplayPanel panel) {
        this.panel = panel;
        this.exchangeRateLoader = new ExchangeRateLoaderFromWeb();
    }
    
    @Override 
    public void actionPerformed(ActionEvent ae) {
        Money baseMoney = panel.getBaseMoney();
        Currency baseCurrency;
        if ((baseCurrency = baseMoney.getCurrency()) == null) {
            panel.displayErrorMessage("ERROR: The introduced amount is not valid");
            return;
        }
        Currency destinationCurrency = panel.getDestinationCurrency();
        ExchangeRate exchangeRate = exchangeRateLoader.loadExchangeRate(baseCurrency,destinationCurrency);
        panel.refreshResult(new Money(exchangeRate.getRate()*baseMoney.getAmount(), destinationCurrency));
    }
}
