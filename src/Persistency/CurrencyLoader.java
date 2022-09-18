package Persistency;

import Model.Currency;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

public class CurrencyLoader {
    private ArrayList<Currency> currencies;
    
    public CurrencyLoader() {
        currencies = new ArrayList<Currency>();
    }
    
    private void saveToFile(Currency currency, String filename) {
        FileWriter ofn = null;
        PrintWriter pw = null;
        
        try {
            ofn = new FileWriter(filename);
            pw = new PrintWriter(ofn);
            pw.println(currency.getCode()+", "+currency.getName()+", "+currency.getSymbol());            
            
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            try {
                if(null != ofn) {
                    ofn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    
    private void loadFromFile(String filename) {
        File ifn = null;
        Scanner sc = null; 
                
        try {
            ifn = new File(filename);
            sc = new Scanner(ifn);
            String line = null;
            
            while ((line = sc.nextLine()) != null) {
                String[] split = line.split(", ");
                currencies.add(new Currency(split[0],split[1],split[2]));
            }
            
        } catch (Exception e) {
    
            e.printStackTrace();
        } 
    }
}
