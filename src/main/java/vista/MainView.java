package vista;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mpliego on 10/5/15.
 */
public class MainView extends JPanel {

    JTabbedPane tabbedPane = new JTabbedPane();
    JPanel clientTab = new ClientView();

    public MainView() {
        initUI();
    }

    private void initUI() {
        setPreferredSize(new Dimension(640, 360));
        setLayout(new BorderLayout());

        tabbedPane.addTab("Clientes", clientTab);
        tabbedPane.setVisible(true);

        add(tabbedPane);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new MainView());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
