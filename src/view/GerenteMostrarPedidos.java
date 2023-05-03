package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import control.Controller;
import model.Pedido;
import model.Producto;
import resources.Pair;

public class GerenteMostrarPedidos extends JPanel {
    private static final long serialVersionUID = 1L;

    public GerenteMostrarPedidos(String dniTrabajador, Controller ctrl, CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());

        TopPanel topPanel = new TopPanel(ctrl.getTrabajadorConDNI(dniTrabajador), ctrl, cardLayout, mainPanel);
        add(topPanel, BorderLayout.NORTH);

        List<Pedido> pedidos = ctrl.getListaPedidos();

        String[] columnNames = {"ID", "Proveedor", "Categoria", "Precio Pedido", "Fecha Pedido", "Fecha Esperada",
                "Productos"};
        Object[][] rowData = new Object[pedidos.size()][7];

        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedido = pedidos.get(i);
            rowData[i][0] = pedido.getIDPedido();
            rowData[i][1] = pedido.getProveedor();
            rowData[i][2] = pedido.getCategoria();
            rowData[i][3] = pedido.getPrecioPedido();
            rowData[i][4] = pedido.getFechaPedido();
            rowData[i][5] = pedido.getFechaEsperada();

            // Formatear la columna de productos y cantidades
            List<Pair<String, Integer>> productos = pedido.getProductos();
            StringBuilder productosYCantidad = new StringBuilder();
            for (Pair<String, Integer> productoCantidad : productos) {
                productosYCantidad.append(productoCantidad.getFirst());
                productosYCantidad.append(" (");
                productosYCantidad.append(productoCantidad.getSecond());
                productosYCantidad.append("), ");
            }

            // Eliminar la última coma y espacio
            if (productosYCantidad.length() > 0) {
                productosYCantidad.setLength(productosYCantidad.length() - 2);
            }
            rowData[i][6] = productosYCantidad.toString();
        }

        JTable table = new JTable(rowData, columnNames);
        table.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
}
