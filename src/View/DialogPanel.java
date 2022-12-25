package View;

import Control.Command;
import Model.Currency;
import Model.Money;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

public class DialogPanel extends JPanel implements Dialog {
    private final List<Currency> currencies;
    private final Map<String,Command> commands;
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
        this.commands = new HashMap();
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));
        initComponents();
        this.setVisible(true);
    }

    private void initComponents() {
        baseAmount = new JTextField();
        result = new JTextField();
        baseCurrency = new JComboBox();
        destinationCurrency = new JComboBox();
        amountLabel = new JLabel("Amount: ");
        fromLabel = new JLabel(" From: ");
        toLabel = new JLabel(" To: ");
        
        swap = button("swap", "Swap curreencies");
        convert = button("convert", "Convert");
        convert.setPreferredSize(new Dimension(400, 52));
        
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
    
    private JButton button(String name, String label) {
        JButton button = new JButton(label);
        button.setEnabled(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get(name).execute();
            }
        });
        return button;
    }
    
    @Override
    public void swapCurrencies() {
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
    public void addCommand(String name, Command command) {
        this.commands.put(name, command);
    }
    
    @Override
    public void updateResult(Money money) {
        result.setText(money.toString());
    }
    
    @Override
    public Money getBaseMoney() {
        try {
            return new Money(Double.parseDouble(baseAmount.getText().replace(",",".")), (Currency) baseCurrency.getSelectedItem());
        } catch(NumberFormatException e) {
            return new Money(0.0,null);
        }
    }
    
    @Override
    public void displayErrorMessage(String error) {
        result.setText(error);
    }
    
    @Override
    public Currency getDestinationCurrency() {
        return (Currency) destinationCurrency.getSelectedItem();
    }
}
