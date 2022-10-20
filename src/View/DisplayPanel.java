package View;

import Control.Controller;
import Model.Currency;
import Model.ExchangeRate;
import Model.Money;
import Persistency.CurrencyLoaderFromFile;
import Web.ExchangeRateLoaderFromWeb;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.*;

public class DisplayPanel extends JPanel implements DisplayView {
    private List<Currency> currencies;
    private Controller controller = new Controller(this);
    
    JPanel panel;
    JPanel panel2;
    JTextField baseAmount;
    JTextField result;
    JComboBox baseCurrency;
    JComboBox destinationCurrency;
    JButton swap;
    JButton convert;
    JLabel amountLabel;
    JLabel fromLabel;
    JLabel toLabel;
    
    public DisplayPanel(List<Currency> currencies) {
        this.currencies = currencies;
        this.controller = new Controller(this);
              
        initComponents();
        this.setVisible(true);
    }
    
    private void initComponents() {
        baseAmount = new JTextField();
        result = new JTextField();
        baseCurrency = new JComboBox();
        destinationCurrency = new JComboBox();
        swap = new JButton("Swap curreencies");
        convert = new JButton("Convert");
        amountLabel = new JLabel("Amount: ");
        fromLabel = new JLabel(" From: ");
        toLabel = new JLabel(" To: ");
        
        swap.setEnabled(true);
        swap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapCurrencies();
            }
        });
        
        convert.setEnabled(true);
        convert.addActionListener(controller);
        
        baseAmount.setEditable(true);
        baseAmount.setText("0.00");
        baseAmount.setPreferredSize(new Dimension(120,26));
        result.setPreferredSize(new Dimension(250,26));
        result.setEditable(false);
        
        fillComboBoxes();
        this.add(amountLabel);
        this.add(baseAmount);
        this.add(fromLabel);
        this.add(baseCurrency);
        this.add(swap);
        this.add(toLabel);
        this.add(destinationCurrency);
        this.add(convert);
        this.add(result);
        
    }
    
    private void swapCurrencies() {
        Currency base = (Currency) baseCurrency.getSelectedItem();
        baseCurrency.setSelectedItem(destinationCurrency.getSelectedItem());
        destinationCurrency.setSelectedItem(base);
    }
    
    private void fillComboBoxes() {
        for (Currency currency : currencies) {
            baseCurrency.addItem(currency);
            destinationCurrency.addItem(currency);
        }
    }
    
    public void refreshResult(Money money) {
        DecimalFormat df = new DecimalFormat("0.000000");
        result.setText(money.toString());
    }
    
    public Money getBaseMoney() {
        try {
            String base = baseAmount.getText().replace(",",".");
            return new Money(Double.parseDouble(base), (Currency) baseCurrency.getSelectedItem());
        } catch(Exception e) {}
        return new Money(0.0,null);
    }
    
    public void displayErrorMessage(String error) {
        result.setText(error);
    }
    
    public Currency getDestinationCurrency() {
        return (Currency) destinationCurrency.getSelectedItem();
    }
    
}
