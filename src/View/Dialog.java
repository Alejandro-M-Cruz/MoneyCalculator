package View;

import Control.Command;
import Model.Currency;
import Model.Money;

public interface Dialog {
    void addCommand(Command command);
    void updateResult(Money money);
    Money getBaseMoney();
    Currency getDestinationCurrency();
    void displayErrorMessage(String error);
}
