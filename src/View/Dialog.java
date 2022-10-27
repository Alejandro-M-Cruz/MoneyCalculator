package View;

import Control.Controller;
import Model.Currency;
import Model.Money;

public interface Dialog {
    public void setController(Controller controller);
    public void refreshResult(Money money);
    public Money getBaseMoney();
    public Currency getDestinationCurrency();
    public void displayErrorMessage(String error);
}
