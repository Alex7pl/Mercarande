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

public class REPRecepcionarPedido1 extends JPanel {
	private static final long serialVersionUID = 1L;
    private Controller ctrl;
    private String DNIT;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public REPRecepcionarPedido1(String dniTrabajador, Controller ctrl, CardLayout cardLayout, JPanel mainPanel) {
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
        JLabel titleLabel = new JLabel("Selecciona en la tabla el pedido a recepcionar:");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        northPanel.add(titleLabel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);

        List<Pedido> pedidos = ctrl.getListaPedidos();

        String[] columnNames = {"ID Pedido", "NIF proveedor", "Categoria", "precioPedido", "fechaPedido", "fechaEsperada"};
        Object[][] rowData = new Object[pedidos.size()][6];

        for (int i = 0; i < pedidos.size(); i++) {
            Pedido p = pedidos.get(i);
            rowData[i][0] = p.getIDPedido();
            rowData[i][1] = p.getProveedor();
            rowData[i][2] = p.getCategoria();
            rowData[i][3] = p.getPrecioPedido();
            rowData[i][4] = p.getFechaPedido();
            rowData[i][5] = p.getFechaEsperada();
        }

        JTable table = new JTable(rowData, columnNames);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Obtenga los datos de la fila seleccionada
                	Pedido pedido = pedidos.get(selectedRow);
                	int opcion = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres recepcionar este pedido?",
                            "Confirmar Recepción de Pedido", JOptionPane.YES_NO_OPTION);
                	if (opcion == JOptionPane.YES_OPTION) {		
                		this.ctrl.recepcionarPedido(DNIT, pedido.getIDPedido(), pedido.getCategoria());
                		JOptionPane.showMessageDialog(REPRecepcionarPedido1.this, "Pedido recepcionado", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
                	}
                    
                }
            }
        });
    }
}
