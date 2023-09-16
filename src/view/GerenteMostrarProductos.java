package view;

import control.Controller;
import model.Pasillo;
import model.Producto;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GerenteMostrarProductos extends JPanel {
    private static final long serialVersionUID = 1L;
    @SuppressWarnings("unused")
	private Controller ctrl;
    @SuppressWarnings("unused")
	private CardLayout cardLayout;
    @SuppressWarnings("unused")
	private JPanel mainPanel;
    private List<Pasillo>tienda;
    private List<Producto>productos;
    
    public GerenteMostrarProductos(String dniTrabajador, Controller ctrl, CardLayout cardLayout, JPanel mainPanel) {
        this.ctrl = ctrl;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        setLayout(new BorderLayout());

        TopPanel topPanel = new TopPanel(ctrl.getTrabajadorConDNI(dniTrabajador), ctrl, cardLayout, mainPanel);
        add(topPanel, BorderLayout.NORTH);
        
        
       tienda = ctrl.getTienda();
       productos = tienda.get(0).getListaProductos();
    int tamano =0;
       
        String[] columnNames = {"ID", "Nombre", "IDproveedor", "Marca", "Categoria", "Precio", "Unidades"};
       for (int k=0;k<tienda.size();k++) {
    	   tamano = tamano+tienda.get(k).getListaProductos().size();
       }
        Object[][] rowData = new Object[tamano][7];

        int in=0;
        for (int j=0;j<tienda.size();j++) {
        productos = tienda.get(j).getListaProductos();
        	for (int i = 0; i < productos.size(); i++) {
           Producto p = productos.get(i);
            rowData[in][0] = p.getID();
            rowData[in][1] = p.getNombre();
            rowData[in][2] = p.getProveedor();
            rowData[in][3] = p.getMarca();
            rowData[in][4] = p.getCategoria();
            rowData[in][5] = p.getPrecio();
            rowData[in][6] = p.getUnidades();
            in++;
        }
        }
        
        JTable table = new JTable(rowData, columnNames);
        table.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
}
