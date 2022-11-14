package View;

import Control.Command;
import Model.Currency;
import Model.Money;

public interface Dialog {
    void addCommand(Command command);
    void refreshResult(Money money);
    Money getBaseMoney();
    Currency getDestinationCurrency();
    void displayErrorMessage(String error);
}
