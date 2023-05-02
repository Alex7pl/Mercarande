package view;

import control.Controller;
import model.Trabajador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RRHHNuevoTrabajador extends JPanel {
    private static final long serialVersionUID = 1L;
    private String dniTrabajador;
    private Controller ctrl;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public RRHHNuevoTrabajador(String dniTrabajador, Controller ctrl, CardLayout cardLayout, JPanel mainPanel) {
        this.ctrl = ctrl;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        setLayout(new BorderLayout());

        Trabajador trabajador = ctrl.getTrabajadorConDNI(dniTrabajador);
        TopPanel topPanel = new TopPanel(trabajador, ctrl, cardLayout, mainPanel);
        add(topPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 5, 5);

        String[] userTypes = {"Gerente", "Reponedor", "DirectorRRHH", "Limpiador", "Cajero"};
        JComboBox<String> userTypeComboBox = new JComboBox<>(userTypes);
        formPanel.add(new JLabel("Tipo de usuario:"), gbc);
        gbc.gridy++;
        formPanel.add(userTypeComboBox, gbc);
        gbc.gridy++;

        JTextField userTextField = new JTextField(15);
        formPanel.add(new JLabel("Usuario:"), gbc);
        gbc.gridy++;
        formPanel.add(userTextField, gbc);
        gbc.gridy++;

        JPasswordField passwordField = new JPasswordField(15);
        formPanel.add(new JLabel("Contraseña:"), gbc);
        gbc.gridy++;
        formPanel.add(passwordField, gbc);
        gbc.gridy++;

        JTextField nameTextField = new JTextField(15);
        formPanel.add(new JLabel("Nombre:"), gbc);
        gbc.gridy++;
        formPanel.add(nameTextField, gbc);
        gbc.gridy++;

        JTextField dniTextField = new JTextField(15);
        formPanel.add(new JLabel("DNI:"), gbc);
        gbc.gridy++;
        formPanel.add(dniTextField, gbc);
        gbc.gridy++;

        JTextField salaryTextField = new JTextField(15);
        formPanel.add(new JLabel("Salario:"), gbc);
        gbc.gridy++;
        formPanel.add(salaryTextField, gbc);
        gbc.gridy++;

        JTextField entradaTextField = new JTextField(15);
        formPanel.add(new JLabel("Hora de entrada:"), gbc);
        gbc.gridy++;
        formPanel.add(entradaTextField, gbc);
        gbc.gridy++;

        JTextField salidaTextField = new JTextField(15);
        formPanel.add(new JLabel("Hora de salida:"), gbc);
        gbc.gridy++;
        formPanel.add(salidaTextField, gbc);
        gbc.gridy++;

        add(formPanel, BorderLayout.CENTER);

        JButton addButton = new JButton("Añadir");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoUsuario = (String) userTypeComboBox.getSelectedItem();
                String user = userTextField.getText();
                String password = new String(passwordField.getPassword());
                String name = nameTextField.getText();
                String dni = dniTextField.getText();
                float salary = Float.parseFloat(salaryTextField.getText());
                int entrada = Integer.parseInt(entradaTextField.getText());
                int salida = Integer.parseInt(salidaTextField.getText());

                boolean added = ctrl.nuevoTrabajador(tipoUsuario, user, password, name, dni, salary, entrada, salida);

                if (added) {
                    JOptionPane.showMessageDialog(RRHHNuevoTrabajador.this, "Trabajador añadido con éxito", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(RRHHNuevoTrabajador.this, "No se pudo añadir el trabajador. Compruebe los datos e inténtelo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}

