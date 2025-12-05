package vista;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.function.Consumer;
import modelo.*;

public class PanelLista extends JPanel {
    private JList<String> listaVehiculos;
    private DefaultListModel<String> modeloLista;
    private JButton btnSeleccionar;
    private Consumer<String> seleccionListener;
    private int nextIndex = 0; // para generar letras A,B,C...

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

        // Modelo de la lista
        modeloLista = new DefaultListModel<>();
        // No agregar elementos de ejemplo: la lista debe empezar vacía.
        nextIndex = 0;

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
                if (seleccionListener != null) seleccionListener.accept(seleccionado);
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
        // Por ahora mostramos un mensaje; también se notifica al listener externo
        JOptionPane.showMessageDialog(this,
                "Mostrando electrónica de: " + vehiculo + "\n" +
                        "Sistema: Sist. Combustión v2.1\n" +
                        "Estado: Operativo",
                "Detalles Electrónicos",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Permitir agregar vehículos a la lista desde el exterior
    public void addVehiculo(String resumen) {
        // Generar etiqueta tipo 'Vehículo X - ' con letra A,B,C...
        String letter = indexToLetters(nextIndex);
        String entry = String.format("Vehículo %s - %s", letter, resumen);
        modeloLista.addElement(entry);
        nextIndex++;
    }

    // Seleccionar el último elemento (por ejemplo, después de crear)
    public void seleccionarUltimo() {
        int last = modeloLista.getSize() - 1;
        if (last >= 0) {
            listaVehiculos.setSelectedIndex(last);
            listaVehiculos.ensureIndexIsVisible(last);
        }
    }

    // Permitir que un controlador reciba la selección
    public void setSeleccionListener(Consumer<String> listener) {
        this.seleccionListener = listener;
    }

    // Abrir un diálogo con la lista para seleccionar (lo que el usuario solicitó: abrir ventana de lista)
    public void showDialog(Window owner, Consumer<String> onSelect) {
        JDialog dialog = new JDialog(owner, "Lista de Vehículos", Dialog.ModalityType.APPLICATION_MODAL);
        JList<String> dialogList = new JList<>(modeloLista);
        dialogList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane sp = new JScrollPane(dialogList);
        dialog.getContentPane().setLayout(new BorderLayout(10,10));
        dialog.getContentPane().add(sp, BorderLayout.CENTER);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnOk = new JButton("Seleccionar");
        btnOk.addActionListener(e -> {
            int idx = dialogList.getSelectedIndex();
            if (idx != -1) {
                String sel = modeloLista.getElementAt(idx);
                if (onSelect != null) onSelect.accept(sel);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Seleccione un vehículo", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
        botones.add(btnOk);
        dialog.getContentPane().add(botones, BorderLayout.SOUTH);
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(owner);
        dialog.setVisible(true);
    }

    // Convierte índice 0->A,1->B,...25->Z,26->AA, etc.
    private String indexToLetters(int index) {
        StringBuilder sb = new StringBuilder();
        index++; // 1-based
        while (index > 0) {
            int rem = (index - 1) % 26;
            sb.insert(0, (char)('A' + rem));
            index = (index - 1) / 26;
        }
        return sb.toString();
    }
}