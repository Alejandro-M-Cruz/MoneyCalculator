package View;

import Model.Currency;
import Model.Money;

public interface DisplayView {
    public void refreshResult(Money money);
    public Money getBaseMoney();
    public Currency getDestinationCurrency();
    public void displayErrorMessage(String error);
}
