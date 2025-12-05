package modelo;

public class Carro extends Vehiculo {
    private int puertas;

    public Carro(String color, String modelo, Electronica electronica, int puertas) {
        super(color, modelo, electronica);
        this.puertas = puertas;
    }

    public int getPuertas() { return puertas; }
    public void setPuertas(int puertas) { this.puertas = puertas; }

    @Override
    public String mostrarDatos() {
        return "Carro - Modelo: " + getModelo() + ", Color: " + getColor() + ", Puertas: " + puertas;
    }
}