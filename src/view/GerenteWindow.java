package view;

import java.awt.FlowLayout;

import javax.swing.JFrame;

public class GerenteWindow extends JFrame {
    public GerenteWindow() {
        setTitle("Ventana Gerente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        // Agrega los componentes de la ventana
        // ...

        pack();
        setLocationRelativeTo(null);
    }
}