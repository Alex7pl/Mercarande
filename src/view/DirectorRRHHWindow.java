package view;

import control.Controller;
import model.Trabajador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DirectorRRHHWindow extends JPanel {
    private static final long serialVersionUID = 1L;
    private String dni;
    private Controller ctrl;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public DirectorRRHHWindow(String dni, Controller ctrl, CardLayout cardLayout, JPanel mainPanel) {
        this.dni = dni;
        this.ctrl = ctrl;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        setLayout(new BorderLayout());

        Trabajador trabajador = ctrl.getTrabajadorConDNI(dni);
        TopPanel topPanel = new TopPanel(trabajador, ctrl, cardLayout, mainPanel);
        add(topPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton newWorkerButton = new JButton("Nuevo trabajador");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        buttonPanel.add(newWorkerButton, gbc);

        JButton modifyWorkerButton = new JButton("Modificar trabajador");
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(modifyWorkerButton, gbc);

        JButton showWorkersButton = new JButton("Mostrar trabajadores");
        gbc.gridx = 0;
        gbc.gridy = 2;
        buttonPanel.add(showWorkersButton, gbc);

        add(buttonPanel, BorderLayout.CENTER);

        // Agrega listeners para los botones y cambia el contenido del panel principal
        newWorkerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RRHHNuevoTrabajador nuevoTrabajadorPanel = new RRHHNuevoTrabajador(dni, ctrl, cardLayout, mainPanel);
                mainPanel.add(nuevoTrabajadorPanel, "RRHHNuevoTrabajador");
                cardLayout.show(mainPanel, "RRHHNuevoTrabajador");
            }
        });

        modifyWorkerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RRHHModificarTrabajador modificarTrabajadorPanel = new RRHHModificarTrabajador(dni, ctrl, cardLayout, mainPanel);
                mainPanel.add(modificarTrabajadorPanel, "RRHHModificarTrabajador");
                cardLayout.show(mainPanel, "RRHHModificarTrabajador");
            }
        });

        showWorkersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RRHHMostrarTrabajadores mostrarTrabajadoresPanel = new RRHHMostrarTrabajadores(dni, ctrl, cardLayout, mainPanel);
                mainPanel.add(mostrarTrabajadoresPanel, "RRHHMostrarTrabajadores");
                cardLayout.show(mainPanel, "RRHHMostrarTrabajadores");
            }
        });
    }
}
