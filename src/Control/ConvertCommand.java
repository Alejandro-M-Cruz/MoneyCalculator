package Control;

import Model.Currency;
import Model.ExchangeRate;
import Model.Money;
import Web.ExchangeRateLoader;
import View.Dialog;

public class ConvertCommand implements Command {
    private final Dialog panel;
    private final ExchangeRateLoader exchangeRateLoader;
    
    public ConvertCommand(Dialog panel, ExchangeRateLoader exchangeRateLoader) {
        this.panel = panel;
        this.exchangeRateLoader = exchangeRateLoader;
    }
    
    @Override
    public void execute() {
        Money baseMoney = panel.getBaseMoney();
        Currency baseCurrency;
        if ((baseCurrency = baseMoney.getCurrency()) == null) {
            panel.displayErrorMessage("ERROR: The introduced amount is not valid");
            return;
        }
        Currency destinationCurrency = panel.getDestinationCurrency();
        ExchangeRate exchangeRate = exchangeRateLoader.loadExchangeRate(baseCurrency,destinationCurrency);
        panel.updateResult(new Money(exchangeRate.getRate()*baseMoney.getAmount(), destinationCurrency));
    }
}
