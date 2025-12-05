package vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private PanelCreacion panelCreacion;
    private PanelLista panelLista;
    private PanelDetalles panelDetalles;

    public VentanaPrincipal() {
        setTitle("Sistema de Gestión de Vehículos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear pestañas (tabs)
        JTabbedPane tabbedPane = new JTabbedPane();

        panelCreacion = new PanelCreacion();
        panelLista = new PanelLista();
        panelDetalles = new PanelDetalles();

        tabbedPane.addTab("Crear Vehículo", panelCreacion);
        tabbedPane.addTab("Lista de Vehículos", panelLista);
        tabbedPane.addTab("Detalles Electrónicos", panelDetalles);

        add(tabbedPane);

        // Configurar colores como en la imagen
        getContentPane().setBackground(new Color(240, 240, 240));
    }
}