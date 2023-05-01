package view;

import java.awt.FlowLayout;

import javax.swing.JFrame;

public class LimpiadorWindow  extends JFrame {
    public LimpiadorWindow() {
        setTitle("Ventana Limpiador");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        // Agrega los componentes de la ventana
        // ...

        pack();
        setLocationRelativeTo(null);
    }
}