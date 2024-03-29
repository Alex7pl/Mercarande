package view;

import control.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LimpiadorWindow extends JPanel {
    private static final long serialVersionUID = 1L;
    private Controller ctrl;

    public LimpiadorWindow(String dniTrabajador, Controller ctrl, CardLayout cardLayout, JPanel mainPanel) {
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

            JButton limpiarButton = new JButton("Limpiar");
            limpiarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean limpio = ctrl.limpiarPasillo(pasillo);

                    if (limpio) {
                        actualizarEstadoPasillo(estadoLabel, pasillo);
                        JOptionPane.showMessageDialog(LimpiadorWindow.this, "Pasillo " + pasillo + " limpiado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(LimpiadorWindow.this, "El pasillo " + pasillo + " ya estaba limpio.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            gbc.gridx = 2;
            gbc.gridy = i;
            gbc.anchor = GridBagConstraints.LINE_START;
            centerPanel.add(limpiarButton, gbc);
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
