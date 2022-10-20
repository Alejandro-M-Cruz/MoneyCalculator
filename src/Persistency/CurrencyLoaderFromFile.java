package Persistency;

import Model.Currency;
import java.io.BufferedReader;
import java.util.List;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;

public class CurrencyLoaderFromFile implements CurrencyLoader{
    private String filename;
    
    public CurrencyLoaderFromFile(String filename) {
        this.filename = filename;
    }
    
    /*
    @Override
    public void saveCurrencies(List<Currency> currencies) {
        FileWriter ofn = null;
        PrintWriter pw = null;
        
        try {
            ofn = new FileWriter(filename);
            pw = new PrintWriter(ofn);
            for(int i=0; i<currencies.size(); i++) {
                pw.println(currencies.get(i).toString());            
            }
            ofn.close();
        } catch (Exception e) {
            e.printStackTrace();
            
        } 
    }
    */
    
    @Override
    public List<Currency> loadCurrencies() {
        ArrayList<Currency> currencies = new ArrayList<Currency>();
        
        try {
            File f = new File(this.filename);
            BufferedReader br = new BufferedReader(new FileReader(f));
            IteratorReader iteratorReader = new IteratorReader(br);
            for(String line : iteratorReader) {
                currencies.add(currencyOf(line));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return currencies;
    }
    
    public Currency currencyOf(String line) {
        String[] split = line.split(", ");
        return new Currency(split[0],split[1],split[2]);
    }
}
