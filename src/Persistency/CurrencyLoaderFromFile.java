package Persistency;

import Model.Currency;
import java.io.BufferedReader;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;

public class CurrencyLoaderFromFile implements CurrencyLoader{
    private final String filename;
    
    public CurrencyLoaderFromFile(String filename) {
        this.filename = filename;
    }
        
    @Override
    public List<Currency> loadCurrencies() {
        ArrayList<Currency> currencies = new ArrayList();
        try {
            File f = new File(this.filename);
            BufferedReader br = new BufferedReader(new FileReader(f));
            IteratorReader iteratorReader = new IteratorReader(br);
            for(String line : iteratorReader) 
                currencies.add(stringToCurrency(line));
        } catch (Exception e) {
            return null;
        } 
        return currencies;
    }
    
    private Currency stringToCurrency(String line) {
        String[] split = line.split(", ");
        return new Currency(split[0],split[1],split[2]);
    }
}
