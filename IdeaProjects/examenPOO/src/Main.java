import vista.VentanaPrincipal;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Usar SwingUtilities para thread-safe
        SwingUtilities.invokeLater(() -> {
            try {
                // Establecer Look and Feel (opcional)
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}