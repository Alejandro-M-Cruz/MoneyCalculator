package File;

import Model.Currency;
import java.io.BufferedReader;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class CurrencyLoaderFromFile implements CurrencyLoader{
    private final File file;
    
    public CurrencyLoaderFromFile(File file) {
        this.file = file;
    }
        
    @Override
    public List<Currency> loadCurrencies() {
        ArrayList<Currency> currencies = new ArrayList();
        try {
            IteratorReader iteratorReader = new IteratorReader(new BufferedReader(new FileReader(file)));
            for(String line : iteratorReader) 
                currencies.add(stringToCurrency(line));
        } catch (FileNotFoundException e) {} 
        return currencies;
    }
    
    private Currency stringToCurrency(String line) {
        String[] split = line.split(", ");
        return new Currency(split[0],split[1],split[2]);
    }
}
