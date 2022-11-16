package View;

import Control.Command;
import Model.Currency;
import Model.Money;

public interface Dialog {
    void addCommand(Command command);
    Money getBaseMoney();
    Currency getDestinationCurrency();
    void updateResult(Money money);
    void displayErrorMessage(String error);
}
