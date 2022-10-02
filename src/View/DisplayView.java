package View;

import Model.Currency;
import Model.ExchangeRate;
import Persistency.CurrencyLoaderFromFile;
import Web.ExchangeRateLoaderFromWeb;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.*;

public class DisplayView extends JFrame {
    CurrencyLoaderFromFile currencyLoader;
    
    JPanel panel;
    JPanel panel2;
    JTextField baseAmount;
    JTextField result;
    JComboBox baseCurrency;
    JComboBox destinationCurrency;
    JButton convert;
    JLabel amountLabel;
    JLabel fromLabel;
    JLabel toLabel;
    
    
    public DisplayView() {
        currencyLoader = new CurrencyLoaderFromFile("Currencies.txt");
    }
    
    public void display() {
        this.setLocation(200, 100);
        this.setTitle("Money Calculator");
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        initComponents();
        this.pack();
        this.addWindowListener(new WindowCloseManager());
        this.setVisible(true);
    }   
    
    private void initComponents() {
        panel = new JPanel();
        panel2 = new JPanel();
        panel.setPreferredSize(new Dimension(500,80));
        baseAmount = new JTextField();
        result = new JTextField();
        baseCurrency = new JComboBox();
        destinationCurrency = new JComboBox();
        convert = new JButton();
        amountLabel = new JLabel("Amount: ");
        fromLabel = new JLabel("    From: ");
        toLabel = new JLabel("    To: ");
        
        convert.setEnabled(true);
        convert.setText("Convert");
        convert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                convertActionPerformed(event);
            }
        });
        
        baseAmount.setEditable(true);
        baseAmount.setText("0.00");
        baseAmount.setPreferredSize(convert.getPreferredSize());
        result.setEditable(false);
        result.setPreferredSize(convert.getPreferredSize());
        
        fillComboBoxes();
        
        panel.add(amountLabel);
        panel.add(baseAmount);
        panel.add(fromLabel);
        panel.add(baseCurrency);
        panel.add(toLabel);
        panel.add(destinationCurrency);
        panel.add(convert);
        panel2.add(result);
        
        this.add(panel, BorderLayout.CENTER);
        this.add(panel2, BorderLayout.SOUTH);
    }
    
    private void fillComboBoxes() {
        List<Currency> currencies = currencyLoader.loadCurrencies();
        for (Currency currency : currencies) {
            baseCurrency.addItem(currency);
            destinationCurrency.addItem(currency);
        }
    }
    
    private static class WindowCloseManager extends WindowAdapter {
        public void windowClosing(WindowEvent windowEvent) {
            System.exit(0);
        }
    }
    
    private void convertActionPerformed(ActionEvent event) {
        double amount = Double.parseDouble(baseAmount.getText());
        Currency base = (Currency) baseCurrency.getSelectedItem();
        Currency destination = (Currency) destinationCurrency.getSelectedItem();
        ExchangeRateLoaderFromWeb exchangeRateLoader = new ExchangeRateLoaderFromWeb();
        ExchangeRate exchangeRate = exchangeRateLoader.loadExchangeRate(base,destination);
        result.setText(Double.toString(exchangeRate.getRate()));
    }
}
