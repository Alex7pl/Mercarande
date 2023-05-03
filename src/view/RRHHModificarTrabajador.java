package view;

import control.Controller;
import model.Trabajador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RRHHModificarTrabajador extends JPanel {
	private static final long serialVersionUID = 1L;
	private Controller ctrl;
	private CardLayout cardLayout;
	private JPanel mainPanel;
	private JComboBox<String> trabajadoresComboBox;
	private Trabajador trabajadorSeleccionado;
	private JTextField nombreTextField;
	private JTextField dniTextField;
	private JTextField salarioTextField;
	private JTextField entradaTextField;
	private JTextField salidaTextField;

	public RRHHModificarTrabajador(String dniTrabajador, Controller ctrl, CardLayout cardLayout, JPanel mainPanel) {
		this.ctrl = ctrl;
		this.cardLayout = cardLayout;
		this.mainPanel = mainPanel;
		setLayout(new BorderLayout());

		// TopPanel
		TopPanel topPanel = new TopPanel(ctrl.getTrabajadorConDNI(dniTrabajador), ctrl, cardLayout, mainPanel);
		add(topPanel, BorderLayout.NORTH);

		// Formulario
		JPanel formPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		formPanel.add(new JLabel("Seleccione trabajador:"), gridBagConstraints);

		List<Trabajador> trabajadores = listaTrabajadoresActualizada();
		String[] trabajadoresArray = new String[trabajadores.size()];
		for (int i = 0; i < trabajadores.size(); i++) {
			trabajadoresArray[i] = trabajadores.get(i).getTipo() + " - " + trabajadores.get(i).getNombre() + " - "
					+ trabajadores.get(i).getDNI();
		}
		trabajadoresComboBox = new JComboBox<>(trabajadoresArray);
		trabajadoresComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = trabajadoresComboBox.getSelectedIndex();
				if (selectedIndex >= 0 && selectedIndex < trabajadores.size()) {
					trabajadorSeleccionado = trabajadores.get(selectedIndex);
				}
				// Actualizar el formulario con los datos del trabajador seleccionado
				actualizarFormulario();
			}
		});

		gridBagConstraints.gridx = 1;
		formPanel.add(trabajadoresComboBox, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		formPanel.add(new JLabel("Tipo de usuario:"), gridBagConstraints);

		String[] tiposUsuario = { "Cajero", "DirectorRRHH", "Gerente", "Limpiador", "Reponedor" };
		JComboBox<String> tipoUsuarioComboBox = new JComboBox<>(tiposUsuario);
		gridBagConstraints.gridx = 1;
		formPanel.add(tipoUsuarioComboBox, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		formPanel.add(new JLabel("Nombre:"), gridBagConstraints);

		nombreTextField = new JTextField(30);
		gridBagConstraints.gridx = 1;
		formPanel.add(nombreTextField, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		formPanel.add(new JLabel("DNI:"), gridBagConstraints);

		dniTextField = new JTextField(30);
		dniTextField.setEditable(false);
		gridBagConstraints.gridx = 1;
		formPanel.add(dniTextField, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		formPanel.add(new JLabel("Salario:"), gridBagConstraints);

		salarioTextField = new JTextField(30);
		gridBagConstraints.gridx = 1;
		formPanel.add(salarioTextField, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 5;
		formPanel.add(new JLabel("Hora de entrada:"), gridBagConstraints);

		entradaTextField = new JTextField(30);
		gridBagConstraints.gridx = 1;
		formPanel.add(entradaTextField, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 6;
		formPanel.add(new JLabel("Hora de salida:"), gridBagConstraints);

		salidaTextField = new JTextField(30);
		gridBagConstraints.gridx = 1;
		formPanel.add(salidaTextField, gridBagConstraints);

		add(formPanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		JButton modifyButton = new JButton("Modificar");
		modifyButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (trabajadorSeleccionado != null) {
		            // Leer los datos del formulario
		            String tipoUsuario = (String) tipoUsuarioComboBox.getSelectedItem();
		            String nombre = nombreTextField.getText();
		            String dni = dniTextField.getText();
		            float salario = 0.0f;
		            int entrada = 0;
		            int salida = 0;

		            // Comprobar campos vacíos
		            if (nombre.isEmpty() || dni.isEmpty() || salarioTextField.getText().isEmpty()
		                    || entradaTextField.getText().isEmpty() || salidaTextField.getText().isEmpty()) {
		                JOptionPane.showMessageDialog(RRHHModificarTrabajador.this, "Por favor, complete todos los campos",
		                        "Error", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            // Validar y convertir los campos numéricos
		            try {
		                salario = Float.parseFloat(salarioTextField.getText());
		                entrada = Integer.parseInt(entradaTextField.getText());
		                salida = Integer.parseInt(salidaTextField.getText());
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(RRHHModificarTrabajador.this,
		                        "Ingrese valores numéricos válidos para Salario, Hora de entrada y Hora de salida",
		                        "Error", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            // Realizar la modificación del trabajador seleccionado
		            boolean modifico = ctrl.modificarTrabajador(tipoUsuario, nombre, dni, salario, entrada, salida);

		            if (modifico) {
		                JOptionPane.showMessageDialog(RRHHModificarTrabajador.this, "Trabajador modificado con éxito",
		                        "Confirmación", JOptionPane.INFORMATION_MESSAGE);
		            } else {
		                JOptionPane.showMessageDialog(RRHHModificarTrabajador.this,
		                        "No se pudo modificar el trabajador. Compruebe los datos e inténtelo de nuevo.", "Error",
		                        JOptionPane.ERROR_MESSAGE);
		            }
		        } else {
		            JOptionPane.showMessageDialog(RRHHModificarTrabajador.this, "No hay trabajador seleccionado para modificar.",
		                    "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		buttonPanel.add(modifyButton);



		JButton deleteButton = new JButton("Eliminar");
		deleteButton.setForeground(Color.RED);
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (trabajadorSeleccionado != null) {
					String dniTrabajadorSeleccionado = trabajadorSeleccionado.getDNI();
					String dniUsuarioActual = dniTrabajador;

					if (dniTrabajadorSeleccionado.equals(dniUsuarioActual)) {
						JOptionPane.showMessageDialog(RRHHModificarTrabajador.this,
								"No se puede eliminar el usuario en uso", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						boolean eliminado = ctrl.eliminarTrabajador(dniTrabajadorSeleccionado);
						if (eliminado) {
							trabajadorSeleccionado = null;
							actualizarDesplegable();
							JOptionPane.showMessageDialog(RRHHModificarTrabajador.this,
									"Trabajador eliminado con éxito", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(RRHHModificarTrabajador.this,
									"No se pudo eliminar el trabajador. Compruebe el DNI e inténtelo de nuevo.",
									"Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				} else {
					JOptionPane.showMessageDialog(RRHHModificarTrabajador.this,
							"No hay trabajador seleccionado para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		buttonPanel.add(deleteButton);

		add(buttonPanel, BorderLayout.SOUTH);
	}

	private List<Trabajador> listaTrabajadoresActualizada() {
		return ctrl.getTrabajadores();
	}

	private void actualizarDesplegable() {
		trabajadoresComboBox.removeAllItems();
		List<Trabajador> trabajadores = ctrl.getTrabajadores();
		for (Trabajador trabajador : trabajadores) {
			trabajadoresComboBox
					.addItem(trabajador.getTipo() + " - " + trabajador.getNombre() + " - " + trabajador.getDNI());
		}
	}

	// Método para actualizar el formulario con los datos del trabajador
	// seleccionado
	private void actualizarFormulario() {
	    if (trabajadorSeleccionado != null) {
	        nombreTextField.setText(trabajadorSeleccionado.getNombre());
	        dniTextField.setText(trabajadorSeleccionado.getDNI());
	        salarioTextField.setText(String.valueOf(trabajadorSeleccionado.getSalario()));
	        entradaTextField.setText(String.valueOf(trabajadorSeleccionado.getHoraEntrada()));
	        salidaTextField.setText(String.valueOf(trabajadorSeleccionado.getHoraSalida()));
	    } else {
	        nombreTextField.setText("");
	        dniTextField.setText("");
	        salarioTextField.setText("");
	        entradaTextField.setText("");
	        salidaTextField.setText("");
	    }
	}
}
