package Web;

import Model.Currency;
import Model.ExchangeRate;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class ExchangeRateLoaderFromWeb implements ExchangeRateLoader {
    
    public ExchangeRateLoaderFromWeb() {
    }
    
    @Override
    public ExchangeRate loadExchangeRate(Currency base, Currency destination) {
        return new ExchangeRate(readFromURL(createURL(base,destination)),base,destination);
    }
    
    private double readFromURL(String url) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openConnection().getInputStream()));
            br.readLine(); br.readLine();
            return Double.parseDouble(br.readLine().substring(11));
        } catch(Exception e) {
            return 0.0;
        }
    }
    
    private String createURL(Currency base, Currency destination) {
        return "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/"+
                base.getCode().toLowerCase()+"/"+destination.getCode().toLowerCase()+".json";
    }
}
