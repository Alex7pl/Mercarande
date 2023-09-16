package view;
import control.Controller;
import model.Trabajador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RRHHNuevoTrabajador extends JPanel {
    private static final long serialVersionUID = 1L;
    @SuppressWarnings("unused")
	private String dniTrabajador;
    @SuppressWarnings("unused")
	private Controller ctrl;
    @SuppressWarnings("unused")
	private CardLayout cardLayout;
    @SuppressWarnings("unused")
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
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);

        String[] userTypes = {"Gerente", "Reponedor", "DirectorRRHH", "Limpiador", "Cajero"};
        JComboBox<String> userTypeComboBox = new JComboBox<>(userTypes);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        formPanel.add(new JLabel("Tipo de usuario:"), gridBagConstraints);
        gridBagConstraints.gridx = 1;
        formPanel.add(userTypeComboBox, gridBagConstraints);

        JTextField userTextField = new JTextField(15);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        formPanel.add(new JLabel("Usuario:"), gridBagConstraints);
        gridBagConstraints.gridx = 1;
        formPanel.add(userTextField, gridBagConstraints);

        JPasswordField passwordField = new JPasswordField(15);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        formPanel.add(new JLabel("Contraseña:"), gridBagConstraints);
        gridBagConstraints.gridx = 1;
        formPanel.add(passwordField, gridBagConstraints);

        JTextField nameTextField = new JTextField(15);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        formPanel.add(new JLabel("Nombre:"), gridBagConstraints);
        gridBagConstraints.gridx = 1;
        formPanel.add(nameTextField, gridBagConstraints);

        JTextField dniTextField = new JTextField(15);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        formPanel.add(new JLabel("DNI:"), gridBagConstraints);
        gridBagConstraints.gridx = 1;
        formPanel.add(dniTextField, gridBagConstraints);

        JTextField salaryTextField = new JTextField(15);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        formPanel.add(new JLabel("Salario:"), gridBagConstraints);
        gridBagConstraints.gridx = 1;
        formPanel.add(salaryTextField, gridBagConstraints);

        JTextField entradaTextField = new JTextField(15);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        formPanel.add(new JLabel("Hora de entrada:"), gridBagConstraints);
        gridBagConstraints.gridx = 1;
        formPanel.add(entradaTextField, gridBagConstraints);

        JTextField salidaTextField = new JTextField(15);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        formPanel.add(new JLabel("Hora de salida:"), gridBagConstraints);
        gridBagConstraints.gridx = 1;
        formPanel.add(salidaTextField, gridBagConstraints);

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
				float salary = 0.0f;
				int entrada = 0;
				int salida = 0;

				// Comprobar campos vacíos
				if (tipoUsuario.isEmpty() || user.isEmpty() || password.isEmpty() || name.isEmpty() || dni.isEmpty()
						|| salaryTextField.getText().isEmpty() || entradaTextField.getText().isEmpty()
						|| salidaTextField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(RRHHNuevoTrabajador.this, "Por favor, complete todos los campos",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Validar y convertir los campos numéricos
				try {
					salary = Float.parseFloat(salaryTextField.getText());
					entrada = Integer.parseInt(entradaTextField.getText());
					salida = Integer.parseInt(salidaTextField.getText());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(RRHHNuevoTrabajador.this,
							"Ingrese valores numéricos válidos para Salario, Hora de entrada y Hora de salida", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				boolean existsUser = ctrl.existeUsuraio(user);
				boolean existsTrabajador = ctrl.existeTrabajadorConDNI(dni);

				if (existsUser) {
					JOptionPane.showMessageDialog(RRHHNuevoTrabajador.this, "El usuario ya existe.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (existsTrabajador) {
					JOptionPane.showMessageDialog(RRHHNuevoTrabajador.this,
							"El trabajador con el DNI especificado ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					boolean added = ctrl.nuevoTrabajador(tipoUsuario, user, password, name, dni, salary, entrada,
							salida);

					if (added) {
						JOptionPane.showMessageDialog(RRHHNuevoTrabajador.this, "Trabajador añadido con éxito",
								"Confirmación", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(RRHHNuevoTrabajador.this,
								"No se pudo añadir el trabajador. Compruebe los datos e inténtelo de nuevo.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(addButton);

		add(buttonPanel, BorderLayout.SOUTH);
	}
}