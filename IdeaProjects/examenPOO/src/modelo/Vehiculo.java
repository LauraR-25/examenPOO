package modelo;

public abstract class Vehiculo {
    private String color;
    private String modelo;
    private Electronica electronica;

    public Vehiculo(String color, String modelo, Electronica electronica) {
        this.color = color;
        this.modelo = modelo;
        this.electronica = electronica;
    }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public Electronica getElectronica() { return electronica; }
    public void setElectronica(Electronica electronica) { this.electronica = electronica; }

    public abstract String mostrarDatos();
}