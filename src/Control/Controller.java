package Control;

import Model.Currency;
import Model.ExchangeRate;
import Model.Money;
import View.DisplayView;
import Web.ExchangeRateLoader;
import Web.ExchangeRateLoaderFromWeb;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    DisplayView panel;
    ExchangeRateLoader exchangeRateLoader;
    
    public Controller(DisplayView panel) {
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
