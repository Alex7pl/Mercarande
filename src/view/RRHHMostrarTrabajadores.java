package view;

import control.Controller;
import model.Trabajador;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RRHHMostrarTrabajadores extends JPanel {
    private static final long serialVersionUID = 1L;
    @SuppressWarnings("unused")
	private Controller ctrl;
    @SuppressWarnings("unused")
	private CardLayout cardLayout;
    @SuppressWarnings("unused")
	private JPanel mainPanel;

    public RRHHMostrarTrabajadores(String dniTrabajador, Controller ctrl, CardLayout cardLayout, JPanel mainPanel) {
        this.ctrl = ctrl;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        setLayout(new BorderLayout());

        TopPanel topPanel = new TopPanel(ctrl.getTrabajadorConDNI(dniTrabajador), ctrl, cardLayout, mainPanel);
        add(topPanel, BorderLayout.NORTH);

        List<Trabajador> trabajadores = ctrl.getTrabajadores();

        String[] columnNames = {"Tipo de Usuario", "Nombre", "DNI", "Salario", "Entrada", "Salida"};
        Object[][] rowData = new Object[trabajadores.size()][6];

        for (int i = 0; i < trabajadores.size(); i++) {
            Trabajador trabajador = trabajadores.get(i);
            rowData[i][0] = trabajador.getTipoT();
            rowData[i][1] = trabajador.getNombre();
            rowData[i][2] = trabajador.getDNI();
            rowData[i][3] = trabajador.getSalario();
            rowData[i][4] = trabajador.getHoraEntrada();
            rowData[i][5] = trabajador.getHoraSalida();
        }

        JTable table = new JTable(rowData, columnNames);
        table.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
}
