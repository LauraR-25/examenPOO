package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.*;

public class PanelCreacion extends JPanel {
    private JComboBox<String> comboTipo;
    private JTextField txtColor, txtModelo, txtPuertas, txtCilindrada;
    private JCheckBox chkElectrico;
    private JButton btnCrear;
    private JLabel lblTipo, lblColor, lblModelo, lblEspecifico;

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
        comboTipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCampos();
            }
        });
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

        // Campo específico (inicialmente para Carno)
        gbc.gridx = 0; gbc.gridy = 3;
        lblEspecifico = new JLabel("Número de puertas:");
        add(lblEspecifico, gbc);

        gbc.gridx = 1;
        txtPuertas = new JTextField(15);
        txtCilindrada = new JTextField(15);
        txtCilindrada.setVisible(false);
        chkElectrico = new JCheckBox("Es eléctrico");
        chkElectrico.setVisible(false);

        JPanel panelEspecifico = new JPanel();
        panelEspecifico.setLayout(new CardLayout());
        panelEspecifico.add(txtPuertas, "CARRO");
        panelEspecifico.add(txtCilindrada, "MOTO");
        panelEspecifico.add(chkElectrico, "BICICLETA");
        add(panelEspecifico, gbc);

        // Botón crear
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnCrear = new JButton("Crear Vehículo");
        btnCrear.setBackground(new Color(100, 150, 255));
        btnCrear.setForeground(Color.WHITE);
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearVehiculo();
            }
        });
        add(btnCrear, gbc);
    }

    private void actualizarCampos() {
        String tipo = (String) comboTipo.getSelectedItem();
        CardLayout cl = (CardLayout) ((JPanel) getComponent(7)).getLayout();

        switch(tipo) {
            case "Carro":
                lblEspecifico.setText("Número de puertas:");
                cl.show((JPanel) getComponent(7), "CARRO");
                break;
            case "Moto":
                lblEspecifico.setText("Cilindrada (cc):");
                cl.show((JPanel) getComponent(7), "MOTO");
                break;
            case "Bicicleta":
                lblEspecifico.setText("Tipo de motor:");
                cl.show((JPanel) getComponent(7), "BICICLETA");
                break;
        }
    }

    private void crearVehiculo() {
        // Lógica para crear vehículo (similar a la versión de consola)
        JOptionPane.showMessageDialog(this, "Vehículo creado exitosamente");
    }
}