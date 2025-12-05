package modelo;

public class Bicicleta extends Vehiculo {
    private boolean esElectrico;

    public Bicicleta(String color, String modelo, Electronica electronica, boolean esElectrico) {
        super(color, modelo, electronica);
        this.esElectrico = esElectrico;
    }

    public boolean isEsElectrico() { return esElectrico; }
    public void setEsElectrico(boolean esElectrico) { this.esElectrico = esElectrico; }

    @Override
    public String mostrarDatos() {
        return "Bicicleta - Modelo: " + getModelo() + ", Color: " + getColor() + ", Eléctrico: " + esElectrico;
    }
}