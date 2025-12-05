package vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private PanelCreacion panelCreacion;
    private PanelLista panelLista;
    private PanelDetalles panelDetalles;

    public VentanaPrincipal() {
        setTitle("Sistema de Gestión de Vehículos");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panelCreacion = new PanelCreacion();
        panelLista = new PanelLista();
        panelDetalles = new PanelDetalles();

        // Layout principal: una sola ventana dividida en dos columnas
        JSplitPane splitHorizontal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitHorizontal.setResizeWeight(0.45); // izquierda 45%, derecha 55%

        // Panel izquierdo: creación (tipo arriba-izquierda y inputs a la derecha están manejados internamente)
        JPanel izquierdo = new JPanel(new BorderLayout());
        izquierdo.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        izquierdo.add(panelCreacion, BorderLayout.CENTER);

        // Panel derecho: lista arriba, detalles abajo
        JPanel derecho = new JPanel(new BorderLayout(10,10));
        derecho.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        derecho.add(panelLista, BorderLayout.CENTER);
        derecho.add(panelDetalles, BorderLayout.SOUTH);
        panelDetalles.setPreferredSize(new Dimension(400, 220));

        splitHorizontal.setLeftComponent(izquierdo);
        splitHorizontal.setRightComponent(derecho);

        add(splitHorizontal, BorderLayout.CENTER);

        // Conexiones: cuando se crea un vehículo, abrir la lista de selección en un diálogo
        panelCreacion.addCrearListener(e -> {
            // Primero añadir el vehículo creado a la lista
            String resumen = panelCreacion.obtenerResumenVehiculo();
            panelLista.addVehiculo(resumen);

            // Abrir el diálogo con la lista; cuando el usuario seleccione, se mostrará en detalles
            panelLista.showDialog(this, seleccion -> {
                // Si el usuario selecciona uno desde el diálogo, mostrar detalles
                panelDetalles.mostrarDetalles(seleccion);
            });
        });

        // Cuando se selecciona un vehículo en la lista (botón de seleccionar), mostrar detalles
        panelLista.setSeleccionListener(seleccion -> {
            panelDetalles.mostrarDetalles(seleccion);
        });

        // Configurar colores como en la imagen
        getContentPane().setBackground(new Color(240, 240, 240));
    }
}