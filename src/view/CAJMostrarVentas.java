package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import control.Controller;
import model.Pedido;
import model.Venta;

public class CAJMostrarVentas extends JPanel {
	private static final long serialVersionUID = 1L;
    private Controller ctrl;
    private String DNIT;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public CAJMostrarVentas(String dniTrabajador, Controller ctrl, CardLayout cardLayout, JPanel mainPanel) {
        this.ctrl = ctrl;
        this.DNIT = dniTrabajador;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        setLayout(new BorderLayout());
        
        initGUI();
    }
    
    private void initGUI() {
        JPanel northPanel = new JPanel(new BorderLayout());
        TopPanel topPanel = new TopPanel(ctrl.getTrabajadorConDNI(DNIT), ctrl, cardLayout, mainPanel);
        northPanel.add(topPanel, BorderLayout.NORTH);
        add(northPanel, BorderLayout.NORTH);

        List<Venta> ventas = ctrl.getVentas();

        String[] columnNames = {"ID Venta", "IDCajero", "importe", "fecha", "ª productos vendidos"};
        Object[][] rowData = new Object[ventas.size()][5];

        for (int i = 0; i < ventas.size(); i++) {
            Venta v = ventas.get(i);
            rowData[i][0] = v.getID();
            rowData[i][1] = v.getIDCajero();
            rowData[i][2] = v.getImporte();
            rowData[i][3] = v.getFecha();
            rowData[i][4] = v.getProductos().size();
        }

        JTable table = new JTable(rowData, columnNames);
        table.setEnabled(false);
        
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
    }
}
