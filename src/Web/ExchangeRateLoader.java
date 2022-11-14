package Web;
import Model.Currency;
import Model.ExchangeRate;

public interface ExchangeRateLoader {
    ExchangeRate loadExchangeRate(Currency base, Currency destination);
}
