package Control;

import Model.Currency;
import Model.ExchangeRate;
import Model.Money;
import View.DisplayView;
import Web.ExchangeRateLoader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private DisplayView panel;
    private ExchangeRateLoader exchangeRateLoader;
    
    public Controller(DisplayView panel, ExchangeRateLoader exchangeRateLoader) {
        this.panel = panel;
        this.panel.setController(this);
        this.exchangeRateLoader = exchangeRateLoader;
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
