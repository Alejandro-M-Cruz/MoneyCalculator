package Control;

import Model.Currency;
import Model.ExchangeRate;
import Model.Money;
import Web.ExchangeRateLoader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.Dialog;

public class Controller implements ActionListener {
    private Dialog panel;
    private ExchangeRateLoader exchangeRateLoader;
    
    public Controller(Dialog panel, ExchangeRateLoader exchangeRateLoader) {
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
