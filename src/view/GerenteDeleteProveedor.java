package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.Controller;
import model.Proveedor;

public class GerenteDeleteProveedor extends JPanel {
	private static final long serialVersionUID = 1L;
	private JComboBox<Object> proveedoresCombo;

	public GerenteDeleteProveedor(String dniTrabajador, Controller ctrl, CardLayout cardLayout, JPanel mainPanel) {
		setLayout(new BorderLayout());

		TopPanel topPanel = new TopPanel(ctrl.getTrabajadorConDNI(dniTrabajador), ctrl, cardLayout, mainPanel);
		add(topPanel, BorderLayout.NORTH);
		proveedoresCombo = new JComboBox<>();

		List<Proveedor> proveedores = ctrl.getProveedores();
		for (Proveedor proveedor : proveedores) {
			proveedoresCombo.addItem(proveedor.getNombre());
		}

		JButton delete = new JButton("Eliminar");
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Proveedor p = ctrl.getProveedorID(proveedoresCombo.getSelectedItem().toString());
				ctrl.getTrabajadorConDNI(dniTrabajador).eliminarProveedor(p.getID());
				JOptionPane.showMessageDialog(GerenteDeleteProveedor.this, "Proveedor eliminado con exito",
						"Confirmación", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		JPanel formPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);


		formPanel.add(proveedoresCombo, gridBagConstraints);

		gridBagConstraints.gridy = 2;
		formPanel.add(delete, gridBagConstraints);

		this.add(formPanel);
	}//// CONSTR

}
