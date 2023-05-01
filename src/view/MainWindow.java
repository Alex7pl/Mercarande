package view;

import javax.swing.*;

import control.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controller ctrl;

	public MainWindow(Controller ctrl) {
		this.ctrl = ctrl;
        setTitle("Ventana Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton directorRRHHButton = new JButton("DirectorRRHH");
        directorRRHHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DirectorRRHHWindow().setVisible(true);
            }
        });
        add(directorRRHHButton);

        JButton gerenteButton = new JButton("Gerente");
        gerenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GerenteWindow().setVisible(true);
            }
        });
        add(gerenteButton);

        JButton reponedorButton = new JButton("Reponedor");
        reponedorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReponedorWindow().setVisible(true);
            }
        });
        add(reponedorButton);

        JButton cajeroButton = new JButton("Cajero");
        cajeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CajeroWindow().setVisible(true);
            }
        });
        add(cajeroButton);

        JButton limpiadorButton = new JButton("Limpiador");
        limpiadorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LimpiadorWindow().setVisible(true);
            }
        });
        add(limpiadorButton);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
