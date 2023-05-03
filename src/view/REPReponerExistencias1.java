package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.Controller;

public class REPReponerExistencias1 extends JPanel {

    private static final long serialVersionUID = 1L;
    private Controller ctrl;
    private String DNIT;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JComboBox<String> pasillosBox;
    private DefaultComboBoxModel<String> pasillosList;

    public REPReponerExistencias1(String DNIT, Controller ctrl, CardLayout cardLayout, JPanel mainPanel) {
        this.ctrl = ctrl;
        this.DNIT = DNIT;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        setLayout(new BorderLayout());

        initGUI();
    }

    private void initGUI() {
        // Crear el JComboBox y agregar los pasillos
        this.pasillosList = new DefaultComboBoxModel<>();
        this.anyadirPasillos();
        this.pasillosBox = new JComboBox<>(pasillosList);
        pasillosBox.setPreferredSize(new Dimension(150, 30));

        // Crear el botón Aceptar y agregar un ActionListener
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pasilloSeleccionado = (String) pasillosBox.getSelectedItem();
                // Lógica para aceptar el producto seleccionado

                if(ctrl.getProductosID(pasilloSeleccionado).isEmpty()) {
                	 JOptionPane.showMessageDialog(REPReponerExistencias1.this, "No existen productos en este pasillo", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
	                REPReponerExistencias2 reponerPanel = new REPReponerExistencias2(pasilloSeleccionado, DNIT, ctrl, cardLayout, mainPanel);
	                mainPanel.add(reponerPanel, "REPReponerExistencias2");
	                cardLayout.show(mainPanel, "REPReponerExistencias2");
                }
            }
        });

        // Crear el panel y configurar el GridBagLayout
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel userLabel = new JLabel("Seleciona el pasillo que quieres reponer:");
        centerPanel.add(userLabel, gbc);
        gbc.gridy++;
        centerPanel.add(pasillosBox, gbc);
        gbc.gridy++;
        centerPanel.add(btnAceptar, gbc);

        // Agregar el panel al this panel
        this.setLayout(new BorderLayout());
        this.add(new TopPanel(ctrl.getTrabajadorConDNI(DNIT), ctrl, cardLayout, mainPanel), BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
    }

    private void anyadirPasillos() {
        List<String> pasillos = this.ctrl.getPasillos();

        for (String p : pasillos) {
            this.pasillosList.addElement(p);
        }
    }
}