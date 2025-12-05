package vista;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import modelo.*;

public class PanelLista extends JPanel {
    private JList<String> listaVehiculos;
    private DefaultListModel<String> modeloLista;
    private JButton btnSeleccionar;

    public PanelLista() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0, 100, 200), 2),
                "Vehículos Creados",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14),
                new Color(0, 100, 200)
        ));

        // Modelo de la lista con datos de ejemplo
        modeloLista = new DefaultListModel<>();
        modeloLista.addElement("Vehículo A - Carro (Rojo)");
        modeloLista.addElement("Vehículo B - Moto (Azul)");
        modeloLista.addElement("Vehículo C - Bicicleta (Verde)");

        // Lista de vehículos
        listaVehiculos = new JList<>(modeloLista);
        listaVehiculos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaVehiculos.setFont(new Font("Monospaced", Font.PLAIN, 12));
        listaVehiculos.setBackground(new Color(250, 250, 250));

        JScrollPane scrollPane = new JScrollPane(listaVehiculos);
        add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSeleccionar = new JButton("Seleccionar Vehículo");
        btnSeleccionar.setBackground(new Color(0, 150, 0));
        btnSeleccionar.setForeground(Color.WHITE);

        btnSeleccionar.addActionListener(e -> {
            int indice = listaVehiculos.getSelectedIndex();
            if (indice != -1) {
                String seleccionado = modeloLista.getElementAt(indice);
                mostrarDetallesElectronica(seleccionado);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Seleccione un vehículo de la lista",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        panelBotones.add(btnSeleccionar);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void mostrarDetallesElectronica(String vehiculo) {
        // Aquí se mostrarían los detalles electrónicos del vehículo seleccionado
        // Por ahora mostramos un mensaje
        JOptionPane.showMessageDialog(this,
                "Mostrando electrónica de: " + vehiculo + "\n" +
                        "Sistema: Sist. Combustión v2.1\n" +
                        "Estado: Operativo",
                "Detalles Electrónicos",
                JOptionPane.INFORMATION_MESSAGE);
    }
}