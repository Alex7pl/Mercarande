package view;

import java.awt.FlowLayout;

import javax.swing.JFrame;

public class ReponedorWindow extends JFrame {
    public ReponedorWindow() {
        setTitle("Ventana Reponedor");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        // Agrega los componentes de la ventana
        // ...

        pack();
        setLocationRelativeTo(null);
    }
}