package Control;

import Model.Currency;
import Model.ExchangeRate;
import Model.Money;
import Web.ExchangeRateLoader;
import View.Dialog;

public class ConvertCommand implements Command {
    private final Dialog dialog;
    private final ExchangeRateLoader exchangeRateLoader;
    
    public ConvertCommand(Dialog dialog, ExchangeRateLoader exchangeRateLoader) {
        this.dialog = dialog;
        this.exchangeRateLoader = exchangeRateLoader;
    }
    
    @Override
    public void execute() {
        Money baseMoney = dialog.getBaseMoney();
        Currency baseCurrency;
        if ((baseCurrency = baseMoney.getCurrency()) == null) {
            dialog.displayErrorMessage("ERROR: The introduced amount is not valid");
            return;
        }
        Currency destinationCurrency = dialog.getDestinationCurrency();
        ExchangeRate exchangeRate = exchangeRateLoader.loadExchangeRate(baseCurrency,destinationCurrency);
        dialog.updateResult(new Money(exchangeRate.getRate()*baseMoney.getAmount(), destinationCurrency));
    }
}
