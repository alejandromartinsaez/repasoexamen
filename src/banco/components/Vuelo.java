package banco.components;

import java.time.LocalDate;

public class Vuelo {
    private int id;
    private String origen;
    private String destino;
    private LocalDate fecha;
    private int plazasDisponibles;

    public Vuelo(int id, String origen, String destino, LocalDate fecha, int plazasDisponibles) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.plazasDisponibles = plazasDisponibles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getPlazasDisponibles() {
        return plazasDisponibles;
    }

    public void setPlazasDisponibles(int plazasDisponibles) {
        this.plazasDisponibles = plazasDisponibles;
    }

    @Override
    public String toString() {
        return "Vuelo{" +
                "id=" + id +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", fecha=" + fecha +
                ", plazasDisponibles=" + plazasDisponibles +
                '}';
    }
}
