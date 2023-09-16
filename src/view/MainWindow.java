package view;

import control.Controller;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    @SuppressWarnings("unused")
	private Controller ctrl;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainWindow(Controller ctrl) {
        this.ctrl = ctrl;
        setTitle("Ventana Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        LoginPanel loginPanel = new LoginPanel(ctrl, cardLayout, mainPanel);
        mainPanel.add(loginPanel, "loginPanel");

        setContentPane(mainPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(new Dimension(1200, 800));
    }
}
