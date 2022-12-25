package Web;

import Model.Currency;
import Model.ExchangeRate;
import com.google.gson.Gson;
import com.google.gson.JsonObject;  
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import static java.util.stream.Collectors.joining;

public class ExchangeRateLoaderFromWeb implements ExchangeRateLoader {
    
    public ExchangeRateLoaderFromWeb() {
    }
    
    @Override
    public ExchangeRate loadExchangeRate(Currency base, Currency destination) {
        try {
            return new ExchangeRate(readFromJson(createURL(base,destination),destination),base,destination);
        } catch (IOException e) {
            return null;
        } 
    }
    
    private double readFromJson(String url_str, Currency destination) throws MalformedURLException, IOException {
        JsonObject jsonObject = new Gson().fromJson(readFromURL(new URL(url_str)), JsonObject.class);
        return jsonObject.get(destination.getCode().toLowerCase()).getAsDouble();
    }
    
    private static String readFromURL(URL url) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            return reader.lines().collect(joining());
        } 
    }
    
    private String createURL(Currency base, Currency destination) {
        return "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/"+
                base.getCode().toLowerCase()+"/"+destination.getCode().toLowerCase()+".json";
    }
}
