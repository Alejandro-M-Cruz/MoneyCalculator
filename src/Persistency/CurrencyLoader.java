package Persistency;

import Model.Currency;
import java.util.List;

public interface CurrencyLoader {    
    public void saveCurrencies(List<Currency> currencies);
    
    public List<Currency> loadCurrencies();
}
