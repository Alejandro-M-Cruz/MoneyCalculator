package View;

import Control.Command;
import Model.Currency;
import Model.Money;

public interface Dialog {
    void addCommand(String name, Command command);
    Money getBaseMoney();
    Currency getDestinationCurrency();
    void swapCurrencies();
    void updateResult(Money money);
    void displayErrorMessage(String error);
}
