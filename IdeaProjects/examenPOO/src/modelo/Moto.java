package modelo;

public class Moto extends Vehiculo {
    private int cilindrada;

    public Moto(String color, String modelo, Electronica electronica, int cilindrada) {
        super(color, modelo, electronica);
        this.cilindrada = cilindrada;
    }

    public int getCilindrada() { return cilindrada; }
    public void setCilindrada(int cilindrada) { this.cilindrada = cilindrada; }

    @Override
    public String mostrarDatos() {
        return "Moto - Modelo: " + getModelo() + ", Color: " + getColor() + ", Cilindrada: " + cilindrada + "cc";
    }
}