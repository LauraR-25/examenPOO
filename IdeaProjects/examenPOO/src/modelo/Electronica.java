package modelo;

public class Electronica {
    private String sistema;
    private String version;

    public Electronica(String sistema, String version) {
        this.sistema = sistema;
        this.version = version;
    }

    public String getSistema() { return sistema; }
    public void setSistema(String sistema) { this.sistema = sistema; }

    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }

    @Override
    public String toString() {
        return "Sistema: " + sistema + ", Versión: " + version;
    }
}