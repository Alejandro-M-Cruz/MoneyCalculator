package View;

import Model.Currency;
import Model.Money;
import java.awt.event.ActionListener;

public interface Dialog {
    public void setController(ActionListener controller);
    public void refreshResult(Money money);
    public Money getBaseMoney();
    public Currency getDestinationCurrency();
    public void displayErrorMessage(String error);
}
