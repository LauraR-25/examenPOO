package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import modelo.*;

public class PanelCreacion extends JPanel {
    private JComboBox<String> comboTipo;
    private JTextField txtColor, txtModelo, txtPuertas, txtCilindrada;
    private JCheckBox chkElectrico;
    private JButton btnCrear;
    private JLabel lblTipo, lblColor, lblModelo, lblEspecifico;
    private JPanel panelEspecifico; // almacenar como campo

    public PanelCreacion() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Tipo de vehículo
        gbc.gridx = 0; gbc.gridy = 0;
        lblTipo = new JLabel("Tipo de vehículo:");
        add(lblTipo, gbc);

        gbc.gridx = 1;
        comboTipo = new JComboBox<>(new String[]{"Carro", "Moto", "Bicicleta"});
        comboTipo.addActionListener(e -> actualizarCampos());
        add(comboTipo, gbc);

        // Color
        gbc.gridx = 0; gbc.gridy = 1;
        lblColor = new JLabel("Color:");
        add(lblColor, gbc);

        gbc.gridx = 1;
        txtColor = new JTextField(15);
        add(txtColor, gbc);

        // Modelo
        gbc.gridx = 0; gbc.gridy = 2;
        lblModelo = new JLabel("Modelo:");
        add(lblModelo, gbc);

        gbc.gridx = 1;
        txtModelo = new JTextField(15);
        add(txtModelo, gbc);

        // Campo específico
        gbc.gridx = 0; gbc.gridy = 3;
        lblEspecifico = new JLabel("Número de puertas:");
        add(lblEspecifico, gbc);

        gbc.gridx = 1;
        txtPuertas = new JTextField(15);
        txtCilindrada = new JTextField(15);
        txtCilindrada.setVisible(false);
        chkElectrico = new JCheckBox("Es eléctrico");
        chkElectrico.setVisible(false);

        panelEspecifico = new JPanel();
        panelEspecifico.setLayout(new CardLayout());
        panelEspecifico.add(txtPuertas, "CARRO");
        panelEspecifico.add(txtCilindrada, "MOTO");
        panelEspecifico.add(chkElectrico, "BICICLETA");
        add(panelEspecifico, gbc);

        // Botón crear (no registra acción interna; controlador externo podrá añadir listener)
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnCrear = new JButton("Crear Vehículo");
        btnCrear.setBackground(new Color(100, 150, 255));
        btnCrear.setForeground(Color.WHITE);
        add(btnCrear, gbc);

        // Inicializar campos según tipo por defecto
        actualizarCampos();
    }

    private void actualizarCampos() {
        String tipo = (String) comboTipo.getSelectedItem();
        if (tipo == null) return; // evitar NullPointerException si no hay selección
        CardLayout cl = (CardLayout) panelEspecifico.getLayout();

        switch(tipo) {
            case "Carro":
                lblEspecifico.setText("Número de puertas:");
                cl.show(panelEspecifico, "CARRO");
                break;
            case "Moto":
                lblEspecifico.setText("Cilindrada (cc):");
                cl.show(panelEspecifico, "MOTO");
                break;
            case "Bicicleta":
                lblEspecifico.setText("Tipo de motor:");
                cl.show(panelEspecifico, "BICICLETA");
                break;
        }
    }

    // Permite que un controlador externo capture el evento de crear
    public void addCrearListener(ActionListener l) {
        btnCrear.addActionListener(l);
    }

    // Obtener resumen de creación para agregar a la lista
    public String obtenerResumenVehiculo() {
        String tipo = (String) comboTipo.getSelectedItem();
        String color = txtColor.getText().trim();
        String modelo = txtModelo.getText().trim();
        String especifico = "";

        switch(tipo) {
            case "Carro":
                especifico = "Puertas: " + txtPuertas.getText().trim();
                break;
            case "Moto":
                especifico = "Cilindrada: " + txtCilindrada.getText().trim();
                break;
            case "Bicicleta":
                especifico = chkElectrico.isSelected() ? "Eléctrica" : "No eléctrica";
                break;
        }

        return String.format("%s | Color: %s | Modelo: %s | %s", tipo, color, modelo, especifico);
    }

    // Getters para campos individuales (útiles si se necesita más control)
    public String getTipo() { return (String) comboTipo.getSelectedItem(); }
    public String getColor() { return txtColor.getText().trim(); }
    public String getModelo() { return txtModelo.getText().trim(); }
    public String getEspecifico() {
        String tipo = getTipo();
        switch(tipo) {
            case "Carro": return txtPuertas.getText().trim();
            case "Moto": return txtCilindrada.getText().trim();
            case "Bicicleta": return chkElectrico.isSelected() ? "Eléctrica" : "No eléctrica";
            default: return "";
        }
    }
}