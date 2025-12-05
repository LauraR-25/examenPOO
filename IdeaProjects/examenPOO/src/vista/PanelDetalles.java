package vista;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PanelDetalles extends JPanel {
    private JTextArea txtElectronica;
    private JLabel lblImagen;

    public PanelDetalles() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(150, 0, 200), 2),
                "Información Electrónica del Vehículo",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14),
                new Color(150, 0, 200)
        ));

        // Área de texto para detalles electrónicos
        txtElectronica = new JTextArea();
        txtElectronica.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtElectronica.setEditable(false);
        txtElectronica.setText(
                "=== SISTEMA ELECTRÓNICO DEL VEHÍCULO ===\n\n" +
                        "1. Sistema principal: Sist. Combustión v2.1\n" +
                        "2. Subsistema de control: OK\n" +
                        "3. Sensores:\n" +
                        "   - Temperatura: 85°C\n" +
                        "   - Presión: 2.3 bar\n" +
                        "   - Nivel combustible: 75%\n" +
                        "4. Estado general: OPERATIVO\n\n" +
                        "Última revisión: 15/05/2024\n" +
                        "Próxima revisión: 15/11/2024"
        );

        JScrollPane scrollPane = new JScrollPane(txtElectronica);
        add(scrollPane, BorderLayout.CENTER);

        // Panel para imagen/diagrama (simulado)
        JPanel panelImagen = new JPanel();
        panelImagen.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panelImagen.setPreferredSize(new Dimension(200, 0));

        lblImagen = new JLabel("<html><center>DIAGRAMA<br>ELECTRÓNICO<br>[IMAGEN]</center></html>");
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagen.setFont(new Font("Arial", Font.ITALIC, 16));
        lblImagen.setForeground(new Color(100, 100, 100));

        panelImagen.add(lblImagen);
        add(panelImagen, BorderLayout.EAST);

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
}