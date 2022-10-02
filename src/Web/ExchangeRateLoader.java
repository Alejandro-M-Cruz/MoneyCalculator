package Web;
import Model.Currency;
import Model.ExchangeRate;

public interface ExchangeRateLoader {
    public ExchangeRate loadExchangeRate(Currency base, Currency destination);
}
