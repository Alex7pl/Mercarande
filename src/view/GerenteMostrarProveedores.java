package view;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import control.Controller;
import model.Categoria;
import model.Proveedor;
import model.Trabajador;

public class GerenteMostrarProveedores extends JPanel {
    private static final long serialVersionUID = 1L;
    private Controller ctrl;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public GerenteMostrarProveedores(String dniTrabajador, Controller ctrl, CardLayout cardLayout, JPanel mainPanel) {
        this.ctrl = ctrl;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        setLayout(new BorderLayout());

        TopPanel topPanel = new TopPanel(ctrl.getTrabajadorConDNI(dniTrabajador), ctrl, cardLayout, mainPanel);
        add(topPanel, BorderLayout.NORTH);

        List<Proveedor> proveedores = ctrl.getProveedores();
           

        String[] columnNames = {"ID", "NIF", "Nombre", "Domicilio Fiscal", "Email", "Telefono","Productos","Categoria"};
        Object[][] rowData = new Object[proveedores.size()][8];

        for (int i = 0; i < proveedores.size(); i++) {
            Proveedor proveedor = proveedores.get(i);
            rowData[i][0] = proveedor.getID();
            rowData[i][1] = proveedor.getNIF();
            rowData[i][2] = proveedor.getNombre();
            rowData[i][3] = proveedor.getDomicilioFiscal();
            rowData[i][4] = proveedor.getEmail();
            rowData[i][5] = proveedor.getTelefono();
            rowData[i][6] = proveedor.getProductos();
            rowData[i][7] = proveedor.getCategoria();
        }

        JTable table = new JTable(rowData, columnNames);
        table.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
}
