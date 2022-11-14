package View;

import Control.Command;
import Model.Currency;
import Model.Money;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

public class DialogPanel extends JPanel implements Dialog {
    private final List<Currency> currencies;
    Command convertCommand;
    JTextField baseAmount;
    JTextField result;
    JComboBox baseCurrency;
    JComboBox destinationCurrency;
    JButton swap;
    JButton convert;
    JLabel amountLabel;
    JLabel fromLabel;
    JLabel toLabel;
    
    public DialogPanel(List<Currency> currencies) {
        this.currencies = currencies;
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));
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
        convert.setPreferredSize(new Dimension(400, 52));
        convert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCommand.execute();
            }
        });
        
        baseAmount.setEditable(true);
        baseAmount.setPreferredSize(new Dimension(250,26));
        result.setPreferredSize(new Dimension(600,26));
        result.setEditable(false);
        result.setHorizontalAlignment(JTextField.CENTER);
        
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
    
    @Override
    public void addCommand(Command command) {
        this.convertCommand = command;
    }
    
    public void refreshResult(Money money) {
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
