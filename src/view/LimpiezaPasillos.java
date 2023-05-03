package view;

import control.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LimpiezaPasillos extends JPanel {
    private static final long serialVersionUID = 1L;
    private Controller ctrl;

    public LimpiezaPasillos(String dniTrabajador, Controller ctrl, CardLayout cardLayout, JPanel mainPanel) {
        this.ctrl = ctrl;
        setLayout(new BorderLayout());

        // TopPanel
        TopPanel topPanel = new TopPanel(ctrl.getTrabajadorConDNI(dniTrabajador), ctrl, cardLayout, mainPanel);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());

        List<String> nombresPasillos = ctrl.getPasillos();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        for (int i = 0; i < nombresPasillos.size(); i++) {
            String pasillo = nombresPasillos.get(i);

            JLabel pasilloLabel = new JLabel(pasillo);
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.anchor = GridBagConstraints.LINE_START;
            centerPanel.add(pasilloLabel, gbc);

            JLabel estadoLabel = new JLabel();
            actualizarEstadoPasillo(estadoLabel, pasillo);
            gbc.gridx = 1;
            gbc.gridy = i;
            gbc.anchor = GridBagConstraints.LINE_START;
            centerPanel.add(estadoLabel, gbc);

            JButton solicitarLimpiezaButton = new JButton("Solicitar limpieza");
            solicitarLimpiezaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean cambiado = ctrl.cambiarPasilloASucio(pasillo);

                    if (cambiado) {
                        actualizarEstadoPasillo(estadoLabel, pasillo);
                        JOptionPane.showMessageDialog(LimpiezaPasillos.this, "Solicitud de limpieza enviada para el pasillo " + pasillo + ".", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(LimpiezaPasillos.this, "El pasillo " + pasillo + " ya estaba sucio.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            gbc.gridx = 2;
            gbc.gridy = i;
            gbc.anchor = GridBagConstraints.LINE_START;
            centerPanel.add(solicitarLimpiezaButton, gbc);
        }

        add(centerPanel, BorderLayout.CENTER);
    }

    private void actualizarEstadoPasillo(JLabel estadoLabel, String pasillo) {
        boolean estado = ctrl.estadoPasillo(pasillo);

        if (estado) {
            estadoLabel.setText("Estado: Limpio");
            estadoLabel.setForeground(Color.GREEN);
        } else {
            estadoLabel.setText("Estado: Sucio");
            estadoLabel.setForeground(Color.ORANGE);
        }
    }
}
