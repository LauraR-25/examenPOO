package vista;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PanelDetalles extends JPanel {
    private JTextArea txtElectronica;

    public PanelDetalles() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(150, 0, 200), 2),
                "Información del Vehículo",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14),
                new Color(150, 0, 200)
        ));

        // Área de texto para detalles
        txtElectronica = new JTextArea();
        txtElectronica.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtElectronica.setEditable(false);
        txtElectronica.setText("Seleccione un vehículo para ver sus detalles...");

        JScrollPane scrollPane = new JScrollPane(txtElectronica);
        add(scrollPane, BorderLayout.CENTER);


        // Botón de actualizar
        JButton btnActualizar = new JButton("Actualizar Lecturas");
        btnActualizar.setBackground(new Color(200, 100, 0));
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.addActionListener(e -> {
            txtElectronica.append("\n\n--- Lecturas actualizadas ---\n");
            txtElectronica.append("Hora: " + new java.util.Date() + "\n");
            txtElectronica.append("Temperatura: " + (85 + (int)(Math.random() * 10)) + "°C");
        });

        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelSur.add(btnActualizar);
        add(panelSur, BorderLayout.SOUTH);
    }

    // Mostrar detalles dinámicos para el vehículo seleccionado
    public void mostrarDetalles(String resumen) {
        if (resumen == null || resumen.isEmpty()) {
            txtElectronica.setText("No hay detalles para mostrar.");
            return;
        }

        // Descomponer el resumen para presentar información más legible
        StringBuilder sb = new StringBuilder();
        sb.append("Detalles del vehículo:\n\n");
        sb.append(resumen).append("\n\n");
        sb.append("Descripción ejemplo:\n");
        sb.append("- Color: ").append(extractField(resumen, "Color:")).append("\n");
        sb.append("- Modelo: ").append(extractField(resumen, "Modelo:")).append("\n");
        sb.append("- Específico: ").append(extractSpecific(resumen)).append("\n");
        sb.append("- Estado: Nuevo");

        txtElectronica.setText(sb.toString());
    }

    private String extractField(String text, String key) {
        int idx = text.indexOf(key);
        if (idx == -1) return "N/A";
        int start = idx + key.length();
        int end = text.indexOf('|', start);
        if (end == -1) end = text.length();
        return text.substring(start, end).trim();
    }

    private String extractSpecific(String text) {
        // Buscar la parte después del último '|'
        int idx = text.lastIndexOf('|');
        if (idx == -1) return "N/A";
        return text.substring(idx + 1).trim();
    }
}