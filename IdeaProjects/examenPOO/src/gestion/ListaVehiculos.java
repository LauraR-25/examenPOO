package gestion;

import modelo.Vehiculo;
import java.util.ArrayList;
import java.util.List;

public class ListaVehiculos {
    private List<Vehiculo> Vehiculos;

    public ListaVehiculos() {
        Vehiculos = new ArrayList<>();
    }

    public void agregarVehiculo(Vehiculo v) {
        Vehiculos.add(v);
    }

    public List<Vehiculo> getVehiculos() {
        return Vehiculos;
    }

    public Vehiculo seleccionarVehiculo(int indice) {
        if (indice >= 0 && indice < Vehiculos.size()) {
            return Vehiculos.get(indice);
        }
        return null;
    }

    public void actualizarVehiculo(int indice, Vehiculo v) {
        if (indice >= 0 && indice < Vehiculos.size()) {
            Vehiculos.set(indice, v);
        }
    }
}