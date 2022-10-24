package View;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MoneyCalculatorView extends JFrame {
    public MoneyCalculatorView(JPanel panel, String title) {
        super(title);
        getContentPane().add(panel);
        addWindowListener(new WindowClosedManager());
        setPreferredSize(new Dimension(970,250));
        pack(); 
        setVisible(true);
    }    

    private static class WindowClosedManager extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent windowEvent) {
            System.exit(0);
        }
    }
}
