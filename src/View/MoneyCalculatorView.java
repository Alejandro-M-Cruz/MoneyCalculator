package View;

import java.awt.Dimension;
import javax.swing.JFrame;

public class MoneyCalculatorView extends JFrame {
    public MoneyCalculatorView(DialogPanel panel, String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(panel);
        setPreferredSize(new Dimension(970,250));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }    
}
