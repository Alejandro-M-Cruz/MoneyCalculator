package Web;

import Model.Currency;
import Model.ExchangeRate;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ExchangeRateLoaderFromWeb implements ExchangeRateLoader {
    
    public ExchangeRateLoaderFromWeb() {
    }
    
    @Override
    public ExchangeRate loadExchangeRate(Currency base, Currency destination) {
        String url_str = createURL(base.getCode(),destination.getCode());
        return new ExchangeRate(readFromURL(url_str),base,destination);
    }
    
    private double readFromURL(String url_str) {
        try {
            URL url = new URL(url_str);
            URLConnection con = url.openConnection();
            InputStream is = con.getInputStream();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            br.readLine();
            br.readLine();
            
            String rate = br.readLine();
            rate = rate.substring(11);
            
            return Double.parseDouble(rate);
            
        } catch(Exception e) {
            return 0.0;
        }
        
    }
    
    private String createURL(String base, String destination) {
        return "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/"+base.toLowerCase()+"/"+destination.toLowerCase()+".json";
    }
}
