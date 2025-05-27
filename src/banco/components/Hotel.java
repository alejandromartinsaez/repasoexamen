package banco.components;

public class Hotel {
    private int id;
    private String cuidad;
    private String nombre;
    private int habitacionesDisponibles;

    public Hotel(int id, String cuidad, String nombre, int habitacionesDisponibles) {
        this.id = id;
        this.cuidad = cuidad;
        this.nombre = nombre;
        this.habitacionesDisponibles = habitacionesDisponibles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCuidad() {
        return cuidad;
    }

    public void setCuidad(String cuidad) {
        this.cuidad = cuidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHabitacionesDisponibles() {
        return habitacionesDisponibles;
    }

    public void setHabitacionesDisponibles(int habitacionesDisponibles) {
        this.habitacionesDisponibles = habitacionesDisponibles;
    }
}
