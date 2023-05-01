package view;

import java.awt.FlowLayout;

import javax.swing.JFrame;

public class CajeroWindow  extends JFrame {
    public CajeroWindow() {
        setTitle("Ventana Cajero");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        // Agrega los componentes de la ventana
        // ...

        pack();
        setLocationRelativeTo(null);
    }
}